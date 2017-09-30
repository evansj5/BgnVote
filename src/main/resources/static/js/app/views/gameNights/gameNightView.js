define([
	// These are path alias that we configured in our bootstrap
	'jquery', // lib/jquery/jquery
	'underscore', // lib/underscore/underscore
	'backbone', // lib/backbone/backbone
	'text!./templates/gameNightTemplate.html', 
	'app/models/gameNightModel',
	'app/collections/gameNightInstanceCollection',
	'app/models/scheduleGameNightInstanceModel'
], function(
	$, 
	_,
	Backbone, 
	GameNightTemplate, 
	GameNight,
	GameNightInstanceCollection,
	ScheduleGameNightInstanceModel
) {
	var GameNightView = Backbone.View.extend({
		el : $('#page'),

		model: null,
		
		instanceModels: null,
		
		datepicker: null,
		
		events: {
			"click .scheduleNewGameNight" : "scheduleGameNight"
		},
		
		initialize : function(model) {
			this.model = model;
			this.collection = new GameNightInstanceCollection();
			this.collection.bind("update", _.bind(this.render, this));
			this.collection.fetchByGameNightId(this.model.get("id"), function () {});
			_.bindAll(this, "scheduleGameNight");
		},

		render : function() {
			var template = _.template(GameNightTemplate);
			var compiledTemplate = template({
				gameNight : this.model,
				instanceModels: this.collection.models
			});
			this.$el.html(compiledTemplate);
			
			this.datepicker = $('#startDate');
			this.datepicker.datepicker();
		},
		
		scheduleGameNight: function (event) {
			event.preventDefault();
			var startDate = new Date(this.datepicker.val());
			var id = this.model.get("id");
			
			var scheduleNewModel = new ScheduleGameNightInstanceModel();
			scheduleNewModel.set("gameNightId", id);
			scheduleNewModel.set("startDate", startDate);
			scheduleNewModel.save();
		}
	});
	// Above we have passed in jQuery, Underscore and Backbone
	// They will not be accessible in the global scope
	return GameNightView;
	// What we return here will be used by other modules
});
