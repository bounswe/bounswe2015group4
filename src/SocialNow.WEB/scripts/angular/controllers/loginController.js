app.controller('loginController', function($scope, loginService) {
    $scope.users = [];

    $scope.signup = function () {
        loginService.signup($scope.currentUser.Username, $scope.currentUser.Password).then(function() {
            $scope.getUsers();
            $scope.currentUser = {};
        });
    }

    $scope.getUsers = function () {
        loginService.getUsers().then(function(users) {
            $scope.users = users;
        });
    }

    $scope.getUsers();
});
