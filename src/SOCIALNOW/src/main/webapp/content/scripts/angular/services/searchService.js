app.service('searchService', function ($q, $http, baseApiUrl) {
    this.search = function (searchTerm) {
        var deferred = $q.defer();

        $http({
            url: baseApiUrl + '/search',
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