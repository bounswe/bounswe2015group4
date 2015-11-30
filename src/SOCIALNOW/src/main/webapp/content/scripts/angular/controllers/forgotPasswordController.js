app.controller('forgotPasswordController', ['$scope', 'userService', '$timeout', 'helperService', function($scope, userService, $timeout, helperService) {
    $scope.forgotPassword = function(isValid) {
        if(!isValid) {
            $scope.submitted = true;
            return;
        }

        userService.passwordReset($scope.currentUser.Email).then(function(message) {
            $scope.errorMessage = '';
            $scope.successMessage = message;
            $timeout(function() {
                helperService.goToPage('/login');
            }, 3000);
        }, function(message) {
            $scope.errorMessage = message;
        });
    }
}])
