define([
        // These are path alias that we configured in our bootstrap
        'jquery',     // lib/jquery/jquery
        'underscore', // lib/underscore/underscore
        'backbone'    // lib/backbone/backbone
        ], function($, _, Backbone){
	// Above we have passed in jQuery, Underscore and Backbone
	// They will not be accessible in the global scope
	var NominatedGameModel = Backbone.Model.extend({
		default: {
				bggGameId: "",
				gameNightInstanceId: "",
			}
		});

	return NominatedGameModel;
	// What we return here will be used by other modules
});