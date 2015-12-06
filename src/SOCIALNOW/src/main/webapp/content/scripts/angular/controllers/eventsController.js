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
        eventService.createEvent($scope.event, $scope.user.token).then(function (event) {
            $scope.errorMessage = "";
            $scope.successMessage = "Event is created successfully"
            $scope.currentEventRoute = $scope.eventRoutes.myEvents;

            getMyEvents();
            getAllEvents();
        }, function (error) {
            console.log(error);
        });
    }

    getMyEvents();
    getAllEvents();

    var datePickerElement = angular.element(document.getElementById('inputTime'));
    datePickerElement.daterangepicker({
        singleDatePicker: true,
        showDropdowns: true
    });
});
