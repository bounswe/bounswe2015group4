app.controller('profileController', function ($scope, sessionService, userService) {
    $scope.user = sessionService.getUserInfo();
    $scope.currentUser = sessionService.getUserInfo();

    $scope.editProfile = function (isValid) {
        if (!isValid) {
            $scope.submitted = true;
            return;
        }

        var profilePicker = document.getElementById('profilePicker');
        if(profilePicker.files.length) {
            $scope.currentUser.profilePicture = profilePicker.files[0];
        }

        userService.editUser($scope.currentUser).then(function(currentUser) {
            sessionService.setUserCredentials($scope.user.Email, currentUser.relation('role'), currentUser.get('Name'), currentUser.get('Surname'));
            $scope.successMessage = 'Your profile is edited successfully';
            $scope.user = sessionService.getUserInfo();
        }, function(error) {
            $scope.errorMessage = error.message;
        })
    }
})
