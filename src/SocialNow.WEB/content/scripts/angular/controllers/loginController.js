<<<<<<< HEAD
app.controller('loginController', function ($scope, userService, roles, sessionService, helperService) {
    $scope.login = function (isValidForm) {
        if(!isValidForm) {
            $scope.submitted = true;
            return;
        }

        userService.logIn($scope.currentUser.Email, $scope.currentUser.Password).then(function (user) {
            sessionService.setUserCredentials($scope.currentUser.Email, user.relation('role'), $scope.currentUser.name, $scope.currentUser.surname);
=======
app.controller('loginController', function($scope, userService, roles, sessionService, helperService) {
    $scope.login = function () {

        userService.logIn($scope.currentUser.Email, $scope.currentUser.Password).then(function(user) {
            console.log(user.get('role').query().toJSON().rolename);
            sessionService.setUserCredentials($scope.currentUser.Email, user.relation('role'),user.get('Name'),user.get("Surname"));
>>>>>>> refs/remotes/origin/master
            helperService.goToPage('/');
        }, function (error) {
            helperService.consoleError(error.message);
            $scope.errorMessage = 'Invalid credentials';
        });
    }
});
