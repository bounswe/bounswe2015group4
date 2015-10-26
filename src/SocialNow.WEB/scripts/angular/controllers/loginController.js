/**
 * Created by erdem on 24.10.2015.
 */

app.controller('loginController', function($scope, userService, roles) {
    $scope.signin = function () {
        userService.logIn($scope.currentUser.Username, $scope.currentUser.Password).then(function(user) {
            console.log("user is : "+ user.username);

        });
    }

});
