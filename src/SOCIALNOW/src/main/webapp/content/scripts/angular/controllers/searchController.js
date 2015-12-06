app.controller('searchController', function ($scope, $routeParams, helperService, searchService, $rootScope) {
    $scope.processCompleted = false;

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
        searchService.search($scope.searchTerm).then(function (response) {
            $scope.searchResponse = response;
            $scope.searchResponse.events = utils.manipulateEvents($scope.searchResponse.events);
            $scope.processCompleted = true;
        });
    }
});
