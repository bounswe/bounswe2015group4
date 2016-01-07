app.service('notificationService', function ($q, $http, sessionService, baseApiUrl) {
    /**
    *In the function getNotifications, token is taken into the function as variable.
    *The function provides the functionality of notification system. If a user gets a
    *notification, the function is called.
    *
    *@param token
    *@return notifiedUser
    */
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
