'use strict';

/**
 * @ngdoc function
 * @name showcaseweb.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of the showcaseweb
 */
angular.module('showcaseweb')
  .controller('AddProducerCtrl', function ($scope, $mdDialog) {
    var firebase = new Firebase("https://radiant-fire-5175.firebaseio.com/producer");

    $scope.producer = {};

    $scope.save = function () {
      firebase.push($scope.producer);
      $scope.producer = {};
      $mdDialog.hide();
    };

    $scope.cancel = function () {
      $mdDialog.hide();
    }
  });
