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
    // self.producers = $firebaseArray(self.firebaseProducers);

    var query = self.firebaseProducers.orderByChild("name");
    self.producers = $firebaseArray(query);
    $scope.producers = self.producers;


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

    $scope.deleteProducer = function (ev, producer) {
      var confirm = $mdDialog.confirm({
          onComplete: function afterShowAnimation() {
            var $dialog = angular.element(document.querySelector('md-dialog'));
            var $actionsSection = $dialog.find('md-dialog-actions');
            var $cancelButton = $actionsSection.children()[0];
            var $confirmButton = $actionsSection.children()[1];
            angular.element($confirmButton).addClass('md-raised md-warn');
            // angular.element($cancelButton).addClass('md-raised');
          }
        })
        .title('Delete producer')
        .textContent('Would you like to delete this producer? This action cannot be undone!')
        .targetEvent(ev)
        .ok('Yes, I am sure')
        .cancel('Nope');
      $mdDialog.show(confirm).then(function () {
        self.producers.$remove(producer)
      });
    };
  });
