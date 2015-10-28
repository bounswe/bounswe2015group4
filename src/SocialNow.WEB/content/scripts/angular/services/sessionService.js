app.service('sessionService', function(roles, $cookies) {
    this.setUserCredentials = function(userEmail, userRole, name, surname) {
        $cookies.putObject("userInfo", {
            username: userEmail,
            email: userEmail,
            role: userRole,
            isAuthenticated: true,
            name : name,
            surname: surname
        });
    }

    this.isUserAuthenticated = function() {
        var userInfo = $cookies.getObject("userInfo");
        return userInfo && userInfo.isAuthenticated;
    }

    this.getUserInfo= function(){
      return $cookies.getObject("userInfo");
    }

    this.remove = function(key) {
        $cookies.remove(key);
    }
})
