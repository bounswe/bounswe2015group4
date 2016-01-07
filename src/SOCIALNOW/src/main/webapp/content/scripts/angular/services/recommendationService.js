app.service('recommendationService', function($q, $http, baseApiUrl) {
    /**
    *In the function getRecommendations, userToken is taken as variable of the function
    *and the groups and events which are going to be recommended to that user are returned.
    *
    *@param usertoken
    *@return recommendedActivity
    */
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
    /**
    *In the function getUserRecommendations, userToken is taken as variable of the function
    *and the people who are going to be recommended to that user is returned.
    *
    *@param usertoken
    *@return recommendedUser
    */
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
