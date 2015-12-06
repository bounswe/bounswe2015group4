app.service('groupService', function ($q, $http, sessionService, baseApiUrl) {
    this.getAllGroups = function () {
        var deferred = $q.defer();

        $http({
            url: baseApiUrl + '/listAllGroups',
            method: 'POST',
            data: JSON.stringify({})
        }).success(function (groups) {
            deferred.resolve(groups);
        }).error(function (response) {
            deferred.reject('An error occurred!');
        })

        return deferred.promise;
    }

    this.getMyGroups = function (userToken) {
        var deferred = $q.defer();

        var request = {
            user_token: userToken
        }

        $http({
            url: baseApiUrl + '/listMyGroups',
            method: 'POST',
            data: JSON.stringify(request)
        }).success(function (groups) {
            deferred.resolve(groups);
        }).error(function (response) {
            deferred.reject('An error occurred!');
        })

        return deferred.promise;
    }

    this.getGroupDetails = function(groupId) {
        var deferred = $q.defer();

        var request = {
            interest_group_id: groupId
        }

        $http({
            url: baseApiUrl + '/groups/showGroupDetail',
            method: 'POST',
            data: JSON.stringify(request)
        }).success(function (groupDetail) {
            deferred.resolve(groupDetail);
        }).error(function (response) {
            deferred.reject('An error occurred!');
        })

        return deferred.promise;
    }

    this.createGroup = function (group, userToken) {
        var deferred = $q.defer();

        group.owner_token = userToken;
        $http({
            url: baseApiUrl + '/createInterestGroup',
            method: 'POST',
            data: JSON.stringify(group)
        }).success(function (group) {
            deferred.resolve(group);
        }).error(function (response) {
            deferred.reject('An error occurred!');
        })

        return deferred.promise;
    }

    this.addMember = function (userToken, groupId) {
        var deferred = $q.defer();

        var request = {
            user_token: userToken,
            interest_group_id : groupId
        }

        $http({
            url: baseApiUrl + '/groups/addMember',
            method: 'POST',
            data: JSON.stringify(request)
        }).success(function (group) {
            deferred.resolve(group);
        }).error(function (response) {
            deferred.reject('An error occurred!');
        })

        return deferred.promise;
    }

    this.removeMember = function (userToken, groupId) {
        var deferred = $q.defer();

        var request = {
            user_token: userToken,
            interest_group_id : groupId
        }

        $http({
            url: baseApiUrl + '/groups/removeMember',
            method: 'POST',
            data: JSON.stringify(request)
        }).success(function (group) {
            deferred.resolve(group);
        }).error(function (response) {
            deferred.reject('An error occurred!');
        })

        return deferred.promise;
    }
})

