(function (ng) {
    /**
     * @ngdoc overview
     * @name accesorios.module:accesorioModule
     * @description
     * Definición del módulo de Angular de Accesorios. El módulo encapsula todos los 
     * controladores y los templates HTML que estén relacionados con los Accesorios 
     * directamente. En la configuración del módulo se injecta la dependencia de 
     * ui.router que es la que se utiliza para la configuración de las URLs bajo las
     * cuales se accede al módulo. Por ejemplo, para mostrar los autores en la 
     * URL: 'localhost:8080/accesorios' es necesario configurar el router por 
     * medio del stateProvider que informa a AngularJS de la relación entre la URL, 
     * un estado definido (estado de mostrar autores), el controlador y la vista 
     * correspondiente.
     */
    var mod = ng.module("AccesoriosMod", ['ui.router']);

    mod.constant("accesorioContext", "api/accesorios");

    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {

            var basePath = 'src/modules/accesorios/';

            $urlRouterProvider.otherwise("/accesorios/list");

            $stateProvider.state('accesorios', {
                url: '/accesorios',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'accesorios.html',
                        controller: 'accesorioCtrl',
                    }
                }
            }).state('accesoriosList', {
                url: '/list',
                parent: 'accesorios',
                views: {
                    'listView': {
                        templateUrl: basePath + 'accesorios.list.html'
                    }
                }
            }).state('accesorioDetail', {
                url: '/{accesorioId:int}/detail',
                parent: 'accesorios',
                param: {
                    accesorioId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'accesorios.detail.html',
                        controller: 'accesorioDetailCtrl'
                    },
                    'calificacionView': {
                        templateUrl: 'src/modules/calificaciones/calificaciones.list.html',
                        controller: 'calificacionesListCtrl2',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('accesorioCreate', {
                url: '/create',
                parent: 'accesorios',
                views: {
                    'listView': {
                        templateUrl: basePath + 'crear/accesorios.create.html',
                        controller: 'accesorioCreateCtrl'
                    }
                }
            }).state('accesorioDelete', {
                parent: 'accesorios',
                params: {
                    itemId: null
                },
                views: {
                    'listView': {
                        templateUrl: basePath + 'accesorios.list.html',
                        controller: 'accesorioDeleteCtrl'
                    }
                }
            }).state('accesorioUpdate', {
                url: '/update/{accesorioId:int}',
                parent: 'accesorios',
                params: {
                    accesorioId: null
                },
                views: {
                    'listView': {
                        templateUrl: basePath + '/editar/accesorios.edit.html',
                        controller: 'accesorioEditCtrl'
                    }
                }
            });
        }]);
})(window.angular)