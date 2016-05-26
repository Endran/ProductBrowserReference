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
    var firebaseProducers = firebase.database().ref("producer");

    $scope.producer = {};

    $scope.save = function () {
      firebaseProducers.push($scope.producer);
      $scope.producer = {};
      $mdDialog.hide();
    };

    $scope.cancel = function () {
      $mdDialog.hide();
    }
  });
