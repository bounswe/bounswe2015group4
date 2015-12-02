app.controller('eventsController', function ($scope, $http, sessionService, userService, $location, helperService, eventService, $interval) {
    $scope.eventRoutes = {
        addNew: 1,
        myEvents: 2,
        allEvents: 3
    }

    $scope.currentEventRoute = $scope.eventRoutes.allEvents;
    $scope.user = sessionService.getUserInfo();

    var getAllEvents = function () {
        eventService.getAllEvents().then(function (events) {
            $scope.allEvents = events;
            console.log($scope.allEvents);
        }, function (error) {
            console.log(error);
        });
    }

    var getMyEvents = function () {
        eventService.getMyEvents($scope.user.token).then(function (events) {
            $scope.myAllEvents = events;
        }, function (error) {
            console.log(error);
        });
    }

    $scope.createEvent = function () {
        var eventPhotoPicker = document.getElementById('evenPhotoPicker');
        var eventPicture = "";
        if (eventPhotoPicker.files.length) {
            eventPicture = eventPhotoPicker.files[0];
        }

        eventService.createEvent($scope.eventTitle, $scope.eventDescription, $scope.eventTime, eventPicture, $scope.eventLocation).then(function (event) {
            var user = sessionService.getUserInfo();
            userService.addEvent(event, user.email).then(
                function (user) {
                    helperService.reload();
                },
                function (error) {
                    alert(error.message);
                }
            );

        }, function (error) {
            console.log(error);
        });
    }

    $scope.$watch(function() { return $scope.tabContentInit; }, function(newValue, oldValue) {
        console.log(newValue + " " + oldValue);
    })

    getMyEvents();
    getAllEvents();

    var datePickerElement = angular.element(document.getElementById('inputTime'));
    datePickerElement.daterangepicker({
        singleDatePicker: true,
        showDropdowns: true
    });
});
