(function (ng) {
     /**
     * @ngdoc overview
     * @name mediosDePago.module:moduloMediosPago
     * @description
     * Definición del módulo de Angular de MediosPago. El módulo encapsula todos los 
     * controladores y los templates HTML que estén relacionados con los MediosPago 
     * directamente. En la configuración del módulo se injecta la dependencia de 
     * ui.router que es la que se utiliza para la configuración de las URLs bajo las
     * cuales se accede al módulo. Por ejemplo, para mostrar los autores en la 
     * URL: 'localhost:8080/api/clientes/mediosPago' es necesario configurar el router por 
     * medio del stateProvider que informa a AngularJS de la relación entre la URL, 
     * un estado definido (estado de mostrar autores), el controlador y la vista 
     * correspondiente.
     */
    var mod = ng.module("moduloMediosPago", ['ui.router']);

    mod.constant("mediosPagoContext", "api/clientes");

    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {

            var basePath = 'src/modules/mediosPago/';


            $stateProvider.state('mediosPago', {
                url: '/mediosPago',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'mediosPago.html',
                        controller: 'mediosPagoCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('mediosPagoList', {
                url: '/list',
                parent: 'mediosPago',
                views: {
                    'listView': {
                         templateUrl: basePath + 'mediosPago.list.html',
                        controller: 'mediosPagoCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('mediosCreate', {
                url: '/create',
                parent: 'mediosPago',
                views: {
                    'listView': {
                        templateUrl: basePath + 'crear/mediosPago.create.html',
                        controller: 'mediosPagoCreateCtrl'
                    }
                }
            }).state('mediosPagoDelete', {
                parent: 'mediosPago',
                params:{
                    medioPagoId: null
                },
                views: {
                    'listView': {
                        templateUrl: basePath + 'mediosPago.list.html',
                        controller: 'mediosPagoDeleteCtrl'
                    }
                }
            });
        }]);
})(window.angular)