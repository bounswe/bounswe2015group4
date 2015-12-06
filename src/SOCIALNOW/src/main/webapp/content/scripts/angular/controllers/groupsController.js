app.controller('groupsController', function ($scope, sessionService, groupService, userService, helperService) {
    $scope.groupRoutes = {
        addNew: 1,
        myGroups: 2,
        allGroups: 3
    }

    $scope.currentGroupRoute = $scope.groupRoutes.addNew;
    $scope.user = sessionService.getUserInfo();

    $scope.createGroup = function () {
        groupService.createGroup($scope.group, $scope.user.token).then(function (group) {
            sessionService.addItems("interestGroups", group);
            helperService.goToPage('/profile');
        }, function (error) {
            $scope.errorMessage = error;
        });
    }
});
