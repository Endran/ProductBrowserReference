'use strict';

/**
 * @ngdoc function
 * @name showcaseweb.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the showcaseweb
 */
angular.module('showcaseweb')
  .controller('MainCtrl', function ($scope) {
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
  });
