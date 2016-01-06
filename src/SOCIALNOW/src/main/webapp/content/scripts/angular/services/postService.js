app.service('postService', function($q, $http, baseApiUrl) {

    this.createPost = function(post, token) {
        var deferred = $q.defer();

        var request = {
            content: post.content,
            owner_token: token,
            post_comments: ''
        }

        $http({
            url: baseApiUrl + '/createPost',
            method: 'POST',
            data: JSON.stringify(request)
        }).success(function(post) {
            deferred.resolve(post);
        }).error(function(response) {
            deferred.reject('An error occurred!');
        })

        return deferred.promise;
    }

    this.addPostToEvent = function(postId, eventId) {
        var deferred = $q.defer();

        var request = {
            event_id : eventId,
            post_id: postId
        }

        $http({
            url: baseApiUrl + '/events/addPost',
            method: 'POST',
            data: JSON.stringify(request)
        }).success(function(event) {
            deferred.resolve(event);
        }).error(function(response) {
            deferred.reject('An error occurred!');
        })

        return deferred.promise;
    }

    this.addPostToGroup = function(postId, groupId) {
        var deferred = $q.defer();

        var request = {
            interest_group_id : groupId,
            post_id: postId
        }

        $http({
            url: baseApiUrl + '/groups/addPost',
            method: 'POST',
            data: JSON.stringify(request)
        }).success(function(group) {
            deferred.resolve(group);
        }).error(function(response) {
            deferred.reject('An error occurred!');
        })

        return deferred.promise;
    }

    this.addComment = function(ownerToken, comment) {
        var deferred = $q.defer();

        var request = {
            owner_token: ownerToken,
            comment_text: comment
        }

        $http({
            url: baseApiUrl + '/createComment',
            method: 'POST',
            data: JSON.stringify(request)
        }).success(function(comment) {
            deferred.resolve(comment);
        }).error(function(response) {
            deferred.reject('An error occurred!');
        })

        return deferred.promise;
    }

    this.addCommentToPost = function(postId, commentId) {
        var deferred = $q.defer();

        var request = {
            post_id: postId,
            comment_id: commentId
        }

        $http({
            url: baseApiUrl + '/post/addComment ',
            method: 'POST',
            data: JSON.stringify(request)
        }).success(function(post) {
            deferred.resolve(post);
        }).error(function(response) {
            deferred.reject('An error occurred!');
        })

        return deferred.promise;
    }
})
