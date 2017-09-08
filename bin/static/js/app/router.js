define([ 
         'jquery', 
         'underscore', 
         'backbone', 
         'app/views/home/homeView',
		 'app/views/profile/profileView', 		 
		 'app/views/gameNights/gameNightsIndexView',
		 'app/views/gameNights/gameNightView', 
		 'app/views/gameNights/scheduleGameNightView',
		 'app/models/profileModel',
		 'app/models/gameNightModel',
		 'app/views/gameNights/gameNightInstanceView',
		 'app/models/gameNightInstanceModel'],
		 function(
		$, 
		_, 
		Backbone,
		HomeView, 
		ProfileView, 
		GameNightIndexView, 
		GameNightView,
		ScheduleGameNightView,
		ProfileModel,
		GameNightModel,
		GameNightInstanceView,
		GameNightInstanceModel
			) {
			var AppRouter = Backbone.Router.extend({
				routes : {
					'myCollection' : 'myCollection',
					'profile' : 'profile',
					'home' : 'home',
					'gamenight/:id' : 'gamenight',
					'scheduleGameNight': 'scheduleGameNight',
					'gameNightInstance/:id': 'gameNightInstance',
					// default
					'*path' : 'defaultAction'
				}
			});

			var initialize = function() {
				var app_router = new AppRouter;

				app_router.on('route:gamenight', function(id) {
					var model = new GameNightModel({
						id : id
					});
					model.fetch({
						data : {
							includeGames : true
						},
						success : function(model) {
							var gameNightView = new GameNightView(model);
							gameNightView.render();
						}
					});
				});
				
				app_router.on("route:gameNightInstance", function (id) {
					var model = new GameNightInstanceModel({ id: id});
					model.fetch({
						success: function (model) {
							var gameNightInstanceView = new GameNightInstanceView(model);
							gameNightInstanceView.render();
						}
					});					
				});

				app_router.on('route:defaultAction', function() {
					var gameNightsIndexView = new GameNightIndexView({
						el : "#page"
					});
					gameNightsIndexView.render();
				});

				app_router.on('route:myCollection', function() {

				});

				app_router.on('route:home', function() {
					var homeView = new HomeView();
					homeView.render();
				});

				app_router.on('route:profile', function() {
					var model = new ProfileModel();
					model.fetch({
						success : function(model, response, options) {
							var profileView = new ProfileView({
								model : model
							});
							profileView.render();
						}
					});
				});
				
				app_router.on("route:scheduleGameNight", function () {
					var scheduleGameNightView = new ScheduleGameNightView();
					scheduleGameNightView.render();
				});

				Backbone.history.start();
			};

			return {
				initialize : initialize
			}
		});
