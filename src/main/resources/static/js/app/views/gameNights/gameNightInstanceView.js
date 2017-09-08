define([
  // These are path alias that we configured in our bootstrap
  'jquery',     // lib/jquery/jquery
  'underscore', // lib/underscore/underscore
  'backbone',    // lib/backbone/backbone
  'app/components/boardGameNominationComponent',
  'app/components/gameNightRsvpComponent',
  'text!./templates/gameNightInstanceTemplate.html',
  'app/router',
  'app/components/votingComponent',
  'app/components/displayVotesComponent'
], function($, _, Backbone, 
		BoardGameNominationComponent, 
		RsvpComponent, 
		GameNightInstanceTemplate,
		router,
		VotingComponent,
		DisplayVotesComponent){
  // Above we have passed in jQuery, Underscore and Backbone
  // They will not be accessible in the global scope
	
	var GameNightInstanceView = Backbone.View.extend({
		el: $('#page'),
		
		model: null,
		
		nominationComponent: null,
		
		rsvpComponent: null,
		
		votingComponent: null,
		
		displayVotesComponent: null,
		
		initialize(model) {
			this.model = model;
			_.bindAll(this, "confirmRsvp");
			_.bindAll(this, "declineRsvp");
			_.bindAll(this, "sendRsvp");
			_.bindAll(this, "votesSubmitted");
			_.extend(this, Backbone.Events);
		},
		
		render: function () {
			var template = _.template(GameNightInstanceTemplate);
			var compiledTemplate = template({
				instance : this.model
			});
			this.$el.html(compiledTemplate);
			
			switch (this.model.get("state")) {
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
				router.navigate("gameNightInstance/" + this.model.get("id"), {trigger:true});
			}.bind(this));
		},
		
		votesSubmitted: function () {
			router.navigate("gameNightInstance/" + this.model.get("id"), {trigger: true});
		}
	});
	
  return GameNightInstanceView;
  // What we return here will be used by other modules
});