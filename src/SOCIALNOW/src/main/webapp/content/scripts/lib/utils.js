(function () {
    var utils = {};

    // Date manipulation
    utils.convertTimestampToDate = function (timestamp) {
        var a = moment(new Date(timestamp));

        return a.format("dddd, MMMM Do YYYY");;
    }

    utils.convertDateToApiDate = function(datetime) {
        // 01/13/2016 6:00 PM
        var dateParts = datetime.split(' ');
        var time = dateParts[1];

        if(dateParts[2] == 'PM') {
            var timeParts = time.split(':');
            time = (parseInt(timeParts[0]) + 12) + ":" + timeParts[1];
        }

        var dateArray = dateParts[0].split('/');
        return dateArray[1] + "/" + dateArray[0] + "/" + dateArray[2] + " " + time + " " + dateParts[2];
    }

    utils.findDifferenceOfTimestampsInMinutes = function(ts1, ts2) {
        var dateA = new Date(ts1);
        var dateB = new Date(ts2);

        var dayRelativeDifference = dateB.getHours()*60 + dateB.getMinutes() - dateA.getHours()*60 - dateA.getMinutes();
        return (ts2-ts1)/60;
    }

    // String manipulation
    utils.trimCharacter = function(str, charachter) {
        if(!str)
            return "";

        while(str.charAt(0) === charachter)
            str = str.substr(1);

        return str;
    }

    // Events manipulation
    utils.manipulateEvents = function(events) {
        _.each(events, function(event) {
            event.event_date_in_date = utils.convertTimestampToDate(event.event_date);
            event.tags = utils.trimCharacter(event.tags, ',');
        })

        return events;
    }

    // User manipulation
    utils.manipulateUser = function(user) {
        _.each(user.followers, function(follower) {
            follower.user_tags = utils.trimCharacter(follower.user_tags, ',');
        })

        _.each(user.followings, function(following) {
            following.user_tags = utils.trimCharacter(following.user_tags, ',');
        })
    }

    // Tag manipulation
    utils.manipulateTags = function(tags) {
        return _.without(_.unique(tags), '');
    }

    window.utils = utils;
})();