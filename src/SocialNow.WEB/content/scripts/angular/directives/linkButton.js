app.directive('linkButton', function($location) {
    return {
        restrict: 'A',
        scope: {
            linkButton: '='
        },
        link: function (scope, element) {
            function goToPage() {
                $location.path(scope.linkButton).replace();
                scope.$apply();
            }

            element.bind('click', goToPage);
        }
    };
})
