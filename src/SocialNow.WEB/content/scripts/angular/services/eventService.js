app.service('eventService', function ($q) {

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
                alert("event successfully saved");
                deferred.resolve(event);
            },
            error: function (event, error) {
                alert("Error: " + error.code + " " + error.message);
                deferred.reject(error);
            }
        });
        return deferred.promise;
    }

})
