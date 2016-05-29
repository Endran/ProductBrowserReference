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
    var self = this;
    self.productFirebase = firebase.database().ref("product");
    self.producerFirebase = firebase.database().ref("producer");

    self.producerFirebase.orderByChild("name").once('value', function (snapshot) {
      var values = snapshot.val();
      self.producers = [];
      for (var key in values) {
        var value = values[key];
        if (value) {
          value.key = key;
          self.producers.push(values[key]);
        }
      }
      $scope.$apply();
    }, /* onError */ function () {
    }, /* context */ this);

    $scope.product = {};

    $scope.save = function () {
      var selectedItem = $scope.producer.selectedItem;
      $scope.product.producerKey = selectedItem.value.key;
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
