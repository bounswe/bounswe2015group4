app.directive('access', function (authorizationService) {
    return {
        restrict: 'A',
        scope: {
            access: '='
        },
        link: function (scope, element) {
            function makeVisible() {
                element.removeClass('hidden');
            }

            function makeHidden() {
                element.addClass('hidden');
            }

            function determineVisibility() {
                var result = authorizationService.authorize(true, scope.access);
                if (result.authorizated) {
                    makeVisible();
                } else {
                    makeHidden();
                }
            }

            if (scope.access) {
                determineVisibility();
            }
        }
    };
});
