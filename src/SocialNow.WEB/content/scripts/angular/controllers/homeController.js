app.controller('homeController', function ($scope, userService, sessionService, $location, helperService) {

    var getInfo = function () {

        $scope.user = sessionService.getUserInfo();

    }

    $scope.logout = function () {
        sessionService.remove('userInfo');
        helperService.goToPage('/login');
    }

    getInfo();

});
