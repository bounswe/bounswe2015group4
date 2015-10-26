app.service('roleService', ['$q', function($q) {
    this.getRoles = function() {
        var deferred = $q.defer();
        var roleQuery = Parse.Object.extend("Role");
        var query = new Parse.Query(roleQuery);
        var roles = [];
        query.find({
            success: function(results) {
                deferred.resolve(results);
            },
            error: function(error) {
                console.log("Error: " + error.code + " " + error.message);
                deferred.reject();
            }
        });

        return deferred.promise;
    }
}]);
