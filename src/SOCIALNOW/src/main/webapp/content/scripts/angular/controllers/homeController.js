app.controller('homeController', function ($scope, userService, sessionService, $location, helperService, recommendationService, timelineService) {
    $scope.recommendationRoutes = {
        events: 1,
        groups: 2,
        users: 3
    }

    $scope.goPage = function(url, id) {
        helperService.goToPage(url + id);
    }

    $scope.currentRecommendationRoute = $scope.recommendationRoutes.events;

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

            angular.forEach($scope.timeline.user_groups, function(userGroup) {
                if(userGroup.user.user_token != userGroup.group.owner_token) {
                    userGroup.phrase = 'joined';
                } else {
                    userGroup.phrase = 'created';
                }
            })

            angular.forEach($scope.timeline.user_events, function(userEvent) {
                if(userEvent.user.user_token != userEvent.event.event_host_token) {
                    userEvent.phrase = 'joined';
                } else {
                    userEvent.phrase = 'created';
                }
            })
        });
    }

    init();
});
