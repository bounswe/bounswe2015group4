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
        })
    }

    var init = function () {
        getInstantEvents();
        $interval(getInstantEvents, 600000);
    }

    init();
})


