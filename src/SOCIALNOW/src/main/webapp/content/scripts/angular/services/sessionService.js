app.service('sessionService', function ($cookies, $rootScope) {
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

    this.isUserAuthenticated = function () {
        var userInfo = $cookies.getObject("userBaseInfo");
        return userInfo && userInfo.isAuthenticated;
    }

    this.getUserInfo = function () {
        return $cookies.getObject("userBaseInfo");

        /*var currentUser = $cookies.getObject("userBaseInfo");
        currentUser.interestGroups = $cookies.getObject("interestGroups");
        currentUser.participatingEvents = $cookies.getObject("participatingEvents");
        currentUser.followers = $cookies.getObject("followers");
        currentUser.followings = $cookies.getObject("followings");

        return currentUser;*/
    }

    this.getRoles = function() {
        return ['Teaching Assistant', 'Student', 'Instructor', 'Alumni'];
    }

    this.removeAll = function() {
        var cookies = $cookies.getAll();
        angular.forEach(cookies, function (v, k) {
            $cookies.remove(k);
        });
    }
})
