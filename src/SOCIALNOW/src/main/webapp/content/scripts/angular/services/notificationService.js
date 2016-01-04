app.service('notificationService', function ($q, $http, sessionService, baseApiUrl) {
    this.getNotifications = function(token) {
        var deferred = $q.defer();

        var request = {
            user_token: token
        }

        $http({
            url: baseApiUrl + '/users/getNotifications',
            method: 'POST',
            data: JSON.stringify(request)
        }).success(function (notifications) {
            deferred.resolve(notifications);
        }).error(function (response) {
            deferred.reject('An error occurred!');
        })

        return deferred.promise;
    }
});
