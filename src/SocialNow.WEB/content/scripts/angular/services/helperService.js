app.service('helperService', function($location) {
    this.goToPage = function(url) {
        $location.path(url);
    }
})
