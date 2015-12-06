app.directive("eventSummary", function (helperService) {
    return {
        scope: {
            event: "=data"
        },
        templateUrl: './content/scripts/angular/directives/partials/eventSummary.html',
        link: function (scope, element, attributes, modelVal) {
            scope.getEventDetails = function(eventId) {
                helperService.goToPage('/eventDetail/' + eventId);
            }
        }
    };
});

