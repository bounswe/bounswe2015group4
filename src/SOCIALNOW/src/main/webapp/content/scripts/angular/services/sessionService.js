app.service('sessionService', function ($cookies, $rootScope) {
    this.setUserCredentials = function (user) {
        var currentUser = {
            username: user.email,
            email: user.email,
            role: user.role,
            isAuthenticated: true,
            name: user.name,
            surname: user.surname,
            token: user.user_token,
            photoUrl: user.user_photo
        }

        $cookies.putObject("userBaseInfo", currentUser);

        $rootScope.user = currentUser;
    }

    this.updateUserCredentials = function(name, surname, profilePic) {
        var currentUser = this.get('userBaseInfo');
        currentUser.name = name;
        currentUser.surname = surname;
        currentUser.photoUrl = profilePic;

        $cookies.putObject("userBaseInfo", currentUser);

        $rootScope.user = currentUser;
    }

    this.setUserProfileDetails = function (user) {
        var currentUser = this.get("userBaseInfo");

        currentUser.tags = utils.manipulateTags(user.user_tags);
        currentUser.numberOfFollowers = user.numberOfFollowers;
        currentUser.numberOfFollowings = user.numberOfFollowings;
        currentUser.stringTags = currentUser.tags.join();

        _.each(user.user_followers, function(follower) {
            follower.user_tags = utils.trimCharacter(follower.user_tags, ',');
        })

        _.each(user.user_following, function(following) {
            following.user_tags = utils.trimCharacter(following.user_tags, ',');
        })

        $cookies.putObject("userBaseInfo", currentUser);
        $cookies.putObject("interestGroups", user.user_interest_groups || []);
        $cookies.putObject("participatingEvents", utils.manipulateEvents(user.user_participating_events) || []);
        $cookies.putObject("followers", user.user_followers || []);
        $cookies.putObject("followings", user.user_following || []);

        $rootScope.user = currentUser;
    }

    this.isUserAuthenticated = function () {
        var userInfo = $cookies.getObject("userBaseInfo");
        return userInfo && userInfo.isAuthenticated;
    }

    this.addItem = function(cookieObject, item) {
        var items = $cookies.getObject(cookieObject);
        items.push(item);
        $cookies.putObject(cookieObject, items);
    }

    this.removeItem = function(cookieObject, item) {
        var items = $cookies.getObject(cookieObject);
        items.splice(items.indexOf(item), 1);
        $cookies.putObject(cookieObject, items);
    }

    this.get = function(cookieObject) {
        return $cookies.getObject(cookieObject);
    }

    this.getUserInfo = function () {
        var currentUser = $cookies.getObject("userBaseInfo");

        if(!currentUser)
            return currentUser;

        currentUser.interestGroups = $cookies.getObject("interestGroups");
        currentUser.participatingEvents = $cookies.getObject("participatingEvents");
        currentUser.followers = $cookies.getObject("followers");
        currentUser.followings = $cookies.getObject("followings");

        return currentUser;
    }

    this.getUserToken = function() {
        return $cookies.getObject("userBaseInfo").token;
    }

    this.addFollowing = function(user) {
        var userBaseInfo = this.get('userBaseInfo');
        userBaseInfo.numberOfFollowings++;
        $cookies.putObject('userBaseInfo', userBaseInfo);

        this.addItem('followings', user);
    }

    this.removeFollowing = function(user) {
        var userBaseInfo = this.get('userBaseInfo');
        userBaseInfo.numberOfFollowings--;
        $cookies.putObject('userBaseInfo', userBaseInfo);

        this.removeItem('followings', user);
    }

    this.remove = function (key) {
        $cookies.remove(key);
    }

    this.removeAll = function() {
        var cookies = $cookies.getAll();
        angular.forEach(cookies, function (v, k) {
            $cookies.remove(k);
        });
    }
})
