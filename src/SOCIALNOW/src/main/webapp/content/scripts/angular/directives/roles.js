app.directive('roles', function(roleService) {
    return {
        restrict: 'E',
        scope: {
            selectedRoles: '=selected'
        },
        templateUrl: './content/scripts/angular/directives/partials/roles.html',
        link: function(scope, element, attrs, ctrl) {
            scope.selectedRoles = [];

            scope.roles = roleService.getRoles();
            
            angular.element(document.getElementsByClassName("select2")).select2();
        }
    }
})
