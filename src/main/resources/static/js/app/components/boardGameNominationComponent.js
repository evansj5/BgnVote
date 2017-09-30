define([
// These are path alias that we configured in our bootstrap
		'jquery', // lib/jquery/jquery
		'underscore', // lib/underscore/underscore
		'backbone', // lib/backbone/backbone
		'text!./boardGameNominationTemplate.html',
		'app/models/gameNightInstanceModel', 
		'app/collections/gameCollection',
		'app/components/gameListComponent',
		'app/collections/gameNightInstanceGamesCollection',
		'app/collections/nominatedGamesCollection'], function(
		$, 
		_, 
		Backbone,
		GameNightInstanceTemplate, 
		GameNightInstanceModel, 
		Games,
		GameListComponent,
		GameNightInstanceGamesCollection,
		NominatedGamesCollection
		) {
	var BoardGameNominationComponent = Backbone.View.extend({
		model : null,
		
		gameListComponent: null,
		
		gameList: null,
		
		alreadyNominatedList: null,
		
		alreadyNominatedListComponent: null,
		
		el: null,

		initialize : function(options) {
			this.model = options.model;
			this.el = options.el;
			_.bindAll(this, "confirmSelections");
			_.bindAll(this, "render");
			_.extend(this, Backbone.Events);
		},

		render : function() {
			var template = _.template(GameNightInstanceTemplate);
			var compiledTemplate = template({
				instance : this.model
			});
			this.$el.html(compiledTemplate);

			this.setupGameList();
		},

		setupGameList : function() {
			var nominatedGames = new GameNightInstanceGamesCollection();
			nominatedGames.fetch(this.model.get("id")).done(function () {
				this.alreadyNominatedList = nominatedGames.models;				
				var games = new Games();
				games.fetchByUsername("evansj5", function(collection, response) {
					this.gameList = games.models;
					this.removeDuplicateGamesFromList();
					this.createListComponents();
				}.bind(this));
			}.bind(this));			
		},
		
		removeDuplicateGamesFromList: function () {
			this.alreadyNominatedList.forEach(function (element) {
				this.gameList = this.gameList.filter(function (el) {
					return el.get("id") !== element.get("id");
				}.bind(this));
			}.bind(this));
		},
		
		createListComponents: function () {
			this.gameListComponent = new GameListComponent({
				headerText: 'Library',
				el: '#game-list',
				collection : this.gameList,
				selectable : !this.model.get("nominated"),
				sortable : false,
				searchable : true,
				selectedHeader : "Selections",
				selectButtonLabel : "Nominate",
				maxSelections : 2,
				confirmSelectionsButtonLabel : "Confirm Nominations"
			});
			
			this.alreadyNominatedListComponent = new GameListComponent({
				headerText: 'Nominated',
				el: '#already-nominated-game-list',
				collection : this.alreadyNominatedList,
				selectable : false,
				sortable : false,
				searchable : false
			});	
			
			this.alreadyNominatedListComponent.render();
			this.gameListComponent.on("confirmSelections", this.confirmSelections);
			this.gameListComponent.render();
		},
		
		confirmSelections: function (selections) {
			var collection = new NominatedGamesCollection();
			
			selections.forEach(function (element) {
				collection.add([{
					bggGameId: element.get("id"),
					gameNightInstanceId: this.model.get("id")
				}]);
			}.bind(this));
			
			collection.updateAll(function () {
				this.trigger("confirmSelections");
			});
		}		
	});
	// Above we have passed in jQuery, Underscore and Backbone
	// They will not be accessible in the global scope
	return BoardGameNominationComponent;
	// What we return here will be used by other modules
});