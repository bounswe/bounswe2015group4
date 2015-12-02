app.controller('profileController', function ($scope, sessionService, userService, helperService) {
    $scope.user = sessionService.getUserInfo();

    $scope.editProfile = function (isValid) {
        if (!isValid) {
            $scope.submitted = true;
            return;
        }

        userService.editUser($scope.user).then(function(currentUser) {
            sessionService.setUserCredentials(currentUser);
            $scope.successMessage = 'Your profile is edited successfully';
            $scope.user = currentUser;
        }, function(error) {
            $scope.errorMessage = error.message;
        })
    }
})
