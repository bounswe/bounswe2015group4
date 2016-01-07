app.directive('multiselect', function(roleService) {
    return {
        restrict: 'E',
        scope: {
            selectedItems: '=selected',
            items: '='
        },
        templateUrl: './content/scripts/angular/directives/partials/multiselect.html',
        link: function(scope, element, attrs, ctrl) {
            scope.selectedItems = [];

            angular.element(document.getElementsByClassName("select2")).select2();
        }
    }
})

