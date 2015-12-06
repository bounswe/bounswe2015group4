app.service('groupService', function ($q, $http, sessionService, baseApiUrl) {
    /*this.getAllGroups = function () {
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
    }*/

    this.createGroup = function (group, userToken) {
        var deferred = $q.defer();

        group.owner_token = userToken;
        $http({
            url: baseApiUrl + '/createInterestGroup',
            method: 'POST',
            data: JSON.stringify(group)
        }).success(function (group) {
            deferred.resolve(group);
        }).error(function (response) {
            deferred.reject('An error occurred!');
        })

        return deferred.promise;
    }

    /*this.getMyGroups = function (userToken) {
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
    }*/
})

