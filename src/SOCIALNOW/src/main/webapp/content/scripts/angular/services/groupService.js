app.service('groupService', function ($q, $http, sessionService, baseApiUrl) {
    /**
    *In this function, the token is taken as variable of the function
    *and in a case the function is successful, all groups are listed.
    *
    *@param token
    *@return allGroups
    */
    this.getAllGroups = function (token) {
        var deferred = $q.defer();

        var request = {
            user_token: token
        }

        $http({
            url: baseApiUrl + '/listAllGroups',
            method: 'POST',
            data: JSON.stringify(request)
        }).success(function (groups) {
            deferred.resolve(groups);
        }).error(function (response) {
            deferred.reject('An error occurred!');
        })

        return deferred.promise;
    }
    /**
    *In the function getMyGroups, the userToken is taken as variable into the function
    *and in a case the function is successful, the function lets users to see all his/her groups.
    *
    *@param userToken
    *@return userGroups
    */
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
    /**
    *In the function getGroupDetails, the groupId is taken as variable into the function
    *and by checking groupId, function reaches which group has been mentioned and returns 
    *the details of that group
    *
    *@param groupId
    *@return groupDetail
    */
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
    /**
    *In the function createGroup, the userToken and group object are taken as variable of the function
    *and in a case the function is successful, the function lets users to create a new interest group.
    *
    *@param userToken
    *@param group
    *@return createdGroup
    */
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
    /**
    *In the function addMember, the userToken and groupId are taken as variable of the function
    *and in a case the function is successful, the function lets users to join and interest group.
    *
    *@param userToken
    *@param groupId
    *@return joinedGroup
    */
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
    /**
    *In the function removeMember, the userToken and groupId are taken as variable of the function
    *and in a case the function is successful, the function lets users to leave an interest group.
    *
    *@param userToken
    *@param groupId
    *@return removedGroup
    */
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

