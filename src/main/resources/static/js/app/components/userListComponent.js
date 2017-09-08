define([
  // These are path alias that we configured in our bootstrap
  'jquery',     // lib/jquery/jquery
  'underscore', // lib/underscore/underscore
  'backbone',    // lib/backbone/backbone
  'text!./templates/inviteUsersTemplate.html'
], function($, _, Backbone, UserListTemplate){
  // Above we have passed in jQuery, Underscore and Backbone
  // They will not be accessible in the global scope
  
  var UserListComponent = Backbone.View.extend({
  	users: null,
  	
  	el: null,
  	
  	selectedUsers: [],
  	
  	events: {
  		"click .userInviteButton": "userSelected",
  		"click .confirmSelections": "confirmSelections"
  	},
  	
  	options: null,
  	
  	initialize(options) {
  		this.options = options;
  		this.users = options.users;
  		this.el = options.el;
  		_.bindAll(this, "userSelected");
  		_.bindAll(this, "confirmSelections");
  		_.bindAll(this, "getSelectedUsers");
  	},
  	
  	render: function () {
  		var template = _.template(UserListTemplate);
  		var compiledTemplate = template({options: this.options});
  		this.$el.html(compiledTemplate);
  	},
  	
  	userSelected: function (event) {
  		event.preventDefault();
  		var target = event.target;
  		var userId = target.attributes["data-id"].value;
  	
  		var selectedUser = this.selectedUsers.find(function (element) {
  			return element.get("id") == userId; 
  		});
  		
  		if(selectedUser) {
  			this.selectedUsers.filter(function (element) {
  				return element.get("id") != userId;
  			});  			
  		} else {
  			var user = this.users.find(function (element) {
  				return element.get("id") == userId;
  			}.bind(this));
  			
  			this.selectedUsers.push(user);  			
  		}
  		
  		$(target).toggleClass("button-primary");
  	},
  	
  	confirmSelections: function () {
  		this.trigger("confirmSelections", this.selectedUsers);
  	},
  	
  	getSelectedUsers: function () {
  		return this.selectedUsers;
  	}
  });
  
  return UserListComponent;
  // What we return here will be used by other modules
});
