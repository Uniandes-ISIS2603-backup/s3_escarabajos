(function (ng) {
    var mod = ng.module('errorMod', ['ui.router']);
    var errorUrl = '/error';
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {

            $urlRouterProvider.otherwise("/carrito");

            $stateProvider.state('error', {
                url: errorUrl,
               params: {
                    mensaje: null,
                    status:null                    
                },views: {
                    mainView: {
                        templateUrl: "src/modules/errores/erroresDeServidor.html",
                        controller: "errorCtrl"
                    }
                }
            });
        }]);
})(window.angular);

