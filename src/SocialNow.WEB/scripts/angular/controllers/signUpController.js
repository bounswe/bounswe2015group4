app.controller('signUpController', function($scope, userService, roles) {
    $scope.users = [];

    $scope.signUp = function () {
        userService.signup($scope.currentUser.Username, $scope.currentUser.Password).then(function() {
            $scope.currentUser = {};

        });
    }

    $scope.getUsers = function () {
        userService.getUsers().then(function(users) {
            $scope.users = users;
        });
    }
});
