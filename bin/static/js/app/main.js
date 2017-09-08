requirejs.config({
	shim: {
		'js/lib/underscore': {
			exports: '_'
		},
		'js/lib/backbone': {
			deps: ['js/lib/underscore', 'js/lib/jquery'],
			exports: 'Backbone'
		}
	},
	baseUrl: 'js/lib',
	paths: {
		app: '../app',
		text: '../text',
		dataTables: "https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min"
	},
	waitSeconds: 15
});

require([
         'app/app'
         ], function(App) {
	App.initialize();
});
