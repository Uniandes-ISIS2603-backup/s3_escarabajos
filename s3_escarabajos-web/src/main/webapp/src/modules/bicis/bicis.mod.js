(function (ng) {
    /**
     * @ngdoc overview
     * @name bicicletas.module:bicicletaModule
     * @description
     * Definición del módulo de Angular de Bicicletas. El módulo encapsula todos los 
     * controladores y los templates HTML que estén relacionados con los Bicicletas 
     * directamente. En la configuración del módulo se injecta la dependencia de 
     * ui.router que es la que se utiliza para la configuración de las URLs bajo las
     * cuales se accede al módulo. Por ejemplo, para mostrar los autores en la 
     * URL: 'localhost:8080/bicicletas' es necesario configurar el router por 
     * medio del stateProvider que informa a AngularJS de la relación entre la URL, 
     * un estado definido (estado de mostrar autores), el controlador y la vista 
     * correspondiente.
     */
    var mod = ng.module("moduloBicis", ['ui.router']);

    mod.constant("biciContext", "api/bicis");

    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {

            var basePath = 'src/modules/bicis/';

            $urlRouterProvider.otherwise("/bicis/list");

            $stateProvider.state('bicis', {
                url: '/bicis',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'bicis.html',
                        controller: 'biciCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('bicisList', {
                url: '/list',
                parent: 'bicis',
                views: {
                    'listView': {
                        templateUrl: basePath + 'bicis.list.html'
                    }
                }
            }).state('biciDetail', {
                url: '/{biciId:int}/detail',
                parent: 'bicis',
                data: {},
                param: {
                    biciId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'bicis.detail.html',
                        controller: 'biciDetailCtrl',
                        controllerAs: 'ctrl'
                    },
                    'calificacionView': {
                        templateUrl: 'src/modules/calificaciones/calificaciones.list.html',
                        controller: 'calificacionesListCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('biciCreate', {
                url: '/create',
                parent: 'bicis',
                views: {
                    'listView': {
                        templateUrl: basePath + '/crear/bicis.create.html',
                        controller: 'biciCreateCtrl'
                    }
                }
            }).state('biciDelete', {
                parent: 'bicis',
                params: {
                    biciId: null
                },
                views: {
                    'listView': {
                        templateUrl: basePath + 'bicis.list.html',
                        controller: 'biciDeleteCtrl'
                    }
                }
            }).state('biciUpdate', {
                url: '/update/{biciId:int}',
                parent: 'bicis',
                params: {
                    biciId: null
                },
                views: {
                    'listView': {
                        templateUrl: basePath + '/editar/bicis.edit.html',
                        controller: 'biciEditCtrl'
                    }
                }
            })
        }]);
})(window.angular)