app.service('postService', function($q, $http, baseApiUrl) {

    this.createPost = function(post, token) {
        var deferred = $q.defer();

        post.owner_token = token;

        $http({
            url: baseApiUrl + '/createPost',
            method: 'POST',
            data: JSON.stringify(post)
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
})
