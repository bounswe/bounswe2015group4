var app = angular.module('socialNowApp', ['ngRoute']);

app.constant('roles', {
    Student: 1,
    Instructor: 2,
    TA: 3,
    All: 4
});

app.constant('signUpUrl', '/signup');
app.constant('loginUrl','/login');

app.config(function ($routeProvider, signUpUrl, loginUrl) {
    $routeProvider
        .when('/',
        {
            templateUrl: 'pages/home.html',
            access: {
                loginRequired: true
            }
        }).
        when(loginUrl,{
            templateUrl: 'pages/login.html',
            controller: 'LoginController'

        })
        .when(signUpUrl,
        {
            templateUrl: 'pages/signUp.html',
            controller: 'SignUpController',
            access: {
                redirectIfAuthenticated: '/'
            }
        })
        .otherwise({
            redirectTo: '/signUp'
        })
})


