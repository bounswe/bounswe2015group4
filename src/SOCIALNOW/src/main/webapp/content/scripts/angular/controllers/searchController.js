app.controller('searchController', function ($scope, $routeParams, helperService, searchService, sessionService) {
    $scope.processCompleted = false;
    $scope.user = sessionService.getUserInfo();

    $scope.searchRoutes = {
        users: 1,
        groups: 2,
        events: 3
    }

    $scope.currentSearchRoute = $scope.searchRoutes.users;

    $scope.searchTerm = $routeParams.searchTerm;

    if(!$scope.searchTerm) {
        helperService.goToPage('/');
    } else {
        searchService.search($scope.searchTerm, $scope.user.user_token).then(function (response) {
            $scope.searchResponse = response;
            $scope.searchResponse.events = utils.manipulateEvents($scope.searchResponse.events);
            $scope.processCompleted = true;
        });
    }
});
