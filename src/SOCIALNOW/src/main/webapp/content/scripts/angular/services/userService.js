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
            if(user.id != -1) {
                deferred.resolve(user);
            } else {
                deferred.reject('Wrong email or password!');
            }
        }).error(function(response) {
            deferred.reject('An error occurred!');
        })

        return deferred.promise;
    }

    this.getProfileDetails = function(token) {
        var deferred = $q.defer();
        var request = {
            user_token: token
        }

        $http({
            url: baseApiUrl + '/showProfileDetails',
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

    this.followUser = function(userToken, followToken) {
        var deferred = $q.defer();
        var request = {
            user_token: userToken,
            user_token_follow: followToken
        }

        $http({
            url: baseApiUrl + '/followUser',
            method: 'POST',
            data: JSON.stringify(request)
        }).success(function(response) {
            deferred.resolve(response);
        }).error(function(response) {
            deferred.reject('An error occurred!');
        })

        return deferred.promise;
    }

    this.unfollowUser = function(userToken, unfollowToken) {
        var deferred = $q.defer();
        var request = {
            user_token: userToken,
            user_token_follow: unfollowToken
        }

        $http({
            url: baseApiUrl + '/unFollowUser',
            method: 'POST',
            data: JSON.stringify(request)
        }).success(function(response) {
            deferred.resolve(response);
        }).error(function(response) {
            deferred.reject('An error occurred!');
        })

        return deferred.promise;
    }

    this.setShowingUserProfile = function(user) {
        var currentUser = {};

        currentUser.followers = user.user_followers;
        currentUser.followings = user.user_followings;
        currentUser.interestGroups = user.user_interest_groups;
        currentUser.participatingEvents = user.user_participating_events;
        currentUser.tags = utils.manipulateTags(user.user_tags);
        currentUser.numberOfFollowers = user.numberOfFollowers;
        currentUser.numberOfFollowings = user.numberOfFollowings;
        currentUser.name = user.name;
        currentUser.surname = user.surname;
        currentUser.role = user.role;
        currentUser.email = user.email;
        currentUser.user_token = user.user_token;
        currentUser.user_photo = user.user_photo;

        currentUser.participatingEvents = utils.manipulateEvents(currentUser.participatingEvents);

        utils.manipulateUser(currentUser);

        currentUser.stringTags = currentUser.tags.join();

        return currentUser;
    }

    this.updateProfileDetails = function(token) {
        this.getProfileDetails(token).then(function(currentUser) {
            sessionService.setUserProfileDetails(currentUser);
        }, function(response) {
        })
    }
})
