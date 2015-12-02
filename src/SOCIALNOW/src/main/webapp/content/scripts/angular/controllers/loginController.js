app.controller('loginController', function ($scope, userService, sessionService, helperService) {
    $scope.login = function (isValidForm) {
        if (!isValidForm) {
            $scope.submitted = true;
            return;
        }

        userService.logIn($scope.currentUser.Email, $scope.currentUser.Password).then(function (user) {
            sessionService.setUserCredentials(user);
            helperService.goToPage('/');
        }, function (error) {
            $scope.errorMessage = error;
        });
    }
});
