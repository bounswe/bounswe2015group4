app.controller('eventDetailController', function($scope, eventService, $routeParams, helperService, sessionService, postService) {
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

    $scope.createPost = function() {
        postService.createPost($scope.newPost, $scope.user.user_token).then(function(post) {
            postService.addPostToEvent(post.id, $scope.eventId).then(function(event) {
                getEventDetails($scope.eventId);
            })
        })
    }

    $scope.createComment = function(postId) {
        postService.addComment($scope.user.user_token, newComment). then(function(comment) {
            postService.addCommentToPost(postId, comment.id).then(function(post) {
                getGroupDetails($scope.eventId);
            })
        })
    }

    init();
})
