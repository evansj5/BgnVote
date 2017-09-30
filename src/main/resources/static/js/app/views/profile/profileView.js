define([
        // These are path alias that we configured in our bootstrap
        'jquery',     // lib/jquery/jquery
        'underscore', // lib/underscore/underscore
        'backbone',    // lib/backbone/backbone
        'app/models/profileModel',
        'text!./profileTemplate.html',
        'jquery.toast.min'
        ], function($, _, Backbone, ProfileModel, ProfileTemplate, toast){
	// Above we have passed in jQuery, Underscore and Backbone
	// They will not be accessible in the global scope
	var ProfileView = Backbone.View.extend({
		el: $("#page"),
		
		events: {
			"click .saveButton": "saveEvent"
		},
		
		render: function() {
			var template = _.template(ProfileTemplate);
			var compiledTemplate = template({ profile: this.model});
			this.$el.html(compiledTemplate);
		},
		
		saveEvent: function(event) {
			event.preventDefault();
			
			var user = this.model.get("user");
			user.rsvpReminderEmail = $(".rsvpReminder").is(":checked");
			user.nominateReminderEmail = $(".nominateReminder").is(":checked");
			user.voteReminderEmail = $(".voteReminder").is(":checked");
			this.model.save({}, { success: function (){
				$.toast({
					text: "Saved successfully",
					showHideTransition: 'slide',
				})
			}});
		}
	});


	return ProfileView;
	// What we return here will be used by other modules
});
