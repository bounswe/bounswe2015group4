/*In this file, the role of user is selected.
*
*@param roles
*@return roles
*/

app.service('roleService', ['$q', function($q) {
    var roles = ["Undergraduate", "TA", "Instructor", "Alumni"];

    this.getRoles = function() {
        return roles;
    }
}]);
