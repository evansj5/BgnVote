define([
  // These are path alias that we configured in our bootstrap
  'jquery',     // lib/jquery/jquery
  'underscore', // lib/underscore/underscore
  'backbone',    // lib/backbone/backbone
  'text!./templates/adminTemplate.html'
], function($, _, Backbone,
		AdminTemplate){
  var AdminView = Backbone.View.extend({
	  el: null,
	  
	  events: {
		"click #addEmail": "addAnotherEmail",
		"click #generateRegKeys": "generateRegKeys"
	  },
	  
	  initialize: function () {
		  _.bindAll(this, "addAnotherEmail");
		  _.bindAll(this, "generateRegKeys");
	  },
	  
	  render: function () {
		var template = _.template(AdminTemplate);
		var compiledTemplate = template();
		$(this.el).html(compiledTemplate);
	  },
	  
	  addAnotherEmail: function (event) {
		  event.preventDefault();
		  var container = $("#emailAddressContainer");
		  var input = "<br/>Email Address: <input type='text' class='emailAddress' />";
		  container.append(input);
	  },
	  
	  generateRegKeys: function (event) {
		  event.preventDefault();
		  var emails = [];
		  var emailElements = $(".emailAddress");
		  
		  $.each(emailElements, function (index) {
			  emails.push(emailElements[index].value);
		  }.bind(this));
		  
		  $.ajax({
			  url:"rest/api/admin/registrationKey", 
			  type: "POST",
			  data: JSON.stringify(emails),
			  contentType: "application/json",
			  dataType:"json"
			  }).done(function () {
			  //TODO do something here...
		  });
	  }
  });
  
  return AdminView;
  // What we return here will be used by other modules
});
