define([
  'jquery',
  'underscore',
  'backbone',
  'text!./templates/displayVotesComponentTemplate.html',
  'app/models/gameNightInstanceResultsModel'
], function($, _, Backbone, 
		DisplayVotesTemplate,
		GameNightInstanceResultsModel){
	var DisplayVotesComponent = Backbone.View.extend({
		el: null,
		
		gameData: null,
		
		initialize: function (options) {
			var resultsModel = new GameNightInstanceResultsModel();
			
			resultsModel.fetch(options.gameNightInstanceId).done(function () {
				this.gameData = resultsModel;
				this.render();
			}.bind(this));
		},
		
		render: function () {
			if(this.gameData) {
				var template = _.template(DisplayVotesTemplate);
				var compiledTemplate = template({model:this.gameData});
				$(this.el).html(compiledTemplate);
			}
		}		
	});
	
	
  return DisplayVotesComponent;
});
