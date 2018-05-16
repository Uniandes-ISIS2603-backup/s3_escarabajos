(function (ng) {
    var mod = ng.module('errorMod', ['ui.router']);
    var errorUrl = '/error';
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {

            $urlRouterProvider.otherwise("/carrito");

            $stateProvider.state('error', {
                url: errorUrl,
                views: {
                    mainView: {
                        templateUrl: "src/modules/errores/erroresDeServidor.html",
                        controller: "carritoCtrl"
                    }
                }
            });
        }]);
})(window.angular);

