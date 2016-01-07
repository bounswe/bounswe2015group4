app.run(function ($rootScope, helperService, eventService, sessionService, $interval, notificationService) {
    var user = sessionService.getUserInfo();
    $rootScope.allNotifications = [];

    $rootScope.search = {
        word: ""
    };

    $rootScope.doSearch = function() {
        helperService.goToPage('/search/' + $rootScope.search.word);
    }

    $rootScope.goUserProfile = function(token) {
        helperService.goToPage('/profile/' + token);
    }

    $rootScope.goEventDetail = function(eventId) {
        helperService.goToPage('/eventDetail/' + eventId);
    }

    $rootScope.createInstantEvent = function(instantEvent) {
        instantEvent.instant_event_owner = user.user_token;

        eventService.createInstantEvent(instantEvent).then(function(instanceEvent) {
            getInstantEvents();
            angular.element(document.getElementById("createInstantEvent")).modal('hide');
        })
    }

    $rootScope.instantEventDetail = function(instanceEvent) {
        $rootScope.currentEventInstance = instanceEvent;
    }

    var getInstantEvents = function() {
        eventService.getInstantEvents().then(function(instanceEvents) {
            $rootScope.instanceEvents = instanceEvents;
        })
    }

    var getNotifications = function() {
        user = sessionService.getUserInfo();

        notificationService.getNotifications(user.user_token).then(function(notifications) {
            $rootScope.newNotificationCount = notifications.length;
            $rootScope.allNotifications = $rootScope.allNotifications.concat(notifications);
        });
    }

    var init = function () {
        getInstantEvents();
        $interval(getInstantEvents, 600000);

        if(user.user_token) {
            getNotifications();
            $interval(getNotifications, 6000);
        }
    }

    init();
})


