app.service('sessionService', function($cookies) {
    this.setUserCredentials = function(userEmail, userRole, name, surname, profile_url) {
        $cookies.putObject("userInfo", {
            username: userEmail,
            email: userEmail,
            role: userRole,
            isAuthenticated: true,
            name : name,
            surname: surname,
            profile_url: profile_url
        });

    }

    this.isUserAuthenticated = function() {
        var userInfo = $cookies.getObject("userInfo");
        return userInfo && userInfo.isAuthenticated;
    }

    this.getUserInfo = function(){
      return $cookies.getObject("userInfo");
    }

    this.remove = function(key) {
        $cookies.remove(key);
    }

})
