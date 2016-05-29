'use strict';

/**
 * @ngdoc function
 * @name showcaseweb.controller:FestivalCtrl
 * @description
 * # FestivalCtrl
 * Controller of the showcaseweb
 */
angular.module('showcaseweb')
  .controller('FestivalCtrl', function ($scope, $mdMedia, $mdDialog) {
    var firebaseFestivals = firebase.database().ref("festival");
    firebaseFestivals.orderByChild("name").on('value', function (snapshot) {
      var values = snapshot.val();
      $scope.festivals = [];
      for (var key in values) {
        var value = values[key];
        if (value) {
          value.key = key;
          $scope.festivals.push(values[key]);
        }
      }
      $scope.$apply();
    }, /* onError */ function () {
    }, /* context */ this);

    $scope.showAddFestival = function (ev) {
      $mdDialog.show({
        templateUrl: 'views/addFestival.html',
        parent: angular.element(document.body),
        targetEvent: ev,
        clickOutsideToClose: true,
        fullscreen: false
      });
    };

    $scope.deleteFestival = function (ev, festival) {
      var confirm = $mdDialog.confirm({
        onComplete: function afterShowAnimation() {
          var $dialog = angular.element(document.querySelector('md-dialog'));
          var $actionsSection = $dialog.find('md-dialog-actions');
          var $cancelButton = $actionsSection.children()[0];
          var $confirmButton = $actionsSection.children()[1];
          angular.element($confirmButton).addClass('md-raised md-warn');
        }
      })
        .title('Delete festival')
        .textContent('Would you like to delete festival ' + festival.name + '? This action cannot be undone!')
        .targetEvent(ev)
        .ok('Yes, I am sure')
        .cancel('Nope');
      $mdDialog.show(confirm).then(function () {
        firebaseFestivals.child(festival.key).remove();
      });
    };
  });
