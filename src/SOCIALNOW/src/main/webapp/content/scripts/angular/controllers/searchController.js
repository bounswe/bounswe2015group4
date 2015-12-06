app.controller('searchController', function ($scope, $routeParams, helperService, searchService, $rootScope) {
    $scope.searchTerm = $routeParams.searchTerm;

    if(!$scope.searchTerm) {
        helperService.goToPage('/');
    } else {
        $scope.searchResponse = searchService.search($scope.searchTerm);
    }
});
