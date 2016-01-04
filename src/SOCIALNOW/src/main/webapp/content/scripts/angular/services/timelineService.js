app.service('timelineService', function($q, $http, baseApiUrl) {
    this.getTimeline = function(token) {
        var deferred = $q.defer();

        var request = {
            user_token: token
        }

        $http({
            url: baseApiUrl + '/timeline',
            method: 'POST',
            data: JSON.stringify(request)
        }).success(function(response) {
            deferred.resolve(response);
        }).error(function(response) {
            deferred.reject('An error occurred!');
        })

        return deferred.promise;
    }
});
