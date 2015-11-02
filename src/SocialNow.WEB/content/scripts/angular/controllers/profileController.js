app.controller('profileController', function($scope, sessionService) {
    $scope.currentUser = sessionService.getUserInfo();

    $scope.editProfile = function(isValid) {
        if(!isValid) {
            $scope.submitted = true;
            return;
        }

        var currentParseUser = Parse.User.current();

        currentParseUser.set('name', $scope.currentUser.name);
        currentParseUser.set('surname', $scope.currentUser.surname);

        currentParseUser.save();
    }
})
