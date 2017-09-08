define([
// These are path alias that we configured in our bootstrap
	'jquery', // lib/jquery/jquery
	'jquery-ui', 
	'underscore', // lib/underscore/underscore
	'backbone', // lib/backbone/backbone
	'text!./templates/scheduleGameNightTemplate.html',
	'app/models/gameNightModel',
	'app/components/userListComponent',
	'app/collections/allUsersCollection'
], function(
	$,
	jqueryUi,
	_, 
	Backbone,
	ScheduleGameNightTemplate,
	GameNightModel,
	UsersListComponent,
	UsersCollection
) {
	var ScheduleGameNightView = Backbone.View.extend({
		el: $("#page"),
		
		datepicker: null,
		
		repeatDays: null,
		
		userListComponent: null,
		
		events: {
			"submit": "onSubmit"
		},
		
		model: null,
		
		initialize: function () {
			this.model = new GameNightModel();
			var userCollection = new UsersCollection();
			userCollection.fetch({
				success: function (collection, response, options) {
					this.userListComponent = new UsersListComponent({
						selectable: true,
						users: userCollection.models,
						el: "#userList",
						showConfirmButton: false,
						selectButtonLabel: "Invite"
					});
					this.userListComponent.render();
				}.bind(this)
			});			
		},
		
		render: function () {
			var template = _.template(ScheduleGameNightTemplate);
			var compiledTemplate = template({});
			this.$el.html(compiledTemplate);
			this.datepicker = $("#startDate");
			this.datepicker.datepicker();
			this.repeatDays = $("#repeatDays");
			
			if(this.userListComponent) {
				this.userListComponent.render();
			}
		},
		
		onSubmit: function (e) {
			e.preventDefault();
			var inviteUserList = this.userListComponent.getSelectedUsers();
			var userIdList = [];
			
			inviteUserList.forEach(function (element) {
				userIdList.push(element.get("id"));
			}.bind(this));
			
			this.model.set("startDate", new Date(this.datepicker.val()));
			this.model.set("repeatDays", this.repeatDays.val());
			this.model.set("invitedUsers", userIdList);
			this.model.save();
		}
	});
	// Above we have passed in jQuery, Underscore and Backbone
	// They will not be accessible in the global scope
	return ScheduleGameNightView;
	// What we return here will be used by other modules
});