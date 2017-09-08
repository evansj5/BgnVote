define([
  // These are path alias that we configured in our bootstrap
  'jquery',     // lib/jquery/jquery
  'underscore', // lib/underscore/underscore
  'backbone',    // lib/backbone/backbone
  'app/models/gameNightInstanceModel'
], function($, _, Backbone, GameNightInstanceModel){
  // Above we have passed in jQuery, Underscore and Backbone
  // They will not be accessible in the global scope
	var GameNights = Backbone.Collection.extend({
		model: GameNightInstanceModel,
		url: 'rest/api/gameNightInstance',
		fetchByGameNightId: function (gameNight, successCallback) {
			var data = this.fetch({ data: {gameNightId: gameNight}, 
				success: function (collection, response) {
					successCallback(collection, response);
				}
			});
			
			return data;
		}
	});

  return GameNights;
  // What we return here will be used by other modules
});