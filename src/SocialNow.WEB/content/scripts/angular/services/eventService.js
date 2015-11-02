app.service('eventService', function ($q, userService) {


    this.getAllEvents = function () {
        var deferred = $q.defer();
        var eventQuery = Parse.Object.extend("Event");
        var query = new Parse.Query(eventQuery);
        query.find({
                success: function (events) {
                    deferred.resolve(events);
                },
                error: function (error) {
                  console.log(error+"getAllEvents")
                    deferred.reject(error);
                }
        });

       return deferred.promise;
    }


    this.createEvent = function (title, description, date, file, location) {
        var deferred = $q.defer();
        var givenDate = new Date(date);
        var Event = Parse.Object.extend("Event");
        var event = new Event();
        if(file) {
            var parseFile = new Parse.File('event_photo', file);
            event.set('event_photo', parseFile);
        }


        event.set("title", title);
        event.set("event_description", description);
        event.set("event_date", givenDate);
        event.set("event_location",location);

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
    this.getEventsOfCurrentUser = function () {
        var deferred = $q.defer();
        userService.getCurrentUser().then(function (user) {
                var events = user.get("events");
                deferred.resolve(events);
            },
            function (error) {
                alert("Error: " + error.code + " " + error.message);
                deferred.reject(error);
            });
        return deferred.promise;
    }

})
