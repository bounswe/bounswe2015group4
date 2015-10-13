app.service('loginService', function ($q) {
    var deferred = $q.defer();

    this.signup = function(username, password) {
        var user = new Parse.User();
        user.set("username", username);
        user.set("password", password);

        user.signUp(null, {
            success: function (user) {
                alert("User successfully signed up.");
            },
            error: function (user, error) {
                alert("Error: " + error.code + " " + error.message);
            }
        });
    }

    this.getUsers = function() {
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
             }
        });

        return deferred.promise;
    }
})
