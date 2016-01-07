app.service('userService', function ($q, $http, sessionService, roleService, baseApiUrl) {
    /**
    *In the function logIn, email and password are taken as variable into the function
    *and in a case the function is successful, by using the taken information, logging in
    *process has provided.
    *
    *@param email
    *@param password
    *@return loggedIn
    */
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
        }).success(function (user) {
            if (user.id != -1) {
                deferred.resolve(user);
            } else {
                deferred.reject('Wrong email or password!');
            }
        }).error(function (response) {
            deferred.reject('An error occurred!');
        })

        return deferred.promise;
    }
    /**
    *In the function getProfileDetails, token is taken as variable into the function
    *and in a case the function is successful, the profile details of user is shown
    *on the screen.
    *
    *@param token
    *@return profileInfo
    */
    this.getProfileDetails = function (token) {
        var deferred = $q.defer();
        var request = {
            user_token: token
        }

        $http({
            url: baseApiUrl + '/showProfileDetails',
            method: 'POST',
            data: JSON.stringify(request)
        }).success(function (user) {
            deferred.resolve(user);
        }).error(function (response) {
            deferred.reject('An error occurred!');
        })

        return deferred.promise;
    }
    /**
    *In the function signup, currentUser is taken as variable into the function
    *and in a case the function is successful, signup process is completed, otherwise
    *error message is shown on console.
    *
    *@param currentUser
    *@return signedUpProfile
    */
    this.signup = function (currentUser) {
        var deferred = $q.defer();

        $http({
            url: baseApiUrl + '/signUp',
            method: 'POST',
            data: JSON.stringify(currentUser)
        }).success(function (user) {
            if (user.id != -1) {
                deferred.resolve(user);
            } else {
                deferred.reject('Email exists!');
            }

        }).error(function (response) {
            deferred.reject('An error occurred!');
        })

        return deferred.promise;
    }
    /**
    *In the function passwordReset, email is taken as variable into the function
    *and in a case the function is successful, the password of the user is cleared.
    *And resend to user's email address.
    *
    *@param email
    *@return newPassword
    */
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
    /**
    *In the function editUser, currentUser is taken as variable into the function
    *and in a case the function is successful, the user updates its' profile by changing
    *his/her personal information.
    *
    *@param currentUser
    *@return updatedProfile
    */
    this.editUser = function (currentUser) {
        var deferred = $q.defer();

        $http({
            url: baseApiUrl + '/edit_user',
            method: 'POST',
            data: JSON.stringify(currentUser)
        }).success(function (user) {
            deferred.resolve(user);
        }).error(function (response) {
            deferred.reject('An error occurred!');
        })

        return deferred.promise;
    }
    /**
    *In the function followUser, userToken and followToken are taken as variable into the function.
    *In this example, userToken represents the current user, and followToken does represent the user,
    *who is going to be followed. The function does the following issue.
    *
    *@param userToken
    *@param followToken
    *@return followedUser
    */
    this.followUser = function (userToken, followToken) {
        var deferred = $q.defer();
        var request = {
            user_token: userToken,
            user_token_follow: followToken
        }

        $http({
            url: baseApiUrl + '/followUser',
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
    *In the function unfollowUser, userToken and unfollowToken are taken as variable into the function.
    *In this example, userToken represents the current user, and unfollowToken does represent the user,
    *who is going to be unfollowed. The function handles the unfollowing process.
    *
    *@param userToken
    *@param unfollowToken
    *@return unfollowedUser
    */
    this.unfollowUser = function (userToken, unfollowToken) {
        var deferred = $q.defer();
        var request = {
            user_token: userToken,
            user_token_follow: unfollowToken
        }

        $http({
            url: baseApiUrl + '/unFollowUser',
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
    *In the function setShowingUserProfile, user is taken into the function as variable.
    *The function takesa user and after modifications, shows the modified information on
    *console.
    *
    *@param user
    *@return modifiedUser
    */
    this.setShowingUserProfile = function (user) {
        var currentUser = {};

        currentUser.username = user.email;
        currentUser.email = user.email;
        currentUser.role = user.role;
        currentUser.isAuthenticated = true;
        currentUser.name = user.name;
        currentUser.surname = user.surname;
        currentUser.user_token = user.user_token;
        currentUser.photoUrl = user.user_photo;
        currentUser.followers = user.user_followers;
        currentUser.followings = user.user_following;
        currentUser.interestGroups = user.user_interest_groups;
        currentUser.participatingEvents = user.user_participating_events;
        currentUser.tags = utils.manipulateTags(user.user_tags);
        currentUser.numberOfFollowers = user.numberOfFollowers;
        currentUser.numberOfFollowings = user.numberOfFollowings;

        currentUser.participatingEvents = utils.manipulateEvents(currentUser.participatingEvents);

        utils.manipulateUser(currentUser);

        currentUser.stringTags = currentUser.tags.join();

        currentUser.tagsModified = getTags(currentUser.tags);

        return currentUser;
    }
    /**
    *In the function getAllUsers, token is taken into the function as variable.
    *The function returns all the users registered in the database.
    *
    *@param token
    *@return allUsers
    */
    this.getAllUsers = function(token) {
        var deferred = $q.defer();

        var request = {
            user_token: token
        }

        $http({
            url: baseApiUrl + '/getAllUsers',
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
    *In the function getTags, tags are taken into the function as variable.
    *The function returns all the tags saved in the database.
    *
    *@param tags
    *@return allTags
    */
    var getTags = function (tags) {
        var tagsModified = [];

        for (var i = 0; i < (tags || []).length; i++) {
            tagsModified.push({
                tag1: tags[i++],
                tag2: tags[i++],
                tag3: tags[i]
            })
        }

        return tagsModified;
    }
})
