app.service('helperService', function($location, $log, $route) {
    this.goToPage = function(url) {
        $location.path(url);
    }

    this.consoleError = function(message) {
        $log.error(message);
    }

    this.consoleInfo = function(message) {
        $log.info(message);
    }

    this.reload = function(){
        $route.reload();
    }
})
