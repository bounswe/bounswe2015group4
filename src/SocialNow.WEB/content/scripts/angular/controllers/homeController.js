app.controller('homeController', function ($scope, userService, sessionService, $location, helperService) {

    var getInfo = function () {

        var user = sessionService.getUserInfo();
        $scope.user = user;
        $scope.role = user;

    }

    $scope.goToEvents = function () {
        helperService.goToPage('/events');

    }

    $scope.logout = function () {
        sessionService.remove('userInfo');
        helperService.goToPage('/login');
    }

    getInfo();

});
