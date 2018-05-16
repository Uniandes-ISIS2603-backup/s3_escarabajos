(function (ng) {
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