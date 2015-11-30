app.controller('homeController', function ($scope, userService, sessionService, $location, helperService) {
    $scope.user = sessionService.getUserInfo();
});
