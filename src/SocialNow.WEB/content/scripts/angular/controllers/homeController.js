app.controller('homeController', function($scope, userService, sessionService, $location, helperService) {
    $scope.logout = function() {
        sessionService.remove('userInfo');
        helperService.goToPage('/login');
    }
})
