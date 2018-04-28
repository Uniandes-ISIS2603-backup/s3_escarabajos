(function(ng) {
  var mod = angular.module('ListadeseosMod', ['ui.router']);

  mod.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise("/listadeseos");
    $stateProvider.state('listadeseos', {
        views: {
          mainView: {
            templateUrl: "src/modules/listaDeseos/listadeseos.view.html",
            controller: "listadeseosCtrl"
          }
        }
      }).state('deleteItemListadeseos', {
        
        params: {
            itemId: null
        },
        
        views: {
          mainView: {
            templateUrl: "src/modules/listaDeseos/listadeseos.view.html",
            controller: "deleteListaDeseosCtrl"
          }
        }
      }).state('vaciarListadeseos', {
                
        views: {
          mainView: {
            templateUrl: "src/modules/listaDeseos/listadeseos.view.html",
            controller: "vaciarListadeseosCtrl"
          }
        }
      }).state('addItemListaDeseos', {
        
        params: {
            itemId: null
        },
        
        views: {
          mainView: {
            templateUrl: "src/modules/listaDeseos/listadeseos.view.html",
            controller: "addListaDeseosCtrl"
          }
        }
        
      })
  }]);

})(window.angular);