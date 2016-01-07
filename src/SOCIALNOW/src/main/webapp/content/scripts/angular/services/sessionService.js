app.service('sessionService', function ($cookies, $rootScope) {
        /**
        *In the function setUserCredentials, user is taken as variable into the function,
        *the info the current user types are kept into the variables username, email and so on 
        *respectively. The function is called durng signUp process.
        *
        *@return createdUser
        */
    this.setUserCredentials = function (user) {
        var currentUser = {
            username: user.email,
            email: user.email,
            role: user.role,
            isAuthenticated: true,
            name: user.name,
            surname: user.surname,
            user_token: user.user_token,
            photoUrl: user.user_photo
        }

        $cookies.putObject("userBaseInfo", currentUser);

        $rootScope.user = currentUser;
    }
    /**
    *In the function isUserAuthenticated, the user is checked if he/she is authenticated or not.
    *
    *@return authenticatedUser
    */
    this.isUserAuthenticated = function () {
        var userInfo = $cookies.getObject("userBaseInfo");
        return userInfo && userInfo.isAuthenticated;
    }
    /**
    *In the function getUserInfo, the info defined in the system is returned
    *
    *@return userInfo
    */
    this.getUserInfo = function () {
        return $cookies.getObject("userBaseInfo");

        /*var currentUser = $cookies.getObject("userBaseInfo");
        currentUser.interestGroups = $cookies.getObject("interestGroups");
        currentUser.participatingEvents = $cookies.getObject("participatingEvents");
        currentUser.followers = $cookies.getObject("followers");
        currentUser.followings = $cookies.getObject("followings");

        return currentUser;*/
    }
    /**
    *In the function removeAll, all the info and everything stated in the cookie is deleted
    *and cleared.
    *
    *@return removedAll
    */
    this.removeAll = function() {
        var cookies = $cookies.getAll();
        angular.forEach(cookies, function (v, k) {
            $cookies.remove(k);
        });
    }
})
