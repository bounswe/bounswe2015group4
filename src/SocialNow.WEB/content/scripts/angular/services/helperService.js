app.service('helperService', function($location, $log) {
    this.goToPage = function(url) {
        $location.path(url);
    }

    this.consoleError = function(message) {
        $log.error(message);
    }

    this.consoleInfo = function(message) {
        $log.info(message);
    }
})
