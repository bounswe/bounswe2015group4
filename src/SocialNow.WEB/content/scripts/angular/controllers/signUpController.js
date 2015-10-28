app.controller('signUpController', function($scope, userService, roleService, helperService, sessionService) {
    $scope.currentUser = {};

    var getRoles = function() {
        roleService.getRoles().then(function(roles) {
            $scope.roles = roles;
            $scope.currentUser.currentRole = roles[0];
        });
    }

    $scope.signUp = function (isValid) {
        if(!isValid) {
            $scope.submitted = true;
            return;
        }

        userService.signup($scope.currentUser).then(function() {
            helperService.goToPage('/login');
        }, function() {
            $scope.message = 'An error occurred';
        });
    }

    $scope.getUsers = function () {
        userService.getUsers().then(function(users) {
            $scope.users = users;
        });
    }

    getRoles();
});
