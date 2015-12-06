app.controller('homeController', function ($scope, userService, sessionService, $location, helperService, recommendationService) {
    var init = function() {
        $scope.user = sessionService.getUserInfo();
        $scope.recommendationsEnabled = false;
        recommendationService.getRecommendations($scope.user.token).then(function(recommendations) {
            $scope.recommendations = recommendations;
            $scope.recommendations.events = utils.manipulateEvents($scope.recommendations.events);
            $scope.recommendationsEnabled = true;
        });
    }

    init();
});
