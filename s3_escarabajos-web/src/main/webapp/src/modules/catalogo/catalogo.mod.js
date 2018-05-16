
(function (ng) {
    var mod = ng.module("catalogoModule", ['ui.router']);
    mod.constant("catalogoContext", "api/catalogo");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider) {
            var basePath = 'src/modules/catalogo/';
            $stateProvider.state('catalogo', {
                url: '/catalogo',
                abstract: true,
                params: {
                    filtros: {marcas: [], categorias: [], colores: [], precioMin: 0.0, precioMax: 999999999.9, calificacionMin: 0.0},
                    pagina: 1,
                    tipo: null,
                    adv: true
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'catalogo.html',
                        controller: 'catalogoCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('adv', {
                parent: 'catalogo',
                views: {
                    'filtrosView': {
                        templateUrl: basePath + 'filtros.html',
                        controller: 'filtrosCtrl',
                        controllerAs: 'ctrl'
                    },
                    'catalogoView': {
                        templateUrl: basePath + 'modelos.html',
                        controller: 'advCtrl',
                        controllerAs: 'ctrl'
                    }, 'advView': {
                        templateUrl: basePath + 'propagandas.html',
                        controller: 'propagandasCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('noAdv', {
                parent: 'catalogo',
                views: {
                    'filtrosView': {
                        templateUrl: basePath + 'filtros.html',
                        controller: 'filtrosCtrl',
                        controllerAs: 'ctrl'
                    },
                    'catalogoView': {
                        templateUrl: basePath + 'modelos.html',
                        controller: 'noAdvCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('principal', {
                parent: 'catalogo',
                views: {
                    'principalView': {
                        templateUrl: basePath + 'modelos.html',
                        controller: 'principalCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('busqueda', {
                params: {
                    busqueda: null
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'modelos.html',
                        controller: 'busquedaCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
        }]);
})(window.angular);
