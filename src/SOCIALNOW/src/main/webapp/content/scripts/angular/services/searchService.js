app.service('searchService', function ($q, $http, baseApiUrl) {
    /**
    *In the function search, the userToken and searchTerm are taken as variable of the function
    *and in a case the function is successful, the selected user look for a searchTerm in the
    *database. This check is completed under user, group and event categories.
    *
    *@param userToken
    *@param searchTerm
    *@return searchedTerm
    */
    this.search = function (searchTerm, userToken) {
        var deferred = $q.defer();

        var request = {
            user_token: userToken,
            keyword: searchTerm
        };

        $http({
            url: baseApiUrl + '/search',
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
    *In the function search, the searchTerm is taken as variable of the function
    *and in a case the function is successful, the searchTerm written is compared with the
    *entities in the database by considering its' meaning.
    *
    *@param searchTerm
    *@return searchedTerm
    */
    this.semantic = function(searchTerm) {
        var deferred = $q.defer();

        $http({
            url: baseApiUrl + '/deneme',
            method: 'POST',
            data: searchTerm
        }).success(function (response) {
            deferred.resolve(response);
        }).error(function (response) {
            deferred.reject('An error occurred!');
        })

        return deferred.promise;
    }
});