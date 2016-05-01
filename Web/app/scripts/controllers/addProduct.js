'use strict';

/**
 * @ngdoc function
 * @name showcaseweb.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of the showcaseweb
 */
angular.module('showcaseweb')
  .controller('AddProductCtrl', function ($scope, $mdDialog) {
    var firebase = new Firebase("https://radiant-fire-5175.firebaseio.com/product");

    $scope.product = {};

    $scope.save = function () {
      firebase.push($scope.product);
      $scope.product = {};
      $mdDialog.hide();
    };

    $scope.cancel = function () {
      $mdDialog.hide();
    }
  });
