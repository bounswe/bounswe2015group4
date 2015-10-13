app.controller('loginController', function($scope, loginService) {
    $scope.users = [];

    $scope.signup = function () {
        loginService.signup($scope.currentUser.Username, $scope.currentUser.Password);
        $scope.getUsers();
    }

    $scope.getUsers = function () {
        loginService.getUsers().then(function(users) {
            $scope.users = users;
        });
    }

    $scope.getUsers();
});
