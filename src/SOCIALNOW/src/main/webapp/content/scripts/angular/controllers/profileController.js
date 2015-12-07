app.controller('profileController', function ($scope, sessionService, userService, helperService, $routeParams) {
    var userToken = $routeParams.userToken;
    var authenticatedUserToken;

    var init = function() {
        $scope.userEdit = sessionService.getUserInfo();
        authenticatedUserToken = $scope.userEdit.user_token;
        $scope.processCompleted = false;

        if(!userToken || userToken == authenticatedUserToken) {
            $scope.ownProfile = true;
            userService.getProfileDetails($scope.userEdit.user_token).then(function(currentUser) {
                $scope.currentUser = userService.setShowingUserProfile(currentUser);
                $scope.processCompleted = true;
            });
        } else {
            $scope.ownProfile = false;
            userService.getProfileDetails(userToken).then(function(currentUser) {
                $scope.currentUser = userService.setShowingUserProfile(currentUser);
                $scope.follow = _.where($scope.currentUser.followers, { user_token: authenticatedUserToken }).length == 0;
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

        userService.editUser($scope.userEdit).then(function() {
            userService.getProfileDetails($scope.userEdit.user_token).then(function(currentUser) {
                $scope.currentUser = userService.setShowingUserProfile(currentUser);
            });
            $scope.successMessage = 'Your profile is edited successfully';
        }, function(error) {
            $scope.errorMessage = error.message;
        })
    }

    $scope.followUser = function() {
        userService.followUser($scope.userEdit.user_token, userToken).then(function(authenticatedUser) {
            $scope.follow = false;
            $scope.currentUser.numberOfFollowers++;
            if(!$scope.currentUser.followers) {
                $scope.currentUser.followers = [];
            }

            authenticatedUser.user_tags = utils.trimCharacter(authenticatedUser.user_tags, ',');
            $scope.currentUser.followers.push(authenticatedUser);

            userService.getProfileDetails($scope.currentUser.user_token).then(function(currentUser) {
                $scope.currentUser = userService.setShowingUserProfile(currentUser);
            });
            $scope.userEdit = sessionService.getUserInfo();
        }, function (response) {
        })
    }

    $scope.unfollowUser = function() {
        userService.unfollowUser($scope.userEdit.user_token, userToken).then(function(currentUser) {
            $scope.follow = true;

            $scope.currentUser.numberOfFollowers--;
            $scope.currentUser.followers = _.without($scope.currentUser.followers, _.findWhere($scope.currentUser.followers, { user_token: authenticatedUserToken}));

            userService.getProfileDetails($scope.currentUser.user_token).then(function(currentUser) {
                $scope.currentUser = userService.setShowingUserProfile(currentUser);
            });
            $scope.userEdit = sessionService.getUserInfo();
        }, function (response) {
        })
    }

    init();
})
