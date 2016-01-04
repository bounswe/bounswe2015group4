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

    this.getUserRecommendations = function(userToken) {
        var deferred = $q.defer();

        var request = {
            user_token: userToken
        }

        $http({
            url: baseApiUrl + '/recommendUsers',
            method: 'POST',
            data: JSON.stringify(request)
        }).success(function (users) {
            deferred.resolve(users);
        }).error(function (response) {
            deferred.reject('An error occurred!');
        })

        return deferred.promise;
    }
})
