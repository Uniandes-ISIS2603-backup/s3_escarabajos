(function (ng) {
    /**
     * @ngdoc overview
     * @name carrito.module:CarritoMod
     * @description
     * Definición del módulo de Angular de Errores. El módulo encapsula todos los 
     * controladores y los templates HTML que estén relacionados con los Bicicletas 
     * directamente. En la configuración del módulo se injecta la dependencia de 
     * ui.router que es la que se utiliza para la configuración de las URLs bajo las
     * cuales se accede al módulo. Por ejemplo, para mostrar los autores en la 
     * URL: 'localhost:8080/error' es necesario configurar el router por 
     * medio del stateProvider que informa a AngularJS de la relación entre la URL, 
     * un estado definido (estado de mostrar autores), el controlador y la vista 
     * correspondiente.
     */
    var mod = ng.module('errorMod', ['ui.router']);
    var errorUrl = '/error';
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {

            $urlRouterProvider.otherwise("/carrito");

            $stateProvider.state('error', {
                url: errorUrl,
                params: {
                    mensaje: null,
                    status: null
                }, views: {
                    mainView: {
                        templateUrl: "src/modules/errores/erroresDeServidor.html",
                        controller: "errorCtrl"
                    }
                }
            });
        }]);
})(window.angular);

