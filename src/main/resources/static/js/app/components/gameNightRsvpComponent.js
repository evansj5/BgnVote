define([
  // These are path alias that we configured in our bootstrap
  'jquery',     // lib/jquery/jquery
  'underscore', // lib/underscore/underscore
  'backbone',    // lib/backbone/backbone
  'text!./templates/rsvpComponentTemplate.html'
], function($, _, Backbone,
	RsvpTemplate){
  // Above we have passed in jQuery, Underscore and Backbone
  // They will not be accessible in the global scope
  var RsvpComponent = Backbone.View.extend({
  	el: null,
  	
  	events: {
  		"click .confirmButton": "confirmRsvp",
  		"click .declineButton": "declineRsvp"
  	},
  	
  	initialize(options) {
  		this.el = options.el;
  		
  		_.bindAll(this, "confirmRsvp");
  		_.bindAll(this, "declineRsvp");
  		_.extend(this, Backbone.Events);
  	},
  	
  	render: function () {
  		var template = _.template(RsvpTemplate);
  		var compiledTemplate = template();
  		$(this.el).html(compiledTemplate);
  	},
  	
  	confirmRsvp: function () {
  		this.trigger("confirmRsvp");
  	},
  	
  	declineRsvp: function () {
  		this.trigger("declineRsvp");
  	}
  });
  
  return RsvpComponent;
  // What we return here will be used by other modules
});
