'use strict';

/**
 * @ngdoc function
 * @name showcaseweb.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of the showcaseweb
 */
angular.module('showcaseweb')
  .controller('SideMenuCtrl', function ($scope, $mdSidenav) {
    $scope.toggleLeftMenu = buildToggler('leftMenu');
    
    function buildToggler(navID) {
      return function() {
        $mdSidenav(navID)
          .toggle()
      }
    }
  });
