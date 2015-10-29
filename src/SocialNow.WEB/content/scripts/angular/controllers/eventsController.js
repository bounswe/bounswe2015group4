app.controller('eventsController', function ($scope, sessionService, userService, $location, helperService, eventService) {

    var getInfo = function () {

        var user = sessionService.getUserInfo();
        $scope.user = user;
        $scope.role = user;


    }
    var getEvents = function () {

        eventService.getEventsOfCurrentUser().then(function (events) {
                var eventDetails = [];

                events.forEach(function (event) {

                    var event = event.attributes;
                    var eventDetail = {
                        description: event.event_description,
                        title: event.title,
                        date: event.event_date
                    }
                    eventDetails.push(eventDetail);
                });
                console.log(eventDetails);
                $scope.eventDetails = eventDetails;
                $scope.createEventShow = true;
                $scope.eventsShow= false;

            },
            function (error) {
                alert(error.message);
            }
        );


    }
    $scope.createEvent = function () {
        eventService.createEvent($scope.eventTitle, $scope.eventDescription, $scope.eventTime).then(function (event) {
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

            alert(error.message);
        });
    }

    $scope.showNewEvent= function(){
        $scope.createEventShow = true;
        $scope.eventsShow= false;
    }

    $scope.ShowEvents = function(){
        $scope.createEventShow = false;
        $scope.eventsShow= true;
    }

    /**
     * returns the events of logged in user
     *
     * $scope has event attributes
     */


    getInfo();
    getEvents();
    var datePickerElement = angular.element(document.getElementById('eventTime'));
    datePickerElement.daterangepicker({
        singleDatePicker: true,
        showDropdowns: true
    });


});
