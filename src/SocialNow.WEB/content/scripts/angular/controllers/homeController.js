app.controller('homeController', function ($scope, userService, sessionService, $location, helperService) {

    var getInfo = function () {

        userService.getCurrentUser().then(
            function (user) {
            var userTemp = {
                name : user.get('Name'),
                surname: user.get('Surname')
            };
                $scope.user = userTemp;


            },
            function (error) {

            }
        );



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
