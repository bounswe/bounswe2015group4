app.directive('logoutButton', function(helperService, sessionService, loginUrl) {
    return {
        restrict: 'A',
        scope: {
            linkButton: '='
        },
        link: function (scope, element) {
            function logOut() {
                sessionService.remove('userInfo');
                helperService.goToPage(loginUrl);
                scope.$apply();
            }

            element.bind('click', logOut);
        }
    };
})

