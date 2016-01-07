app.controller('eventDetailController', function($scope, eventService, $routeParams, helperService, sessionService, postService, groupService, userService) {
    $scope.users = [];
    $scope.groups = [];

    $scope.goUserProfile = function(token) {
        helperService.goToPage('/profile/' + token);
    }

    var setInitialProperties = function(eventDetail) {
        $scope.event = eventDetail;

        $scope.notGoing = _.where($scope.event.event_participants, { user_token: $scope.user.user_token }).length == 0;

        $scope.processCompleted = true;
    }

    var getEventDetails = function(eventId) {
        eventService.getEventDetails(eventId).then(function(eventDetail) {
            setInitialProperties(eventDetail);
        });
    }

    var init = function() {
        $scope.user = sessionService.getUserInfo();
        $scope.eventId = $routeParams.id;
        $scope.processCompleted = false;

        if(!$scope.eventId) {
            helperService.goToPage('/');
        } else {
            getEventDetails($scope.eventId);
        }
    }

    $scope.makeGoing = function() {
        eventService.makeGoing($scope.user.user_token, $scope.eventId).then(function(event) {
            getEventDetails($scope.eventId);
        });
    }

    $scope.makeNotgoing = function() {
        eventService.makeNotgoing($scope.user.user_token, $scope.eventId).then(function(event) {
            getEventDetails($scope.eventId);
        });
    }

    $scope.shareEvent = function(type) {
        if(type == 1) {
            if($scope.groups.length == 0) {
                groupService.getAllGroups($scope.user.user_token).then(function(groups) {
                    angular.forEach(groups, function(group) {
                        $scope.groups.push({
                            id: group.id,
                            name: group.group_name
                        });
                    });
                });

                angular.element(document.getElementById("shareGroupModal")).modal('show');
            }
        } else if(type == 2) {
            if($scope.users.length == 0) {
                userService.getAllUsers($scope.user.user_token).then(function(users) {
                    users = _.reject(users, function(user) {
                        return user.user_token == $scope.user.user_token;
                    });

                    angular.forEach(users, function(user) {
                        $scope.users.push({
                            id: user.user_token,
                            name: user.name + " " + user.surname
                        });
                    });
                });

                angular.element(document.getElementById("shareUserModal")).modal('show');
            }
        }
    }

    $scope.shareWithGroups = function()  {
        try {
            angular.forEach($scope.selectedGroups, function(group) {
                eventService.shareEventWithAGroup($scope.user.user_token, group, $scope.eventId).then(function() {});
            })

            angular.element(document.getElementById("shareGroupModal")).modal('hide');
            alert("Successfully shared");
        } catch(e) {
            console.log(e);
            angular.element(document.getElementById("shareGroupModal")).modal('hide');
            alert("An error occurred while sharing!");
        }
    }

    $scope.shareWithUsers = function()  {
        try {
            angular.forEach($scope.selectedUsers, function(user) {
                eventService.shareEvent($scope.user.user_token, user, $scope.eventId).then(function() {});
            })

            angular.element(document.getElementById("shareUserModal")).modal('hide');
            alert("Successfully shared");
        } catch(e) {
            console.log(e);
            angular.element(document.getElementById("shareUserModal")).modal('hide');
            alert("An error occurred while sharing!");
        }
    }

    $scope.createPost = function() {
        postService.createPost($scope.newPost, $scope.user.user_token).then(function(post) {
            postService.addPostToEvent(post.id, $scope.eventId).then(function(event) {
                getEventDetails($scope.eventId);
                $scope.newPost = {};
            })
        })
    }

    $scope.createComment = function(post) {
        postService.addComment($scope.user.user_token, post.comment). then(function(comment) {
            postService.addCommentToPost(post.id, comment.id).then(function(post) {
                getEventDetails($scope.eventId);
            })
        })
    }

    init();
})
