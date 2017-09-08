define([
        // These are path alias that we configured in our bootstrap
        'jquery',     // lib/jquery/jquery
        'underscore', // lib/underscore/underscore
        'backbone'    // lib/backbone/backbone
        ], function($, _, Backbone){
	// Above we have passed in jQuery, Underscore and Backbone
	// They will not be accessible in the global scope
	var GameNight = Backbone.Model.extend({
    urlRoot: 'rest/api/gameNight',
    default: {
      id: "-1",
      startDate: new Date(),
      repeatDays: 14
    }
  });

	return GameNight;
	// What we return here will be used by other modules
});
