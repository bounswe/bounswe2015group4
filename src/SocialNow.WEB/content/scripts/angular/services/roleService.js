app.service('roleService', ['$q', function($q) {
    var roles = [];

    this.getRoles = function() {
        var deferred = $q.defer();
        if(!roles.length) {
            var roleQuery = Parse.Object.extend("Role");
            var query = new Parse.Query(roleQuery);
            query.find({
                success: function(results) {
                    roles = results;
                    deferred.resolve(roles);
                },
                error: function(error) {
                    console.log("Error: " + error.code + " " + error.message);
                    deferred.reject();
                }
            });
        } else {
            deferred.resolve(roles);
        }

        return deferred.promise;
    }
}]);
