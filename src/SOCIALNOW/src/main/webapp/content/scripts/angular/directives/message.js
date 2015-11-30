app.directive('message', function() {
    return {
        restrict: 'E',
        scope: {
            display: '=',
            type: '@'
        },
        template: '<span class="margin small" ng-bind="display"></span>',
        link: function(scope, element, attrs, ctrl) {
            if(scope.type === 'error') {
                element.addClass('text-red');
            } else if(scope.type === 'success') {
                element.addClass('text-green');
            } else {
                element.addClass('text-gray')
            }
        }
    }
})
