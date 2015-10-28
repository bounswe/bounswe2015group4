app.service('userService', function ($q, roles, sessionService) {
    this.logIn = function(email, password) {
        var deferred = $q.defer();
        Parse.User.logIn(email, password, {
            success: function(user) {
                deferred.resolve(user);
            },
            error: function(user, error) {
                deferred.reject(error);
            }
        });
        return deferred.promise;
    }

    this.signup = function(email, password, role,name, surname) {
        var deferred = $q.defer();

        var user = new Parse.User();

        user.set("email", email);
        user.set("password", password);
        user.set("Name",name);
        user.set("Surname",surname)
        user.set("username",email);

        var relation = user.relation("role");
        relation.add(role);

        user.signup(null, {
            success: function (user) {
                alert("User successfully signed up.");
                deferred.resolve(user);
            },
            error: function (user, error) {
                alert("Error: " + error.code + " " + error.message);
                deferred.reject(error);
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
<<<<<<< HEAD
=======

    this.addEvent= function(event, currentUserEmail){
        var deferred = $q.defer();
        var query = new Parse.Query(Parse.User);
        query.equalTo('email',currentUserEmail);
        query.first({
            success: function(user) {

              if(!user.get("events"))
              {
                  var events = [];
                  events.push(event);
                  user.set("events",events);
              }else
              {
                  var events = user.get("events");
                  events.push(event);
                  user.set("events",events);
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
            error: function(user, error) {
                deferred.reject(error);
            }
        });
        return deferred.promise;
    }

>>>>>>> refs/remotes/origin/master
})
