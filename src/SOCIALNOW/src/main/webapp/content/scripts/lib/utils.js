(function () {
    var utils = {};

    // Date manipulation
    utils.convertTimestampToDate = function (timestamp) {
        var a = moment(new Date(timestamp));

        return a.format("dddd, MMMM Do YYYY");;
    }

    utils.convertDateToApiDate = function(date) {
        var dateArray = date.split('/');
        return dateArray[1] + "/" + dateArray[0] + "/" + dateArray[2];
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