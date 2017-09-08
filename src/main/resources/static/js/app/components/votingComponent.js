define([
  // These are path alias that we configured in our bootstrap
  'jquery',     // lib/jquery/jquery
  'underscore', // lib/underscore/underscore
  'backbone',    // lib/backbone/backbone
  'app/components/gameListComponent',
  'app/collections/gameNightInstanceGamesCollection',
  'text!./templates/votingComponentTemplate.html',
  'app/models/voteModel'
], function($, _, Backbone,
	GameListComponent,
	NominatedGamesCollection,
	VotingComponentTemplate,
	VotesModel){
  // Above we have passed in jQuery, Underscore and Backbone
  // They will not be accessible in the global scope
  var VotingComponent = Backbone.View.extend({
  	el: null,
  	
  	gameListComponent: null,
  	
  	gameList: null,
  	
  	gameNightInstanceId: null,
  	
  	events: {
  		'click .submit-vote': 'submitVote'
  	},
  	
  	initialize: function (options) {
  		this.el = options.el;
  		this.gameNightInstanceId = options.gameNightInstanceId;
  		this.gameList = new NominatedGamesCollection();
  		this.gameList.fetch(this.gameNightInstanceId).done(function () {  			
  			this.gameListComponent = new GameListComponent({
  				el: "#game-list",
  				headerText: 'Games',
  				collection: this.gameList.models,
  				selectable: false,
  				sortable: true,
  				searchable: false
  			});
  			this.render();
  		}.bind(this));
  		
  		_.bindAll(this, "render");
  		_.bindAll(this, "submitVote");
  	},
  	
  	render: function () {
  		var template = _.template(VotingComponentTemplate);
  		var compiledTemplate = template();
  		$(this.el).html(compiledTemplate);
  		
  		if(this.gameListComponent) {
  			this.gameListComponent.render();
  		}
  	},
  	
  	submitVote: function (event) {
  		event.preventDefault();
  		
  		var votes = this.gameListComponent.getGameOrder();
  		
  		var voteModel = new VotesModel({
  			gameNightInstanceId: this.gameNightInstanceId,
  			votes: votes
  		});
  		
  		voteModel.save().done(function () {
  			this.trigger("votesSubmitted");
  		}.bind(this));
  	}
  });
  
  return VotingComponent;
  // What we return here will be used by other modules
});
