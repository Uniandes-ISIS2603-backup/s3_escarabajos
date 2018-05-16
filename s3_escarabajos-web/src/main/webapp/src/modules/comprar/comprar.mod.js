(function (ng) {
    var mod = ng.module('moduloComprar', ['ui.router']);

    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {

            

            $stateProvider.state('pagar', {
                views: {
                    mainView: {
                        templateUrl: "src/modules/comprar/comprar.realizada.html",
                        controller: "comprarCtrl"
                    }
                }
            })
             }]);

})(window.angular);