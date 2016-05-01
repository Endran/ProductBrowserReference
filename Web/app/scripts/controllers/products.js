'use strict';

/**
 * @ngdoc function
 * @name showcaseweb.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of the showcaseweb
 */
angular.module('showcaseweb')
  .controller('ProductsCtrl', function ($scope, $firebaseArray, $mdMedia, $mdDialog) {
    var self = this;
    self.firebaseProducts = new Firebase("https://radiant-fire-5175.firebaseio.com/product");

    var query = self.firebaseProducts.orderByChild("name");
    self.products = $firebaseArray(query);
    $scope.products = self.products;

    $scope.showAddProduct = function (ev) {
      $mdDialog.show({
        templateUrl: 'views/addProduct.html',
        parent: angular.element(document.body),
        targetEvent: ev,
        clickOutsideToClose: true,
        fullscreen: false
      });
    };

    $scope.deleteProduct = function (ev, product) {
      var confirm = $mdDialog.confirm({
          onComplete: function afterShowAnimation() {
            var $dialog = angular.element(document.querySelector('md-dialog'));
            var $actionsSection = $dialog.find('md-dialog-actions');
            var $cancelButton = $actionsSection.children()[0];
            var $confirmButton = $actionsSection.children()[1];
            angular.element($confirmButton).addClass('md-raised md-warn');
          }
        })
        .title('Delete product')
        .textContent('Would you like to delete product ' + product.name + '? This action cannot be undone!')
        .targetEvent(ev)
        .ok('Yes, I am sure')
        .cancel('Nope');
      $mdDialog.show(confirm).then(function () {
        self.products.$remove(product)
      });
    };
  });
