/*In this file, the role of user is selected.
*
*@param roles
*@return roles
*/

app.service('roleService', ['$q', function($q) {
    var roles = [ { id: 1, name: 'Teaching Assistant' }, { id: 2, name: 'Undergraduate' }, { id: 3, name: 'Faculty' },
        { id: 4, name: 'Alumni' }];

    this.getRoles = function() {
        return roles;
    }

    this.getRoleIds = function() {
        var ids = [];

        angular.forEach(roles, function(role) {
            ids.push(role.id);
        })

        return ids;
    }

    this.getRoleNamesAccordingToIds = function(ids) {
        if(_.isEqual(this.getRoleIds(), ids)) {
            return "all";
        }

        var roleNames = [];

        angular.forEach(roles, function(role) {
            angular.forEach(ids, function(id) {
                if(role.id == id) {
                    roleNames.push(role.name);
                }
            })
        })

        return roleNames.join();
    }
}]);
