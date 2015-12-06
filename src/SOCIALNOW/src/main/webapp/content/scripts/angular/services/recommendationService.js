app.service('recommendationService', function($q, $http, baseApiUrl) {
    this.getRecommendations = function(userToken) {
        var deferred = $q.defer();

        var request = {
            user_token: userToken
        }

        $http({
            url: baseApiUrl + '/recommend',
            method: 'POST',
            data: JSON.stringify(request)
        }).success(function (response) {
            deferred.resolve(response);
        }).error(function (response) {
            deferred.reject('An error occurred!');
        })

        return deferred.promise;
    }
})
