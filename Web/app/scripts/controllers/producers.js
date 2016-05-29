'use strict';

/**
 * @ngdoc function
 * @name showcaseweb.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of the showcaseweb
 */
angular.module('showcaseweb')
  .controller('ProducersCtrl', function ($scope, $mdMedia, $mdDialog) {
    var firebaseProducers = firebase.database().ref("producer");


    var producersListener = function (snapshot) {
      var values = snapshot.val();
      $scope.producers = [];
      for (var key in values) {
        var value = values[key];
        if (value) {
          value.key = key;
          $scope.producers.push(values[key]);
        }
      }
      $scope.$apply();
    };

    firebaseProducers.orderByChild("name").on('value', producersListener);
    $scope.$on('$destroy', function () {
      firebaseProducers.off();
    });

    $scope.showAddProducer = function (ev) {
      $mdDialog.show({
        templateUrl: 'views/addProducer.html',
        parent: angular.element(document.body),
        targetEvent: ev,
        clickOutsideToClose: true,
        fullscreen: false
      });
    };

    $scope.deleteProducer = function (ev, producer) {
      var confirm = $mdDialog.confirm({
        onComplete: function afterShowAnimation() {
          var $dialog = angular.element(document.querySelector('md-dialog'));
          var $actionsSection = $dialog.find('md-dialog-actions');
          var $cancelButton = $actionsSection.children()[0];
          var $confirmButton = $actionsSection.children()[1];
          angular.element($confirmButton).addClass('md-raised md-warn');
        }
      })
        .title('Delete producer')
        .textContent('Would you like to delete producer ' + producer.name + '? This action cannot be undone!')
        .targetEvent(ev)
        .ok('Yes, I am sure')
        .cancel('Nope');
      $mdDialog.show(confirm).then(function () {
        firebaseProducers.child(producer.key).remove();
      });
    };
  });
