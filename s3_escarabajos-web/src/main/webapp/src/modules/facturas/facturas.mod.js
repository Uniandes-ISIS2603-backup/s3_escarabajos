(function (ng) {
    
    /**
     * @ngdoc overview
     * @name facturas.module:accesorioModule
     * @description
     * Definición del módulo de Angular de Facturas. El módulo encapsula todos los 
     * controladores y los templates HTML que estén relacionados con los Facturas 
     * directamente. En la configuración del módulo se injecta la dependencia de 
     * ui.router que es la que se utiliza para la configuración de las URLs bajo las
     * cuales se accede al módulo. Por ejemplo, para mostrar los autores en la 
     * URL: 'localhost:8080/facturas' es necesario configurar el router por 
     * medio del stateProvider que informa a AngularJS de la relación entre la URL, 
     * un estado definido (estado de mostrar autores), el controlador y la vista 
     * correspondiente.
     */
    var mod = ng.module("moduloFacturas", ['ui.router']);

    mod.constant("facturaContext", "api/facturas");

    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {

            var basePath = 'src/modules/facturas/';

           // $urlRouterProvider.otherwise("/facturas/list");

            $stateProvider.state('facturas', {
                url: '/facturas',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'facturas.html',
                        controller: 'facturaCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('facturasList', {
                url: '/list',
                parent: 'facturas',
                views: {
                    'listView': {
                        templateUrl: basePath + 'facturas.list.html'
                    }
                }
            }).state('facturasCliente', {
                url: '/cliente',
                parent:'facturas',
                views: {
                    'listView': {
                         templateUrl: basePath + 'facturas.cliente.html',
                        controller: 'facturaClienteCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('facturaDelete', {
                parent: 'facturas',
                params:{
                    facturaId: null
                },
                views: {
                    'listView': {
                        templateUrl: basePath + 'facturas.list.html',
                        controller: 'facturaDeleteCtrl'
                    }
                }
            }).state('facturaUpdate', {
                url: '/update/{facturaId:int}',
                parent: 'facturas',
                params: {
                    facturaId: null
                },
                views: {
                    'listView': {
                        templateUrl: basePath + '/editar/facturas.edit.html',
                        controller: 'facturaEditCtrl'
                    }
                }
            });
        }]);
})(window.angular)