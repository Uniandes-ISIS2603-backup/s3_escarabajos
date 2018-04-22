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
        
        params: {
            itemId: null
        },
        
        views: {
          mainView: {
            templateUrl: "src/modules/carrito/carrito.view.html",
            controller: "deleteCarritoCtrl"
          }
        }
      }).state('vaciarCarrito', {
                
        views: {
          mainView: {
            templateUrl: "src/modules/carrito/carrito.view.html",
            controller: "vaciarCarritoCtrl"
          }
        }
      })
  }]);

})(window.angular);