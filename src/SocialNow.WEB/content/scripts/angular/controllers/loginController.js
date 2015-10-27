app.controller('loginController', function($scope, userService, roles, sessionService, helperService) {
    $scope.login = function () {
        userService.logIn($scope.currentUser.Email, $scope.currentUser.Password).then(function(user) {
            sessionService.setUserCredentials($scope.currentUser.Email, user.relation('role'));
            helperService.goToPage('/');
        }, function(error) {
            alert(error.message);
        });
    }
});
