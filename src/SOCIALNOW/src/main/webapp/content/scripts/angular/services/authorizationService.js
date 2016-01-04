/*
*In this file, the authorization of users is checked if they are 
*eligible to access the page they are asking to go or not. If
*they are authorized, they are directed to the location where 
*they try to, otherwise they are directed to the login page.
*
*@param authenticated
*@param result
*@return result
*/

app.service('authorizationService', function (sessionService) {
    this.authorize = function () {
        var authenticated = sessionService.isUserAuthenticated();

        var result = {
            authenticated: authenticated
        }

        return result;
    };
});

app.run(function ($rootScope, $location, authorizationService, loginUrl, sessionService) {
    $rootScope.user = sessionService.getUserInfo();

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