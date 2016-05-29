'use strict';

describe('Controller: FestivalCtrl', function () {

  // load the controller's module
  beforeEach(module('showcaseweb'));

  var FestivalCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    FestivalCtrl = $controller('FestivalCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(FestivalCtrl.awesomeThings.length).toBe(3);
  });
});
