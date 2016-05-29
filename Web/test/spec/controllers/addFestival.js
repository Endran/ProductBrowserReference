'use strict';

describe('Controller: AddfestivalCtrl', function () {

  // load the controller's module
  beforeEach(module('showcaseweb'));

  var AddfestivalCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    AddfestivalCtrl = $controller('AddfestivalCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(AddfestivalCtrl.awesomeThings.length).toBe(3);
  });
});
