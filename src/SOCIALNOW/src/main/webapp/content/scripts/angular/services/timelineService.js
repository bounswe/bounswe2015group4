app.service('timelineService', function($q, $http, baseApiUrl) {
    /**
    *In the function getTimeline, token is taken into the function as variable.
    *The function provides the functionality of timeline design. When timeline
    *is called by the user, the function is called.
    *
    *@param token
    *@return timeLine
    */
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
