app.service('userService', function ($q, roles, sessionService) {
    this.logIn = function (email, password) {
        var deferred = $q.defer();
        Parse.User.logIn(email, password, {
            success: function (user) {
                deferred.resolve(user);
            },
            error: function (user, error) {
                deferred.reject(error);
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

        var relation = user.relation("role");
        relation.add(currentUser.currentRole);

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
