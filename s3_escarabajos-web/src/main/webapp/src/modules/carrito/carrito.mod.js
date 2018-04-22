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
      }).state('deleteItemCarrito', {
          
        param: {
            itemId: null
        },
        views: {
          mainView: {
            templateUrl: "src/modules/carrito/carrito.view.html",
            controller: "deleteItemCarritoCtrl"
          }
        }
      })
  }]);

})(window.angular);