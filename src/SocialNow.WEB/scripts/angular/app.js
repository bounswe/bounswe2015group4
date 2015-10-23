var app = angular.module('socialNowApp', ['ngRoute']);

app.constant('roles', {
    Student: 1,
    Instructor: 2,
    TA: 3,
    All: 4
});

app.constant('loginUrl', '/login');

app.config(function ($routeProvider, loginUrl, roles) {
    $routeProvider
        .when('/',
        {
            templateUrl: 'pages/home.html',
            access: {
                loginRequired: true
            }
        })
        .when(loginUrl,
        {
            templateUrl: 'pages/login.html',
            controller: 'loginController',
            access: {
                redirectIfAuthenticated: '/'
            }
        })
        .otherwise({
            redirectTo: '/'
        })
})


