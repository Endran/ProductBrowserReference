'use strict';

/**
 * @ngdoc function
 * @name showcaseweb.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of the showcaseweb
 */
angular.module('showcaseweb')
  .controller('AddProductCtrl', function ($scope, $firebaseArray, $mdDialog, $q) {
    var self = this;
    self.productFirebase = new Firebase("https://radiant-fire-5175.firebaseio.com/product");
    self.producerFirebase = new Firebase("https://radiant-fire-5175.firebaseio.com/producer");
    var query = self.producerFirebase.orderByChild("name");
    self.producers = $firebaseArray(query);

    $scope.product = {};

    $scope.save = function () {
      var selectedItem = $scope.producer.selectedItem;
      $scope.product.producerId = selectedItem.value.$id;
      self.productFirebase.push($scope.product);
      $scope.product = {};
      $mdDialog.hide();
    };

    $scope.cancel = function () {
      $mdDialog.hide();
    }

    $scope.producer = {};
    $scope.producer.querySearch = queryProducers;

    function queryProducers(query) {
      var result = [];
      if (self.producers) {
        result = self.producers.filter(function (producer) {
          return producer.name.toLowerCase().indexOf(query.toLowerCase()) > -1;
        }).map(function (producer) {
          return {
            value: producer,
            display: producer.name
          };
        });
      }
      console.log(JSON.stringify(result));
      return result;
    }
  });
