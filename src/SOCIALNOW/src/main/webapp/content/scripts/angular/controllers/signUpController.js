app.controller('signUpController', function ($scope, userService, roleService, helperService, sessionService, $timeout) {
    $scope.currentUser = {};

    var getRoles = function () {
        roleService.getRoles().then(function (roles) {

            roleNames = [];
            roles.forEach(function (role) {
                roleNames.push({
                    name: role.get("rolename")
                });
        });
        $scope.roles = roleNames ;
        $scope.currentUser.currentRole = roleNames[0];
    }
    )
    ;
}

$scope.signUp = function (isValid) {
    if (!isValid) {
        $scope.submitted = true;
        return;
    }

    userService.signup($scope.currentUser).then(function () {
        $scope.errorMessage = '';
        $scope.successMessage = 'Activation email was sent';
        $timeout(function () {
            helperService.goToPage('/login');
        }, 3000);

    }, function (error) {
        $scope.errorMessage = error.message;
    });
}

$scope.getUsers = function () {
    userService.getUsers().then(function (users) {
        $scope.users = users;
    });
}

getRoles();
})
;
