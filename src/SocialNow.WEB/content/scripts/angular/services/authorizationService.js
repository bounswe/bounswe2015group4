app.service('authorizationService', function (sessionService) {
    this.authorize = function () {
        var authenticated = sessionService.isUserAuthenticated();

        var result = {
            authenticated: authenticated
        }

        return result;
    };
});

app.run(function ($rootScope, $location, authorizationService, loginUrl) {
    $rootScope.$on('$routeChangeStart', function (event, next) {
        if (next.access) {
            var accessControl = authorizationService.authorize();
            if (next.access.loginRequired && !accessControl.authenticated) {
                $location.path(loginUrl).replace();
            }

            if (accessControl.authenticated && next.access.redirectIfAuthenticated) {
                $location.path(next.access.redirectIfAuthenticated).replace();
            }
        }
    });
});