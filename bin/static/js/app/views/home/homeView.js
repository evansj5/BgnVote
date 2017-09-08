define([
  // These are path alias that we configured in our bootstrap
  'jquery',     // lib/jquery/jquery
  'underscore', // lib/underscore/underscore
  'backbone',    // lib/backbone/backbone
  'text!./homeTemplate.html',
  'app/collections/gameCollection',
  'app/components/gameListComponent'
], function($, _, Backbone, homeTemplate, Games, GameListComponent){
  // Above we have passed in jQuery, Underscore and Backbone
  // They will not be accessible in the global scope
  var HomeView = Backbone.View.extend({
    el: $('#page'),
    render: function() {
      this.$el.html(homeTemplate);
      var games = new Games();
      games.fetchByUsername("evansj5", function(collection, response) {
    	  var gameListComponent = new GameListComponent(
    			  { 
    				  collection: games.models, 
    				  selectable: true,
    				  sortable: false,
    				  searchable: true,
    				  selectedHeader: "Nominations",
    				  selectButtonLabel: "Nominate",
    				  maxSelections: 2,
    				  confirmSelectionsButtonLabel: "Confirm Nominations"
				  });
		  
          gameListComponent.render();
      });


    }
  });


  return HomeView;
  // What we return here will be used by other modules
});
