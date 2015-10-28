app.controller('loginController', function ($scope, userService, roles, sessionService, helperService) {
    $scope.login = function (isValidForm) {
        if (!isValidForm) {
            $scope.submitted = true;
            return;
        }

        userService.logIn($scope.currentUser.Email, $scope.currentUser.Password).then(function (user) {
            sessionService.setUserCredentials($scope.currentUser.Email, user.relation('role'), user.get('name'), user.get("surname"));
            helperService.goToPage('/');
        }, function (error) {
            helperService.consoleError(error.message);
            $scope.errorMessage = 'Invalid credentials';
        });
    }
});
