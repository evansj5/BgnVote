define([
  // These are path alias that we configured in our bootstrap
  'jquery',     // lib/jquery/jquery
  'underscore', // lib/underscore/underscore
  'backbone',    // lib/backbone/backbone
  'app/models/userModel'
], function($, _, Backbone, UserModel){
  // Above we have passed in jQuery, Underscore and Backbone
  // They will not be accessible in the global scope
  var VoteModel = Backbone.Model.extend({
    url: 'rest/api/gameNightInstance/vote',
  });

  return VoteModel;
  // What we return here will be used by other modules
});
