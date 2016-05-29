'use strict';

/**
 * @ngdoc function
 * @name showcaseweb.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of the showcaseweb
 */
angular.module('showcaseweb')
  .controller('AddEventCtrl', function ($scope, $mdDialog) {
    var firebaseEvents = firebase.database().ref("event");

    $scope.event = {};

    $scope.save = function () {
      firebaseEvents.push($scope.event);
      $scope.event = {};
      $mdDialog.hide();
    };

    $scope.cancel = function () {
      $mdDialog.hide();
    }
  });
