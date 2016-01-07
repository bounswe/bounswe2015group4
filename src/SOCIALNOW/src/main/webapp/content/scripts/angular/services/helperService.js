app.service('helperService', function($location, $log, $route) {
    /**
    *In the function goToPage, url is taken as variable of the function
    *and in a case the function is successful, users are succesfuly directed to 
    *pages defined in the url.
    *
    *@param url
    *@return page
    */
    this.goToPage = function(url) {
        $location.path(url);
    }
    /**
    *In the function consoleError, a message is taken as variable of the function
    *and in a case the function is successful, the function writes an error message
    *to the console.
    *
    *@param message
    *@return errorMessage
    */
    this.consoleError = function(message) {
        $log.error(message);
    }
    /**
    *In the function consoleInfo, a message is taken as variable of the function
    *and in a case the function is successful, the function writes an info message
    *to the console.
    *
    *@param message
    *@return infoMessage
    */
    this.consoleInfo = function(message) {
        $log.info(message);
    }
    /**
    *In the function reload, the page user currently using is refreshed.
    *
    *@return refreshedPage
    */
    this.reload = function(){
        $route.reload();
    }
})
