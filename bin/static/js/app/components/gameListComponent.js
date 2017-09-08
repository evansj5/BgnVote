define([
        // These are path alias that we configured in our bootstrap
        'jquery',     // lib/jquery/jquery
        'underscore', // lib/underscore/underscore
        'backbone',    // lib/backbone/backbone
        'app/collections/gameCollection',
        'text!./gameListComponentTemplate.html',
        'jquery-ui'
        ], function($, _, Backbone, GameCollection, gameListTemplate, jqueryui){
	// Above we have passed in jQuery, Underscore and Backbone
	// They will not be accessible in the global scope
	var GameListComponent = Backbone.View.extend({		
		gameListSortable: null,
		
		filterInput: null,
		
		options: {},
		
		selectedGames: [],
		
		initialize: function (options) {
			this.el = options.el;
			this.options = options;
			_.bindAll(this, "filter");
			_.bindAll(this, "moveToSelected");
			_.bindAll(this, "confirmSelections");
			_.extend(this, Backbone.Events);
		},
		
		render: function() {
			var data = {
					selectedGames: this.selectedGames,
					games: this.collection,
					options: this.options
			};
			var template = _.template(gameListTemplate);
			var compiledTemplate = template(data);
			$(this.el).html(compiledTemplate);
			
			this.gameListSortable = $('#game-list-sortable');
			
			if(this.options.sortable) {
				$('#game-list-sortable').sortable();
			}
			
			if(this.options.searchable) {
				this.filterInput = $('#gameListFilter')
				this.filterInput.on("input", this.filter);
			}
			
			if(this.options.selectable) {
				$('button.selectButton').on("click", this.moveToSelected);
				$('button.confirmSelectionsButton').on("click", this.confirmSelections);
			}
			
			$('#game-list-sortable').disableSelection();
		},
		
		filter: function (event) {			
			var value = this.filterInput.val();
			
			$(this.el).find("#game-list-sortable > li").each(function () {
				if($(this).find(".gameName").text().search(value) > -1) {
					$(this).show();
				} else {
					$(this).hide();
				}
			});
		},
		
		moveToSelected: function (event) {
			if(this.options.maxSelections && this.selectedGames.length < this.options.maxSelections) {
				var gameId = event.target.attributes['data-id'].value;
				
				var selectedGame = this.collection.find(function (element) {
					return element.get("id") == gameId;
				});
				
				this.collection = this.collection.filter(function (el) {
										return el.get("id") !== gameId;
									});
				
				this.selectedGames.push(selectedGame);
				
				this.render();
			} else {
				//TODO validation message
			}
		},
		
		confirmSelections: function (event) {
			event.preventDefault();
			this.trigger("confirmSelections", this.selectedGames);
		},
		
		getGameOrder: function () {
			var orderedList = [];
			var index = 1;
			$(".gameName").each(function () {
				orderedList.push({
					vote: index,
					gameId: this.attributes['data-id'].value
				});
				index++;
			});
			
			return orderedList;
		}
	});

	return GameListComponent;
	// What we return here will be used by other modules
});
