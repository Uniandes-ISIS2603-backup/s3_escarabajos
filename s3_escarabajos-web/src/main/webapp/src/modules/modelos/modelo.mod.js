
(function (ng) {
    var mod = ng.module("modeloModule", ['ui.router']);
    mod.constant("modeloContext", "api/modelos");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/modelos/';
            $stateProvider.state('modelo', {
                url: '/catalogo',
                abstract: true,
                params: {
                    filtros:{marcas:[],categorias:[],colores:[],precioMin:0.0,precioMax:999999999.9,calificacionMin:0.0},
                    pagina:1,
                    tipo:null,
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
            });
        }]);
})(window.angular);
