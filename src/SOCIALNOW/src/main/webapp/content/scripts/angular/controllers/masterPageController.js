app.run(function ($rootScope, helperService, eventService) {
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
        eventService.createInstantEvent(instantEvent).then(function(instanceEvent) {
            getInstantEvents();
        })
    }

    $rootScope.instantEventDetail = function() {
        alert("Test");
    }

    var getInstantEvents = function() {
        eventService.getInstantEvents().then(function(instanceEvents) {
            $rootScope.instanceEvents = instanceEvents;
        })
    }

    var init = function () {
        getInstantEvents();
    }

    init();
})


