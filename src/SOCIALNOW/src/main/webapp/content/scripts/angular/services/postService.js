app.service('postService', function($q, $http, baseApiUrl) {
    /**
    *In the function createPost, post message and token are taken as variable of the function
    *and in a case the function is successful, the function lets users to post a message under 
    *a group or event. For all message posting requirements, this function is called.
    *
    *@param post
    *@param token
    *@return postedMessage
    */
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
    /**
    *In the function addPostToEvent, postId and eventId are taken as variable of the function
    *and it aims to attach the created post in the database to an event by considering event's
    *eventId.
    *
    *@param postId
    *@param eventId
    *@return eventPost
    */
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
    /**
    *In the function addPostToGroup, postId and groupId are taken as variable of the function
    *and it aims to attach the created post in the database to a group by considering group's
    *groupId.
    *
    *@param postId
    *@param groupId
    *@return groupPost
    */
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
    /**
    *In the function addComment, ownerToken and comment are taken as variable of the function
    *and in a case the function is successful, the function lets users to create a comment. 
    *To comment under any post in event or group, this function is called.
    *
    *@param comment
    *@param ownerToken
    *@return createdComment
    */
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
    /**
    *In the function addCommentToPost, postId and commentId are taken as variable of the function
    *and it aims to attach the created comment in the database to a post by considering post's
    *postId.
    *
    *@param postId
    *@param commentId
    *@return commentForPost
    */
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
