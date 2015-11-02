app.service('userService', function ($q, roles, sessionService, roleService) {
    this.logIn = function (email, password) {
        var deferred = $q.defer();
        Parse.User.logIn(email, password, {
            success: function (loggedInUser) {
                var userClass = Parse.Object.extend("User");
                var query = new Parse.Query(userClass);
                query.equalTo("objectId", loggedInUser.id);
                query.equalTo("emailVerified", true);
                query.first({
                    success: function (user) {
                        if (user) {
                            deferred.resolve(user);
                        } else {
                            deferred.reject('Email verification has not done yet');
                        }
                    },
                    error: function () {
                        deferred.reject('Email verification has not done yet');
                    }
                });
            },
            error: function (user, error) {
                console.log(error);
                deferred.reject('Invalid credentials');
            }
        });

        return deferred.promise;
    }

    this.signup = function (currentUser) {
        var deferred = $q.defer();
        var user = new Parse.User();
        user.set("email", currentUser.Email);
        user.set("username", currentUser.Email);
        user.set("password", currentUser.Password);
        user.set("Name", currentUser.name);
        user.set("Surname", currentUser.surname)

        roleService.getRoles().then(function (roles) {

                roles.forEach(function (role) {

                    if (role.get("rolename") == currentUser.currentRole.name) {
                        user.set("role",role.toPointer());
                    }
                });
            }, function (error) {
                console.log(error);
            }
        );
        user.signup(null, {
            success: function (user) {

                deferred.resolve(user);
            },
            error: function (user, error) {
                deferred.reject(error);
            }
        });
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

    this.getUsers = function () {
        var deferred = $q.defer();
        var userQuery = Parse.Object.extend("User");
        var query = new Parse.Query(userQuery);
        var users = [];
        query.find({
            success: function (results) {
                results.forEach(function (userResult) {
                    users.push({
                        username: userResult.get("username")
                    })
                })

                deferred.resolve(users);
            },
            error: function (error) {
                console.log("Error: " + error.code + " " + error.message);
                deferred.reject();
            }
        });

        return deferred.promise;
    }

    this.editUser = function(currentUser) {
        var deferred = $q.defer();
        var currentParseUser = Parse.User.current();

        currentParseUser.set('Name', currentUser.name);
        currentParseUser.set('Surname', currentUser.surname);

        if(currentUser.profilePicture) {
            var parseFile = new Parse.File('profile_picture', currentUser.profilePicture);
            currentParseUser.set('Profile_Picture', parseFile);
        }

        currentParseUser.save(null, {
            success: function (currentParseUser) {
                deferred.resolve(currentParseUser);
            }, error: function (currentParseUser, error) {
                deferred.reject(error);
            }
        });

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



    /**
     * Returns the object of current user
     *
     *
     *
     * @returns {bC.promise|Function|promise|d.promise|*}
     */
    this.getCurrentUser = function () {
        var deferred = $q.defer();
        var currentUserEmail = sessionService.getUserInfo().email;
        console.log(currentUserEmail);
        var query = new Parse.Query(Parse.User);
        query.include('events');               // events is pointer array to Event Table,
        // this ensures us to get the real data of these to
        query.equalTo('email', currentUserEmail);
        query.first({
            success: function (user) {
                console.log(user);
                deferred.resolve(user);
            },
            error: function (user, error) {
                deferred.reject(error);
            }
        });
        return deferred.promise;
    }

})
