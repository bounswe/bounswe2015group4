app.controller('loginController', function($scope, userService, roles, sessionService, helperService) {
    $scope.login = function () {

        userService.logIn($scope.currentUser.Email, $scope.currentUser.Password).then(function(user) {
            console.log(user.get('role').query().toJSON().rolename);
            sessionService.setUserCredentials($scope.currentUser.Email, user.relation('role'),user.get('Name'),user.get("Surname"));
            helperService.goToPage('/');
        }, function(error) {
            alert(error.message);
        });
    }
});
