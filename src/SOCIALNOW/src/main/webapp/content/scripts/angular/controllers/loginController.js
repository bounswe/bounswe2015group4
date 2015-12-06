app.controller('loginController', function ($scope, userService, sessionService, helperService) {
    $scope.login = function (isValidForm) {
        if (!isValidForm) {
            $scope.submitted = true;
            return;
        }

        var token;

        userService.logIn($scope.currentUser.Email, $scope.currentUser.Password).then(function (user) {
            token = user.user_token;
            sessionService.setUserCredentials(user);
            userService.getProfileDetails(token).then(function(currentUser) {
                sessionService.setUserProfileDetails(currentUser);
                helperService.goToPage('/');
            })
        }, function (error) {
            $scope.errorMessage = error;
        });


    }
});
