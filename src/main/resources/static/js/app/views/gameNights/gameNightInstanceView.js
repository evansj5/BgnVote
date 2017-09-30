define([
  // These are path alias that we configured in our bootstrap
  'jquery',     // lib/jquery/jquery
  'underscore', // lib/underscore/underscore
  'backbone',    // lib/backbone/backbone
  'app/components/boardGameNominationComponent',
  'app/components/gameNightRsvpComponent',
  'text!./templates/gameNightInstanceTemplate.html',
  'app/components/votingComponent',
  'app/components/displayVotesComponent',
  'app/models/gameNightInstanceModel'
], function($, _, Backbone, 
		BoardGameNominationComponent, 
		RsvpComponent, 
		GameNightInstanceTemplate,
		VotingComponent,
		DisplayVotesComponent,
		GameNightInstanceModel){
  // Above we have passed in jQuery, Underscore and Backbone
  // They will not be accessible in the global scope
	
	var GameNightInstanceView = Backbone.View.extend({
		el: $('#page'),
		
		model: null,
		
		nominationComponent: null,
		
		rsvpComponent: null,
		
		votingComponent: null,
		
		displayVotesComponent: null,
		
		initialize(id) {
			this.id = id;
			_.bindAll(this, "confirmRsvp");
			_.bindAll(this, "declineRsvp");
			_.bindAll(this, "sendRsvp");
			_.bindAll(this, "votesSubmitted");
			_.bindAll(this, "render");
			_.bindAll(this, "handleWaitingForRsvp");
			_.extend(this, Backbone.Events);
		},
		
		render: function () {
			this.model = new GameNightInstanceModel({ id: this.id});
			this.model.fetch({
				success: function (model) {			
					var template = _.template(GameNightInstanceTemplate);
					var compiledTemplate = template({
						instance : this.model
					});
					this.$el.html(compiledTemplate);
					
					switch (model.get("state")) {
					case "WAITING_FOR_RSVP":
						this.handleWaitingForRsvp();
						break;
					case "VOTING":
						this.handleVoting();
						break;
					case "FINALIZED":
						this.handleDisplayVotes();
						break;
					}
				}.bind(this)
			});
		},
		
		handleDisplayVotes: function () {
			this.displayVotesComponent = new DisplayVotesComponent({
				gameNightInstanceId: this.model.get('id'),
				el: '#view'
			});			
		},
		
		handleVoting: function () {
			this.votingComponent = new VotingComponent({
				gameNightInstanceId: this.model.get('id'),
				voted: this.model.get('voted'),
				el: '#view'
			});
			
			this.votingComponent.on("votesSubmitted", this.votesSubmitted);	
		},
		
		handleWaitingForRsvp: function () {
			if(this.model.get('rsvpd')) {
				if(this.model.get('coming')) {
					if(!this.nominationComponent) {
						this.nominationComponent = new BoardGameNominationComponent({
							model: this.model,
							el: '#view'
						});
						this.nominationComponent.on("confirmSelections", this.render);
					}
					
					this.nominationComponent.render();
				}
			} else {
				this.createRsvpComponent();
			}
		},
		
		createRsvpComponent: function () {
			if(!this.rsvpComponent) {
				this.rsvpComponent = new RsvpComponent({
					el: '#view'
				});
			}
			
			this.rsvpComponent.on("confirmRsvp", this.confirmRsvp);
			this.rsvpComponent.on("declineRsvp", this.declineRsvp);
			
			this.rsvpComponent.render();
		},
		
		confirmRsvp: function () {
			this.sendRsvp(true);
		},
		
		declineRsvp: function () {
			this.sendRsvp(false);
		},
		
		sendRsvp: function (coming) {
			$.get("/rest/api/gameNightInstance/" + this.model.get("id") + "/rsvp", {
				coming: coming
			}).done(function () {
				Backbone.history.loadUrl();
			}.bind(this));
		},
		
		votesSubmitted: function () {
			Backbone.history.loadUrl();
		}
	});
	
  return GameNightInstanceView;
  // What we return here will be used by other modules
});