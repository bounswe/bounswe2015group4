app.controller('signUpController', function($scope, userService, roleService, helperService, sessionService) {
    $scope.currentUser = {};

    var getRoles = function() {
        roleService.getRoles().then(function(roles) {
            $scope.roles = roles;
            $scope.currentUser.currentRole = roles[0];
        });
    }

    $scope.signUp = function () {
        userService.signup($scope.currentUser.Email, $scope.currentUser.Password, $scope.currentUser.currentRole,$scope.currentUser.name,$scope.currentUser.surname).then(function() {
            sessionService.setUserCredentials($scope.currentUser.Email, $scope.currentRole);
            helperService.goToPage('/');
        });
    }

    $scope.getUsers = function () {
        userService.getUsers().then(function(users) {
            $scope.users = users;
        });
    }

    getRoles();
});
