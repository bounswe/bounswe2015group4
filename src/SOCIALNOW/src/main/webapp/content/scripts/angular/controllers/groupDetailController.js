app.controller('groupDetailController', function($scope, groupService, $routeParams, helperService, sessionService, postService) {
    var setInitialProperties = function(groupDetail) {
        $scope.group = groupDetail;

        $scope.notJoined = _.where($scope.group.group_members, { user_token: $scope.user.user_token }).length == 0;
        $scope.isOwner = $scope.group.owner.user_token == $scope.user.user_token;

        $scope.processCompleted = true;
    }

    var getGroupDetails = function(groupId) {
        groupService.getGroupDetails(groupId).then(function(groupDetail) {
            setInitialProperties(groupDetail);
        });
    }

    var init = function() {
        $scope.user = sessionService.getUserInfo();
        $scope.groupId = $routeParams.id;
        $scope.processCompleted = false;

        if(!$scope.groupId) {
            helperService.goToPage('/');
        } else {
            getGroupDetails($scope.groupId);
        }
    }

    $scope.addMember = function() {
        groupService.addMember($scope.user.user_token, $scope.groupId).then(function(group) {
            getGroupDetails($scope.groupId);
        });
    }

    $scope.removeMember = function() {
        groupService.removeMember($scope.user.user_token, $scope.groupId).then(function(group) {
            getGroupDetails($scope.groupId);
        });
    }

    $scope.createPost = function() {
        postService.createPost($scope.newPost, $scope.user.user_token).then(function(post) {
            postService.addPostToGroup(post.id, $scope.groupId).then(function(group) {
                getGroupDetails($scope.groupId);
            })
        })
    }

    init();
});
