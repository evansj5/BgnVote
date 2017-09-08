define([
	// These are path alias that we configured in our bootstrap
	'jquery', // lib/jquery/jquery
	'jquery-ui', 
	'underscore', // lib/underscore/underscore
	'backbone', // lib/backbone/backbone
	'dataTables', 
	'app/collections/gameNightCollection',
	'text!./templates/gameNightsIndexTemplate.html'
], function(
	$, 
	jqueryUi, 
	_,
	Backbone, 
	DataTable, 
	GameNightCollection, 
	GameNightsIndexTemplate
) {
	var GameNightIndexView = Backbone.View.extend({
		initialize : function() {
			this.collection = new GameNightCollection();
			this.collection.bind("update", _.bind(this.render, this));
			this.collection.fetch();
		},
		render : function() {
			var template = _.template(GameNightsIndexTemplate);
			var compiledTemplate = template({
				gameNights : this.collection.models
			});
			$(this.el).html(compiledTemplate);
			$('#gameNightsTable').DataTable({
				"order" : [ [ 1, 'desc' ] ]
			});
		},
	});
	// Above we have passed in jQuery, Underscore and Backbone
	// They will not be accessible in the global scope
	return GameNightIndexView;
	// What we return here will be used by other modules
});
