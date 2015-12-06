app.controller('profileController', function ($scope, sessionService, userService, helperService, $routeParams) {
    var userToken = $routeParams.userToken;
    var authenticatedUserToken;

    var getTags = function(tags) {
        var tagsModified = [];

        for(var i=0; i<(tags || []).length; i++) {
            tagsModified.push({
                tag1: tags[i++],
                tag2: tags[i++],
                tag3: tags[i]
            })
        }

        return tagsModified;
    }

    var init = function() {
        $scope.userEdit = sessionService.getUserInfo();
        authenticatedUserToken = $scope.userEdit.token;
        $scope.processCompleted = false;

        if(!userToken || userToken == authenticatedUserToken) {
            $scope.ownProfile = true;
            userService.updateProfileDetails($scope.userEdit.token).then(function(currentUser) {
                $scope.currentUser = currentUser;
                $scope.currentUser.tagsModified = getTags($scope.currentUser.tags);
                $scope.processCompleted = true;
            });
        } else {
            $scope.ownProfile = false;
            userService.getProfileDetails(userToken).then(function(currentUser) {
                $scope.currentUser = userService.setShowingUserProfile(currentUser);
                $scope.follow = _.where($scope.currentUser.followers, { user_token: authenticatedUserToken }).length == 0;
                $scope.currentUser.tagsModified = getTags($scope.currentUser.tags);
                $scope.processCompleted = true;
            }, function(error) {
                helperService.goToPage('/');
            });
        }

        $scope.profileRoutes = {
            activity: 1,
            edit: 2,
            followers: 3,
            followings: 4
        }

        $scope.currentProfileRoute = $scope.profileRoutes.activity;
    }

    $scope.editProfile = function (isValid) {
        if (!isValid) {
            $scope.submitted = true;
            return;
        }

        userService.editUser($scope.userEdit).then(function(currentUser) {
            sessionService.updateUserCredentials(currentUser.name, currentUser.surname, currentUser.user_photo);
            $scope.successMessage = 'Your profile is edited successfully';
            $scope.currentUser = sessionService.getUserInfo();
            $scope.currentUser.tagsModified = getTags($scope.currentUser.tags);
        }, function(error) {
            $scope.errorMessage = error.message;
        })
    }

    $scope.followUser = function() {
        userService.followUser($scope.userEdit.token, userToken).then(function(authenticatedUser) {
            $scope.follow = false;
            $scope.currentUser.numberOfFollowers++;
            if(!$scope.currentUser.followers) {
                $scope.currentUser.followers = [];
            }

            authenticatedUser.user_tags = utils.trimCharacter(authenticatedUser.user_tags, ',');
            $scope.currentUser.followers.push(authenticatedUser);

            userService.updateProfileDetails($scope.userEdit.token);
            $scope.userEdit = sessionService.getUserInfo();
        }, function (response) {
        })
    }

    $scope.unfollowUser = function() {
        userService.unfollowUser($scope.userEdit.token, userToken).then(function(currentUser) {
            $scope.follow = true;

            $scope.currentUser.numberOfFollowers--;
            $scope.currentUser.followers = _.without($scope.currentUser.followers, _.findWhere($scope.currentUser.followers, { user_token: authenticatedUserToken}));

            userService.updateProfileDetails($scope.userEdit.token);
            $scope.userEdit = sessionService.getUserInfo();
        }, function (response) {
        })
    }

    init();
})
