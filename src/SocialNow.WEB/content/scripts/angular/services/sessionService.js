app.service('sessionService', function(roles, $cookies) {
    this.setUserCredentials = function(userEmail, userRole) {
        $cookies.putObject("userInfo", { email: userEmail, role: userRole, isAuthenticated: true });
    }

    this.isUserAuthenticated = function() {
        var userInfo = $cookies.getObject("userInfo");
        return userInfo && userInfo.isAuthenticated;
    }

    this.remove = function(key) {
        $cookies.remove(key);
    }
})
