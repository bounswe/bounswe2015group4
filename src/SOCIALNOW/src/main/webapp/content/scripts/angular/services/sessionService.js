app.service('sessionService', function($cookies, $rootScope) {
    this.setUserCredentials = function(user) {
        var currentUser = {
            username: user.email,
            email: user.email,
            role: user.role,
            isAuthenticated: true,
            name : user.name,
            surname: user.surname,
            token: user.user_token
        }

        $cookies.putObject("userInfo", currentUser);

        $rootScope.user = currentUser;
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
