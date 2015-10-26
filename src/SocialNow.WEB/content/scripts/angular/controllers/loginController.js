app.controller('loginController', function($scope, userService, roles, signUpUrl, $location) {
    $scope.login = function () {
        userService.logIn($scope.currentUser.Email, $scope.currentUser.Password).then(function(user) {

        });
    }
});
