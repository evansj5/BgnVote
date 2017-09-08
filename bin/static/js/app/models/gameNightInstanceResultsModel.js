define([
        // These are path alias that we configured in our bootstrap
        'jquery',     // lib/jquery/jquery
        'underscore', // lib/underscore/underscore
        'backbone'    // lib/backbone/backbone
        ], function($, _, Backbone){
	// Above we have passed in jQuery, Underscore and Backbone
	// They will not be accessible in the global scope
	var GameNightInstanceResultsModel = Backbone.Model.extend({
		url: 'rest/api/gameNightInstance/getResults',
		fetch: function(gameNightInstanceId) {
			var options = {data: {gameNightInstanceId: gameNightInstanceId}};
			return Backbone.Model.prototype.fetch.call(this, options);
		}					
	});
		
	return GameNightInstanceResultsModel;
	// What we return here will be used by other modules
});
