app.service('authorizationService', function (sessionService) {
    this.authorize = function (loginRequired, allowedRoles) {
        var authenticated = sessionService.userInfo.isAuthenticated;
        var authorizated = !allowedRoles || _.contains(allowedRoles, sessionService.userInfo.role);

        var result = {
            authenticated: authenticated,
            authorizated: authorizated
        }

        return result;
    };
});

app.run(function ($rootScope, $location, authorizationService, loginUrl) {
    $rootScope.$on('$routeChangeStart', function (event, next) {
        if (next.access) {
            var accessControl = authorizationService.authorize(next.access.loginRequired, next.access.allowedRoles);
            if (!accessControl.authenticated) {
                $location.path(loginUrl).replace();
            }

            if(!accessControl.authorizated) {
                event.preventDefault();
            }

            if (accessControl.authenticated && next.access.redirectIfAuthenticated) {
                $location.path(next.access.redirectIfAuthenticated).replace();
            }
        }
    });
});