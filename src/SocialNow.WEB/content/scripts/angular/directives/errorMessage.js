app.directive('errorMessage', function() {
    return {
        restrict: 'E',
        scope: {
            message: '='
        },
        template: '<span class="text-red margin small" ng-bind="message"></span>',
    }
})
