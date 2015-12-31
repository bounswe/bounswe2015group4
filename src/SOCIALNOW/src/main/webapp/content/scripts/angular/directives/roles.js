app.directive('roles', function() {
    return {
        restrict: 'E',
        templateUrl: './content/scripts/angular/directives/partials/roles.html',
        link: function(scope, element, attrs, ctrl) {
            scope.roles = ['Teaching Assistant', 'Student', 'Instructor', 'Alumni', 'All'];
        }
    }
})
