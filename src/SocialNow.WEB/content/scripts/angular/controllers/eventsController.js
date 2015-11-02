app.controller('eventsController', function ($scope, sessionService, userService, $location, helperService, eventService) {

    var getInfo = function () {

        var user = sessionService.getUserInfo();
        $scope.user = user;
        $scope.role = user;


    }

    var getAllEvents = function () {
        eventService.getAllEvents().then(function(events){
            var allEventDetails = [];

            events.forEach(function (event) {

                var event = event.attributes;
                console.log(event.event_photo._url);
                var eventDetail = {
                    description: event.event_description,
                    title: event.title,
                    date: event.event_date,
                    location : event.event_location,
                    src : event.event_photo._url
                }
                allEventDetails.push(eventDetail);
            });
            $scope.allEventDetails = allEventDetails;
            $scope.createEventShow = true;
            $scope.eventsShow= false;


        }, function(error){
            console.log(error + "getAllEvents");
        });

    }




    var getEvents = function () {

        eventService.getEventsOfCurrentUser().then(function (events) {
                var eventDetails = [];

                events.forEach(function (event) {

                    var event = event.attributes;
                    var eventDetail = {
                        description: event.event_description,
                        title: event.title,
                        date: event.event_date,
                        location : event.event_location,
                        src : event.event_photo._url
                    }
                    eventDetails.push(eventDetail);
                });
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
        var eventPhotoPicker = document.getElementById('evenPhotoPicker');
        var eventPicture="";
        if(eventPhotoPicker.files.length) {
             eventPicture = eventPhotoPicker.files[0];
        }

        eventService.createEvent($scope.eventTitle, $scope.eventDescription, $scope.eventTime,eventPicture,$scope.eventLocation).then(function (event) {
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
        $scope.allEventsShow =false;
    }

    $scope.ShowEvents = function(){
        $scope.createEventShow = false;
        $scope.eventsShow= true;
        $scope.allEventsShow =false;
    }

    $scope.showAllEvents = function () {
        $scope.createEventShow = false;
        $scope.eventsShow= false;
        $scope.allEventsShow = true;
    }

    /**
     * returns the events of logged in user
     *
     * $scope has event attributes
     */


    getInfo();
    getEvents();
    getAllEvents();
    var datePickerElement = angular.element(document.getElementById('eventTime'));
    datePickerElement.daterangepicker({
        singleDatePicker: true,
        showDropdowns: true
    });


});
