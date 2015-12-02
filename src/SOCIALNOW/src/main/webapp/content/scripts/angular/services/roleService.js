app.service('roleService', ['$q', function($q) {
    var roles = ["Undergradute", "TA", "Instructor", "Alumni"];

    this.getRoles = function() {
        return roles;
    }
}]);
