'use strict';

/**
 * @ngdoc overview
 * @name showcaseweb
 * @description
 * # showcaseweb
 *
 * Main module of the application.
 */
angular
  .module('showcaseweb', [
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngMessages',
    'ngMaterial'
  ])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl',
        controllerAs: 'main'
      })
      .when('/about', {
        templateUrl: 'views/about.html',
        controller: 'AboutCtrl',
        controllerAs: 'about'
      })
      .when('/addProducer', {
        templateUrl: 'views/addProducer.html',
        controller: 'AddProducerCtrl',
        controllerAs: 'addProducer'
      })
      .otherwise({
        redirectTo: '/'
      });
  })
  .config(function ($mdThemingProvider) {
    $mdThemingProvider.theme('default')
      .primaryPalette('amber')
      .accentPalette('red');
  });
