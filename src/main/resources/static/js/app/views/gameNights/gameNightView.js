define([
	// These are path alias that we configured in our bootstrap
	'jquery', // lib/jquery/jquery
	'underscore', // lib/underscore/underscore
	'backbone', // lib/backbone/backbone
	'text!./templates/gameNightTemplate.html', 
	'app/models/gameNightModel',
	'app/collections/gameNightInstanceCollection'
], function(
	$, 
	_,
	Backbone, 
	GameNightTemplate, 
	GameNight,
	GameNightInstanceCollection
) {
	var GameNightView = Backbone.View.extend({
		el : $('#page'),

		model: null,
		
		instanceModels: null,
		
		initialize : function(model) {
			this.model = model;
			this.collection = new GameNightInstanceCollection();
			this.collection.bind("update", _.bind(this.render, this));
			this.collection.fetchByGameNightId(this.model.get("id"), function () {});
		},

		render : function() {
			var template = _.template(GameNightTemplate);
			var compiledTemplate = template({
				gameNight : this.model,
				instanceModels: this.collection.models
			});
			this.$el.html(compiledTemplate);
		}
	});
	// Above we have passed in jQuery, Underscore and Backbone
	// They will not be accessible in the global scope
	return GameNightView;
	// What we return here will be used by other modules
});
