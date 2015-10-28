var app = angular.module('socialNowApp', ['ngRoute', 'ngCookies']);

app.constant('roles', {
    Student: 1,
    Instructor: 2,
    TA: 3,
    All: 4
});

app.constant('signUpUrl', '/signup');
app.constant('loginUrl','/login');
app.constant('eventsUrl','/events');

app.config(function ($routeProvider, signUpUrl, loginUrl,eventsUrl) {
    $routeProvider
        .when('/',
        {
            templateUrl: 'pages/home.html',
            controller: 'homeController',
            access: {
                loginRequired: true
            }
        })
        .when(loginUrl,{
            templateUrl: 'pages/login.html',
            controller: 'loginController',
            access: {
                redirectIfAuthenticated: '/'
            }
        })
        .when(signUpUrl,
        {
            templateUrl: 'pages/signUp.html',
            controller: 'signUpController'
        }).when(eventsUrl,
        {
            templateUrl: 'pages/events.html',
            controller : 'eventsController'
        })
        .otherwise({
            redirectTo: '/'
        })
})


