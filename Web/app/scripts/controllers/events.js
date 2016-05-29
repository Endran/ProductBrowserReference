'use strict';

/**
 * @ngdoc function
 * @name showcaseweb.controller:EventsCtrl
 * @description
 * # EventsCtrl
 * Controller of the showcaseweb
 */
angular.module('showcaseweb')
  .controller('EventsCtrl', function ($scope, $mdMedia, $mdDialog) {
    var firebaseEvents = firebase.database().ref("event");
    firebaseEvents.orderByChild("name").on('value', function (snapshot) {
      var values = snapshot.val();
      $scope.events = [];
      for (var key in values) {
        var value = values[key];
        if (value) {
          value.key = key;
          $scope.events.push(values[key]);
        }
      }
      $scope.$apply();
    }, /* onError */ function () {
    }, /* context */ this);

    $scope.showAddEvent = function (ev) {
      $mdDialog.show({
        templateUrl: 'views/addEvent.html',
        parent: angular.element(document.body),
        targetEvent: ev,
        clickOutsideToClose: true,
        fullscreen: false
      });
    };

    $scope.deleteEvent = function (ev, event) {
      var confirm = $mdDialog.confirm({
        onComplete: function afterShowAnimation() {
          var $dialog = angular.element(document.querySelector('md-dialog'));
          var $actionsSection = $dialog.find('md-dialog-actions');
          var $cancelButton = $actionsSection.children()[0];
          var $confirmButton = $actionsSection.children()[1];
          angular.element($confirmButton).addClass('md-raised md-warn');
        }
      })
        .title('Delete event')
        .textContent('Would you like to delete event ' + event.name + '? This action cannot be undone!')
        .targetEvent(ev)
        .ok('Yes, I am sure')
        .cancel('Nope');
      $mdDialog.show(confirm).then(function () {
        firebaseEvents.child(event.key).remove();
      });
    };
  });
