app.directive("userSummary", function (helperService) {
    return {
        scope: {
            user: "=data"
        },
        templateUrl: './content/scripts/angular/directives/partials/userSummary.html',
        link: function (scope, element, attributes, modelVal) {
            scope.getUserDetails = function(userToken) {
                helperService.goToPage('/profile/' + userToken);
            }
        }
    };
});
