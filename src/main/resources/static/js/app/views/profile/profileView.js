define([
        // These are path alias that we configured in our bootstrap
        'jquery',     // lib/jquery/jquery
        'underscore', // lib/underscore/underscore
        'backbone',    // lib/backbone/backbone
        'app/models/profileModel',
        'text!./profileTemplate.html'
        ], function($, _, Backbone, ProfileModel, ProfileTemplate){
	// Above we have passed in jQuery, Underscore and Backbone
	// They will not be accessible in the global scope
	var ProfileView = Backbone.View.extend({
		el: $("#page"),
		
		render: function() {
			var template = _.template(ProfileTemplate);
			var compiledTemplate = template({ profile: this.model});
			this.$el.html(compiledTemplate);
		}
	});


	return ProfileView;
	// What we return here will be used by other modules
});
