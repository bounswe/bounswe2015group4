app.controller('signUpController', function ($scope, userService, roleService, helperService, sessionService, $timeout) {
    $scope.currentUser = {};

    var getRoles = function () {
        $scope.roles = roleService.getRoles();
        $scope.currentUser.role = $scope.roles[0];
    }

    $scope.signUp = function (isValid) {
        if (!isValid) {
            $scope.submitted = true;
            return;
        }

        userService.signup($scope.currentUser).then(function (user) {
            $scope.errorMessage = '';
            $scope.successMessage = 'You are successfully signed up!';
            sessionService.setUserCredentials(user);
            $timeout(function () {
                helperService.goToPage('/');
            }, 2000);

        }, function (error) {
            $scope.errorMessage = error;
        });
    }

    getRoles();
});
