'use strict';

/**
 * @ngdoc function
 * @name showcaseweb.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of the showcaseweb
 */
angular.module('showcaseweb')
  .controller('ProducersCtrl', function ($scope, $firebaseArray, $mdMedia, $mdDialog) {
    var self = this;
    self.firebaseProducers = new Firebase("https://radiant-fire-5175.firebaseio.com/producer");
    self.producers = $firebaseArray(self.firebaseProducers);

    var query = self.firebaseProducers.orderByChild("name");
    $scope.producers = $firebaseArray(query);

    // firebase.on("child_added", function (snapshot) {
    //   self.producers.push(snapshot.val());
    // });


    $scope.showAddProducer = function (ev) {
      $mdDialog.show({
        templateUrl: 'views/addProducer.html',
        parent: angular.element(document.body),
        targetEvent: ev,
        clickOutsideToClose: true,
        fullscreen: false
      });
    };

  });
