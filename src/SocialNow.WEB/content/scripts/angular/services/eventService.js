app.service('eventService', function ($q, userService) {

    this.createEvent = function(title, description, date) {
        var deferred = $q.defer();
        console.log(title);
        console.log(description);
        console.log(date);
        var givenDate = new Date(date);
        var Event = Parse.Object.extend("Event");
        var event = new Event();
        event.set("title",title);
            event.set("event_description", description);
            event.set("event_date",givenDate);

        event.save(null, {
            success: function (event) {
                deferred.resolve(event);
            },
            error: function (event, error) {
                alert("Error: " + error.code + " " + error.message);
                deferred.reject(error);
            }
        });
        return deferred.promise;
    }

// User service'ten currentuser object get and get the events
    /**
     * uses userservice to get currentUser object,
     * returns the event without any query since userservice did it.
     *
     * @returns {bC.promise|Function|promise|d.promise|*}
     */
    this.getEventsOfCurrentUser = function(){
        var deferred = $q.defer();
            userService.getCurrentUser().then(function(user){
                    var events = user.get("events");
                    deferred.resolve(events);
            },
            function(error){
                alert("Error: " + error.code + " " + error.message);
                deferred.reject(error);
            });
        return deferred.promise;
    }

})
