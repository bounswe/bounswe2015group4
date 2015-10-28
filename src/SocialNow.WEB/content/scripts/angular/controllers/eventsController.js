app.controller('eventsController', function ($scope, sessionService, userService, $location, helperService, eventService) {

    var getInfo = function () {

        var user = sessionService.getUserInfo();
        $scope.user = user;
        $scope.role = user;

    }

    $scope.createEvent = function () {
        eventService.createEvent($scope.eventTitle, $scope.eventDescription, $scope.eventDate).then(function (event) {
            var user = sessionService.getUserInfo();
            userService.addEvent(event, user.email).then(
                function (user) {

                },
                function (error) {
                    alert(error.message);
                }
            );

        }, function (error) {

            alert(error.message);
        });
    }

    getInfo();

    var datePickerElement = angular.element(document.getElementById('eventTime'));
    datePickerElement.daterangepicker({
        timePicker: true, timePickerIncrement: 15, locale: {
            format: 'MM/DD/YYYY h:mm A'
        }
    });

});
