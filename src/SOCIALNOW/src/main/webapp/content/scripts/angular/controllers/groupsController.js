app.controller('groupsController', function ($scope, sessionService, groupService, userService, helperService) {
    var getAllGroups = function () {
        groupService.getAllGroups().then(function (groups) {
            $scope.allGroups = groups;
        }, function (error) {
            console.log(error);
        });
    }

    var getMyGroups = function () {
        groupService.getMyGroups($scope.user.token).then(function (groups) {
            $scope.myGroups = groups;
        }, function (error) {
            console.log(error);
        });
    }

    var init = function() {
        $scope.groupRoutes = {
            addNew: 1,
            myGroups: 2,
            allGroups: 3
        }

        $scope.currentGroupRoute = $scope.groupRoutes.allGroups;
        $scope.user = sessionService.getUserInfo();

        getAllGroups();
        getMyGroups();
    }

    $scope.createGroup = function () {
        groupService.createGroup($scope.group, $scope.user.token).then(function (group) {
            $scope.errorMessage = "";
            $scope.successMessage = "Group is created successfully"
            $scope.currentGroupRoute = $scope.groupRoutes.allGroups;

            getAllGroups();
            getMyGroups();
        }, function (error) {
            $scope.errorMessage = error;
        });
    }

    init();
});
