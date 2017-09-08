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
		url: 'rest/api/games',
		fetchByUsername: function(user, successCallback) {
			var data = this.fetch({ data: { username: user }, 
				success: function(collection, response) {			
					successCallback(collection, response);
				}
			});
			
			
			return data;
		},
		fetchByUsernames: function(users, successCallback) {
			var data = this.fetch({ data: { username: users },
				success: function(collection, response) {
					successCallback(collection, response);
				}
			})
			
		},
		
		fetchByGameNightInstance: function (gameNightInstanceId, successCallback) {
			var data = this.fetch({ data: { gameNightInstanceId: gameNightInstanceId},
				success: function(collection, response) {
					successCallback(collection, response);
				}
			});			
		}
	});

  return Games;
  // What we return here will be used by other modules
});
