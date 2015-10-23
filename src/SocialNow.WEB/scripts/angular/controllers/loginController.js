app.controller('loginController', function($scope, userService, roles) {
    $scope.users = [];

    $scope.signup = function () {
        userService.signup($scope.currentUser.Username, $scope.currentUser.Password).then(function() {
            $scope.getUsers();
            $scope.currentUser = {};
        });
    }

    $scope.getUsers = function () {
        userService.getUsers().then(function(users) {
            $scope.users = users;
        });
    }
});
