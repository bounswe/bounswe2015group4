app.controller('loginController', function ($scope, userService, sessionService, helperService) {
    $scope.login = function (isValidForm) {
        if (!isValidForm) {
            $scope.submitted = true;
            return;
        }

        userService.logIn($scope.currentUser.Email, $scope.currentUser.Password).then(function (user) {
            sessionService.setUserCredentials($scope.currentUser.Email, user.relation('role'), user.get('Name'), user.get('Surname'), user.get('Profile_Picture'));
            helperService.goToPage('/');
        }, function (error) {
            $scope.errorMessage = error;
        });
    }
});
