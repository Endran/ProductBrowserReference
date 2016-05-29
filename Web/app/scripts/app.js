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
    'ngMaterial',
    'firebase'
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
      .when('/producers', {
        templateUrl: 'views/producers.html',
        controller: 'ProducersCtrl',
        controllerAs: 'producers'
      })
      .when('/addProducer', {
        templateUrl: 'views/addProducer.html',
        controller: 'AddProducerCtrl',
        controllerAs: 'addProducer'
      })
      .when('/products', {
        templateUrl: 'views/products.html',
        controller: 'ProductsCtrl',
        controllerAs: 'products'
      })
      .when('/addProduct', {
        templateUrl: 'views/addProduct.html',
        controller: 'AddProductCtrl',
        controllerAs: 'addProduct'
      })
      .when('/festival', {
        templateUrl: 'views/festival.html',
        controller: 'FestivalCtrl',
        controllerAs: 'festival'
      })
      .when('/addFestival', {
        templateUrl: 'views/addFestival.html',
        controller: 'AddFestivalCtrl',
        controllerAs: 'addFestival'
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
