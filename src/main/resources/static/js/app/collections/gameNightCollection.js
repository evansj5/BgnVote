define([
  // These are path alias that we configured in our bootstrap
  'jquery',     // lib/jquery/jquery
  'underscore', // lib/underscore/underscore
  'backbone',    // lib/backbone/backbone
  'app/models/gameNightModel'
], function($, _, Backbone, GameNightModel){
  // Above we have passed in jQuery, Underscore and Backbone
  // They will not be accessible in the global scope
	var GameNights = Backbone.Collection.extend({
		model: GameNightModel,
		url: 'rest/api/gameNight',
	});

  return GameNights;
  // What we return here will be used by other modules
});
