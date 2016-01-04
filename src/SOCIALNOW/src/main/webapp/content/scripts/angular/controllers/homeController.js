app.controller('homeController', function ($scope, userService, sessionService, $location, helperService, recommendationService, timelineService) {
    var init = function() {
        $scope.user = sessionService.getUserInfo();
        $scope.recommendationsEnabled = false;
        recommendationService.getRecommendations($scope.user.user_token).then(function(recommendations) {
            $scope.recommendations = recommendations;
            $scope.recommendations.events = utils.manipulateEvents($scope.recommendations.events);
            $scope.recommendationsEnabled = true;
        });

        timelineService.getTimeline($scope.user.user_token).then(function(timeline) {
           $scope.timeline = timeline;
        });
    }

    init();
});
