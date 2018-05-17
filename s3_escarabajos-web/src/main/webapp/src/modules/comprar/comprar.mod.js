(function (ng) {
    /**
     * @ngdoc overview
     * @name comprar.module:clientesModule
     * @description
     * Definición del módulo de Angular de comprar. El módulo encapsula todos los 
     * controladores y los templates HTML que estén relacionados con los Comprar 
     * directamente. En la configuración del módulo se injecta la dependencia de 
     * ui.router que es la que se utiliza para la configuración de las URLs bajo las
     * cuales se accede al módulo. Por ejemplo, para mostrar los autores en la 
     * URL: 'localhost:8080/clientes' es necesario configurar el router por 
     * medio del stateProvider que informa a AngularJS de la relación entre la URL, 
     * un estado definido (estado de mostrar autores), el controlador y la vista 
     * correspondiente.
     */
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