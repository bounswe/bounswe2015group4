app.directive('linkButton', function(helperService) {
    return {
        restrict: 'A',
        scope: {
            linkButton: '='
        },
        link: function (scope, element) {
            function goToPage() {
                console.log(scope);
                helperService.goToPage(scope.linkButton);
                scope.$apply();
            }

            element.bind('click', goToPage);
        }
    };
})
