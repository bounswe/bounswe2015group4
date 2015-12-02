app.service('eventService', function ($q, $http, sessionService, baseApiUrl) {
    this.getAllEvents = function () {
        var deferred = $q.defer();

        $http({
            url: baseApiUrl + '/listAllEvents',
            method: 'POST'
        }).success(function (events) {
            deferred.resolve(events);
        }).error(function (response) {
            deferred.reject('An error occurred!');
        })

        return deferred.promise;
    }

    this.createEvent = function (title, description, date, file, location) {
        var deferred = $q.defer();
        var givenDate = new Date(date);
        var Event = Parse.Object.extend("Event");
        var event = new Event();
        if (file) {
            var parseFile = new Parse.File('event_photo', file);
            event.set('event_photo', parseFile);
        }

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
            deferred.resolve(events);
        }).error(function (response) {
            deferred.reject('An error occurred!');
        })

        return deferred.promise;
    }
})
