app.service('userService', function ($q, roles, sessionService) {
    this.logIn = function(username, password) {
        // LogIn request
        var deferred = $q.defer();
        Parse.User.logIn(username, password, {
            success: function(user) {
                alert("User successfully signed in.");
                deferred.resolve(user);
            },
            error: function(user, error) {
                // The login failed. Check error to see why.
                alert(error);
                deferred.reject();
            }
        });
        return deferred.promise;
    }

    this.signup = function(username, password) {
        var deferred = $q.defer();
        var user = new Parse.User();
        user.set("username", username);
        user.set("password", password);

        user.signUp(null, {
            success: function (user) {
                alert("User successfully signed up.");
                deferred.resolve();
            },
            error: function (user, error) {
                alert("Error: " + error.code + " " + error.message);
                deferred.reject();
            }
        });
        return deferred.promise;
    }

    this.getUsers = function() {
        var deferred = $q.defer();
        var userQuery = Parse.Object.extend("User");
        var query = new Parse.Query(userQuery);
        var users = [];
        query.find({
            success: function(results) {
                results.forEach(function(userResult) {
                    users.push({
                        username: userResult.get("username")
                    })
                })

                deferred.resolve(users);
            },
            error: function(error) {
                console.log("Error: " + error.code + " " + error.message);
                deferred.reject();
            }
        });

        return deferred.promise;
    }



})