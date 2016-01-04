app.run(function ($rootScope, helperService, eventService, sessionService, $interval) {
    var user = sessionService.getUserInfo();

    $rootScope.search = {
        word: ""
    };

    $rootScope.doSearch = function() {
        helperService.goToPage('/search/' + $rootScope.search.word);
    }

    $rootScope.goUserProfile = function(token) {
        helperService.goToPage('/profile/' + token);
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
            setRemainingTimes();
            $interval(setRemainingTimes, 60000);
        })
    }

    var setRemainingTimes = function() {
        var now = Math.floor(Date.now() / 1000);

        angular.forEach($rootScope.instanceEvents, function(instanceEvent) {
            instanceEvent.remainingTime = instanceEvent.duration_in_minutes - utils.findDifferenceOfTimestampsInMinutes(instanceEvent.date, now);
        })
    }

    var init = function () {
        getInstantEvents();
    }

    init();
})


