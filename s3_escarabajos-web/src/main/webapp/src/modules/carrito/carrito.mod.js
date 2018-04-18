(function(ng) {
  var mod = angular.module('CarritoMod', ['ui.router']);

  mod.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider) {
    
    $stateProvider.state('carrito', {
        views: {
          mainView: {
            templateUrl: "src/modules/carrito/carrito.view.html",
            controller: "carritoCtrl"
          }
        }
      })
  }]);

})(window.angular);