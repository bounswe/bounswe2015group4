app.service('eventService', function ($q, $http, sessionService, baseApiUrl) {
    this.getAllEvents = function () {
        var deferred = $q.defer();

        $http({
            url: baseApiUrl + '/listAllEvents',
            method: 'POST'
        }).success(function (events) {
            events = utils.manipulateEvents(events);

            deferred.resolve(events);
        }).error(function (response) {
            deferred.reject('An error occurred!');
        })

        return deferred.promise;
    }

    this.createEvent = function (event, userToken) {
        var deferred = $q.defer();

        event.event_host_token = userToken;
        $http({
            url: baseApiUrl + '/createEvent',
            method: 'POST',
            data: JSON.stringify(event)
        }).success(function (events) {
            deferred.resolve(events);
        }).error(function (response) {
            deferred.reject('An error occurred!');
        })

        return deferred.promise;
    }

    this.getMyEvents = function (userToken) {
        var deferred = $q.defer();

        var request = {
            event_host_token: userToken
        }

        $http({
            url: baseApiUrl + '/listMyEvents',
            method: 'POST',
            data: JSON.stringify(request)
        }).success(function (events) {
            events = utils.manipulateEvents(events);

            deferred.resolve(events);
        }).error(function (response) {
            deferred.reject('An error occurred!');
        })

        return deferred.promise;
    }

    this.getEventDetails = function(eventId) {
        var deferred = $q.defer();

        var request = {
            event_id: eventId
        }

        $http({
            url: baseApiUrl + '/events/getEventDetail',
            method: 'POST',
            data: JSON.stringify(request)
        }).success(function (eventDetail) {
            deferred.resolve(eventDetail);
        }).error(function (response) {
            deferred.reject('An error occurred!');
        })

        return deferred.promise;
    }

    this.makeGoing = function(userToken, eventId) {
        var deferred = $q.defer();

        var request = {
            user_token: userToken,
            event_id: eventId
        }

        $http({
            url: baseApiUrl + '/events/addParticipant',
            method: 'POST',
            data: JSON.stringify(request)
        }).success(function (event) {
            deferred.resolve(event);
        }).error(function (response) {
            deferred.reject('An error occurred!');
        })

        return deferred.promise;
    }

    this.makeNotgoing = function(userToken, eventId) {
        var deferred = $q.defer();

        var request = {
            user_token: userToken,
            event_id: eventId
        }

        $http({
            url: baseApiUrl + '/events/removeParticipant',
            method: 'POST',
            data: JSON.stringify(request)
        }).success(function (event) {
            deferred.resolve(event);
        }).error(function (response) {
            deferred.reject('An error occurred!');
        })

        return deferred.promise;
    }
})
