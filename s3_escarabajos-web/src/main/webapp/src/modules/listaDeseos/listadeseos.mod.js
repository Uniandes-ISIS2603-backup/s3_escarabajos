(function(ng) {
  var mod = angular.module('ListadeseosMod', ['ui.router']);

  mod.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider) {
    
    $stateProvider.state('listadeseos', {
        views: {
          mainView: {
            templateUrl: "src/modules/listaDeseos/listadeseos.view.html",
            controller: "listadeseosCtrl"
          }
        }
      })
  }]);

})(window.angular);