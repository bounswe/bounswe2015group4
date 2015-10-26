app.controller('signUpController', function($scope, userService, roleService, roles) {
    $scope.users = [];

    var getRoles = function() {
        roleService.getRoles().then(function(roles) {
            $scope.roles = roles;
            $scope.currentRole = roles[0];
        });
    }

    $scope.signUp = function () {
        userService.signup($scope.currentUser.Email, $scope.currentUser.Password, $scope.currentRole).then(function() {
            $scope.currentUser = {};
        });
    }

    $scope.getUsers = function () {
        userService.getUsers().then(function(users) {
            $scope.users = users;
        });
    }

    getRoles();
});
