app.directive('roles', function() {
    return {
        restrict: 'E',
        templateUrl: './content/scripts/angular/directives/partials/roles.html',
        link: function(scope, element, attrs, ctrl) {
            scope.roles = [ { id: 1, name: 'Teaching Assistant' }, { id: 2, name: 'Student' }, { id: 3, name: 'Instructor' },
                { id: 4, name: 'Alumni' }];

            angular.element(document.getElementsByClassName("select2")).select2();
        }
    }
})
