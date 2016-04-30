'use strict';

/**
 * @ngdoc function
 * @name showcaseweb.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of the showcaseweb
 */
angular.module('showcaseweb')
  .controller('AddProducerCtrl', function ($scope, $firebaseObject) {
    var firebase = new Firebase("https://radiant-fire-5175.firebaseio.com/producer");
    // var syncObject = $firebaseObject(firebase);
    // syncObject.$bindTo($scope, "producer");
    // $scope.producer = $firebaseObject(firebase);

    $scope.producer = {};

    $scope.save = function () {
      firebase.push($scope.producer);

    };
  });
