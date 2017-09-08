define([
  // These are path alias that we configured in our bootstrap
  'jquery',     // lib/jquery/jquery
  'underscore', // lib/underscore/underscore
  'backbone',    // lib/backbone/backbone
  'app/models/nominatedGameModel'
], function($, _, Backbone, NominatedGameModel){
  // Above we have passed in jQuery, Underscore and Backbone
  // They will not be accessible in the global scope
	var GameNights = Backbone.Collection.extend({
		model: NominatedGameModel,
		url: 'rest/api/gameNightInstance/nominate',
		
		updateAll: function () {
			var collection = this;
			options = {
					success: function (model, resp, xhr) {
						collection.reset(model);
					}
			};
			
			return Backbone.sync('update', this, options);
		}
	});

  return GameNights;
  // What we return here will be used by other modules
});