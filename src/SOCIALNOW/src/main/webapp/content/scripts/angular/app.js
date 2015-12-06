var app = angular.module('socialNowApp', ['ngRoute', 'ngCookies']);

app.constant('baseApiUrl', 'http://ec2-52-11-176-49.us-west-2.compute.amazonaws.com:8080/social_backend');

app.constant('signUpUrl', '/signup');
app.constant('loginUrl', '/login');
app.constant('eventsUrl', '/events');

app.config(function ($routeProvider, signUpUrl, loginUrl, eventsUrl) {
    $routeProvider
        .when('/',
        {
            templateUrl: 'pages/home.html',
            controller: 'homeController',
            access: {
                loginRequired: true
            }
        })
        .when(loginUrl, {
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
        })
        .when('/forgot-password', {
            templateUrl: 'pages/forgotPassword.html',
            controller: 'forgotPasswordController'
        })
        .when(eventsUrl,
        {
            templateUrl: 'pages/events.html',
            controller: 'eventsController',
            access: {
                loginRequired: true
            }
        })
        .when('/eventDetail',
        {
            templateUrl: 'pages/eventDetail.html',
            access: {
                loginRequired: true
            }
        })
        .when('/profile',
        {
            templateUrl: 'pages/profile.html',
            controller: 'profileController',
            access: {
                loginRequired: true
            }
        })
        .otherwise({
            redirectTo: '/'
        })
})


