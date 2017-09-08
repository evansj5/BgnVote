define([
  // These are path alias that we configured in our bootstrap
  'jquery',     // lib/jquery/jquery
  'underscore', // lib/underscore/underscore
  'backbone',    // lib/backbone/backbone
  'app/models/gameModel'
], function($, _, Backbone, GameModel){
  // Above we have passed in jQuery, Underscore and Backbone
  // They will not be accessible in the global scope
	var Games = Backbone.Collection.extend({
		model: GameModel,
		url: 'rest/api/games/gameNightInstance',
		fetch: function(gameNightInstanceId) {
			var options = { data: {id: gameNightInstanceId}};
			return Backbone.Collection.prototype.fetch.call(this, options);
		}
	});

  return Games;
  // What we return here will be used by other modules
});