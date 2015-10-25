/**
 * Created by erdem on 24.10.2015.
 */

app.controller('signInController', function($scope, userService, roles) {
    $scope.signin = function () {
        userService.logIn($scope.currentUser.Username, $scope.currentUser.Password).then(function() {


        });
    }

});
