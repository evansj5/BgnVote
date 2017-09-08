define([
  // These are path alias that we configured in our bootstrap
  'jquery',     // lib/jquery/jquery
  'underscore', // lib/underscore/underscore
  'backbone',    // lib/backbone/backbone
  'app/models/userModel'
], function($, _, Backbone, UserModel){
  // Above we have passed in jQuery, Underscore and Backbone
  // They will not be accessible in the global scope
  var ProfileModel = Backbone.Model.extend({
    url: 'rest/api/profile',
  });

  return ProfileModel;
  // What we return here will be used by other modules
});
