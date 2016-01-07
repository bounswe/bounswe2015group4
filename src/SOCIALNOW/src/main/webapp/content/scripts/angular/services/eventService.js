app.service('eventService', function ($q, $http, sessionService, baseApiUrl) {
    /**
    *In this function, the userToken is taken as variable of the function
    *and in a case the function is successful, all events are listed.
    *
    *@param userToken
    *@return allEvents
    */
    this.getAllEvents = function (userToken) {
        var deferred = $q.defer();
        var request = {
            user_token: userToken
        }

        $http({
            url: baseApiUrl + '/listAllEvents',
            data: JSON.stringify(request) ,
            method: 'POST'
        }).success(function (events) {
            events = utils.manipulateEvents(events);

            deferred.resolve(events);
        }).error(function (response) {
            deferred.reject('An error occurred!');
        })

        return deferred.promise;
    }
    /**
    *In the function createEvent, the userToken and event object are taken as variable of the function
    *and in a case the function is successful, the function lets users to create event.
    *
    *@param userToken
    *@param event
    *@return createdEvent
    */
    this.createEvent = function (event, userToken) {
        var deferred = $q.defer();

        event.event_host_token = userToken;
        $http({
            url: baseApiUrl + '/createEvent',
            method: 'POST',
            data: JSON.stringify(event)
        }).success(function (events) {
            deferred.resolve(events);
        }).error(function (response) {
            deferred.reject('An error occurred!');
        })

        return deferred.promise;
    }

    /**
    *In the function getMyEvents, the userToken is taken as variable into the function
    *and in a case the function is successful, the function lets users to see all his/her events.
    *
    *@param userToken
    *@return userEvents
    */
    this.getMyEvents = function (userToken) {
        var deferred = $q.defer();

        var request = {
            event_host_token: userToken
        }

        $http({
            url: baseApiUrl + '/listMyEvents',
            method: 'POST',
            data: JSON.stringify(request)
        }).success(function (events) {
            events = utils.manipulateEvents(events);

            deferred.resolve(events);
        }).error(function (response) {
            deferred.reject('An error occurred!');
        })

        return deferred.promise;
    }
    /**
    *In the function getEventDetails, the eventId is taken as variable into the function
    *and by checking eventId, function reaches which event has been mentioned and returns 
    *the details of that event
    *
    *@param eventId
    *@return eventDetail
    */

    this.getEventDetails = function(eventId) {
        var deferred = $q.defer();

        var request = {
            event_id: eventId
        }

        $http({
            url: baseApiUrl + '/events/getEventDetail',
            method: 'POST',
            data: JSON.stringify(request)
        }).success(function (eventDetail) {
            eventDetail.event_start_date = utils.convertTimestampToDate(eventDetail.event_start_date);
            eventDetail.event_end_date = utils.convertTimestampToDate(eventDetail.event_end_date);

            deferred.resolve(eventDetail);
        }).error(function (response) {
            deferred.reject('An error occurred!');
        })

        return deferred.promise;
    }
    /**
    *In the function makeGoing, the userToken and eventId are taken as variable into the function
    *and by matching eventId and userToken, function changes users' status as attendee of the event.
    *
    *@param eventId
    *@param userToken
    *@return madeGoing
    */

    this.makeGoing = function(userToken, eventId) {
        var deferred = $q.defer();

        var request = {
            user_token: userToken,
            event_id: eventId
        }

        $http({
            url: baseApiUrl + '/events/addParticipant',
            method: 'POST',
            data: JSON.stringify(request)
        }).success(function (event) {
            deferred.resolve(event);
        }).error(function (response) {
            deferred.reject('An error occurred!');
        })

        return deferred.promise;
    }
    /**
    *In the function makeNotgoing, the userToken and eventId are taken as variable into the function
    *and by matching eventId and userToken, function changes users' attending status of the event.
    *
    *@param eventId
    *@param userToken
    *@return madeNotGoing
    */
    this.makeNotgoing = function(userToken, eventId) {
        var deferred = $q.defer();

        var request = {
            user_token: userToken,
            event_id: eventId
        }

        $http({
            url: baseApiUrl + '/events/removeParticipant',
            method: 'POST',
            data: JSON.stringify(request)
        }).success(function (event) {
            deferred.resolve(event);
        }).error(function (response) {
            deferred.reject('An error occurred!');
        })

        return deferred.promise;
    }
    /**
    *In the function shareEvent, the fromToken, eventId and toToken are taken as variable into the function
    *and fromToken represents the user sharing event, toToken represents to which user the event has been shared
    *and eventId shows which event has been shared.
    *
    *@param fromToken
    *@param toToken
    *@param eventId
    *@return shareEvent
    */
    this.shareEvent = function(fromToken, toToken, eventId) {
        var deferred = $q.defer();

        var request = {
            from_user_token: fromToken,
            to_user_token: toToken,
            event_id : eventId
        }

        $http({
            url: baseApiUrl + '/users/shareEvent',
            method: 'POST',
            data: JSON.stringify(request)
        }).success(function (eventShare) {
            deferred.resolve(eventShare);
        }).error(function (response) {
            deferred.reject('An error occurred!');
        })

        return deferred.promise;
    }
    /**
    *In the function shareEventwithAGroup, fromToken, eventId and groupId are taken as variable into the function
    *and fromToken represents the user sharing event, eventId represents to which group the event has been shared
    *and eventId shows which event has been shared.
    *
    *@param fromToken
    *@param groupId
    *@param eventId
    *@return shareEvent
    */
    this.shareEventWithAGroup = function(fromToken, groupId, eventId) {
        var deferred = $q.defer();

        var request = {
            from_user_token: fromToken,
            group_id: groupId,
            event_id : eventId
        }

        $http({
            url: baseApiUrl + '/groups/shareEvent',
            method: 'POST',
            data: JSON.stringify(request)
        }).success(function (eventShare) {
            deferred.resolve(eventShare);
        }).error(function (response) {
            deferred.reject('An error occurred!');
        })

        return deferred.promise;
    }
    /**
    *In the function createInstantEvent, instantEvent is taken into the function as variable and
    *the function is called whenever it is asked to create an instant event.
    *
    *@param instantEvent
    *@return createdInstantEvent
    */
    this.createInstantEvent = function(instantEvent) {
        var deferred = $q.defer();

        $http({
            url: baseApiUrl + '/createInstantEvent',
            method: 'POST',
            data: JSON.stringify(instantEvent)
        }).success(function (response) {
            deferred.resolve(response);
        }).error(function (response) {
            deferred.reject('An error occurred!');
        })

        return deferred.promise;
    }
    /**
    *In the function getInstantEvent, the function provides the information of createdInstantEvent
    *to users. 
    *
    *@return instantEvent
    */
    this.getInstantEvents = function() {
        var deferred = $q.defer();

        $http({
            url: baseApiUrl + '/getInstantEvent',
            method: 'POST',
            data: JSON.stringify({})
        }).success(function (response) {
            angular.forEach(response, function(event) {
                event.timestampDate = event.date;
                event.date = utils.convertTimestampToDate(event.date);
            })

            response = _.sortBy(response, 'time_remaining');

            deferred.resolve(response || []);
        }).error(function (response) {
            deferred.reject('An error occurred!');
        })

        return deferred.promise;
    }
})
