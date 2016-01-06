app.controller('eventsController', function ($scope, $http, sessionService, userService, $location, helperService, eventService, roleService) {
    $scope.eventRoutes = {
        addNew: 1,
        myEvents: 2,
        allEvents: 3
    }

    $scope.currentEventRoute = $scope.eventRoutes.allEvents;
    $scope.user = sessionService.getUserInfo();

    var getAllEvents = function () {
        eventService.getAllEvents($scope.user.user_token).then(function (events) {
            $scope.allEvents = events;
        }, function (error) {
            console.log(error);
        });
    }

    var getMyEvents = function () {
        eventService.getMyEvents($scope.user.user_token).then(function (events) {
            $scope.myAllEvents = events;
        }, function (error) {
            console.log(error);
        });
    }

    $scope.createEvent = function () {
        $scope.event.visibleTo = roleService.getRoleNamesAccordingToIds($scope.event.visibleTo);
        $scope.event.event_start_date = utils.convertDateToApiDate($scope.event.event_start);
        $scope.event.event_end_date = utils.convertDateToApiDate($scope.event.event_end);
        eventService.createEvent($scope.event, $scope.user.user_token).then(function (event) {
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

    var datePickerElementStart = angular.element(document.getElementById('inputStartTime'));
    datePickerElementStart.daterangepicker({
        singleDatePicker: true,
        timePicker: true,
        timePicker24Hour: true,
        autoApply: true,
        locale: {
            format: 'MM/DD/YYYY h:mm A'
        }
    });

    var datePickerElementEnd = angular.element(document.getElementById('inputEndTime'));
    datePickerElementEnd.daterangepicker({
        singleDatePicker: true,
        timePicker: true,
        timePicker24Hour: true,
        autoApply: true,
        locale: {
            format: 'MM/DD/YYYY h:mm A'
        }
    });
});
