app.directive("groupSummary", function (helperService) {
    return {
        scope: {
            interestGroup: "=data"
        },
        templateUrl: './content/scripts/angular/directives/partials/groupSummary.html',
        link: function (scope) {
            scope.getGroupDetails = function(groupId) {
                helperService.goToPage('/groupDetail/' + groupId);
            }
        }
    };
});


