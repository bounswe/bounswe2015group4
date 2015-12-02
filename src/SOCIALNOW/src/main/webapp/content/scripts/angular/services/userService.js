app.service('userService', function ($q, $http, sessionService, roleService, baseApiUrl) {
    this.logIn = function (email, password) {
        var deferred = $q.defer();
        var request = {
            email: email,
            password: password
        }

        $http({
            url: baseApiUrl + '/login',
            method: 'POST',
            data: JSON.stringify(request)
        }).success(function(user) {
            deferred.resolve(user);
        }).error(function(response) {
            deferred.reject('An error occurred!');
        })

        return deferred.promise;
    }

    this.signup = function (currentUser) {
        var deferred = $q.defer();

        $http({
            url: baseApiUrl + '/signUp',
            method: 'POST',
            data: JSON.stringify(currentUser)
        }).success(function(user) {
            if(user.id != -1) {
                deferred.resolve(user);
            } else {
                deferred.reject('Email exists!');
            }

        }).error(function(response) {
            deferred.reject('An error occurred!');
        })

        return deferred.promise;
    }

    this.passwordReset = function (email) {
        var deferred = $q.defer();
        Parse.User.requestPasswordReset(email, {
            success: function () {
                deferred.resolve('Password reset email sent');
            },
            error: function (error) {
                deferred.reject('An error occurred');
            }
        });

        return deferred.promise;
    }

    this.editUser = function(currentUser) {
        var deferred = $q.defer();

        $http({
            url: baseApiUrl + '/edit_user',
            method: 'POST',
            data: JSON.stringify(currentUser)
        }).success(function(user) {
            deferred.resolve(user);
        }).error(function(response) {
            deferred.reject('An error occurred!');
        })

        return deferred.promise;
    }

    this.addEvent = function (event, currentUserEmail) {
        var deferred = $q.defer();
        var query = new Parse.Query(Parse.User);
        query.equalTo('email', currentUserEmail);
        query.first({
            success: function (user) {

                if (!user.get("events")) {
                    var events = [];
                    events.push(event);
                    user.set("events", events);
                } else {
                    var events = user.get("events");
                    events.push(event);
                    user.set("events", events);
                }
                user.save(null, {
                    success: function (user) {
                        deferred.resolve(user);
                    },
                    error: function () {
                        deferred.reject(error);
                    }

                });
            },
            error: function (user, error) {
                deferred.reject(error);
            }
        });
        return deferred.promise;
    }
})
