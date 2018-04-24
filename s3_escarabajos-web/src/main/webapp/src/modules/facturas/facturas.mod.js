(function (ng) {
    var mod = ng.module("moduloFacturas", ['ui.router']);

    mod.constant("facturaContext", "api/facturas");

    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {

            var basePath = 'src/modules/facturas/';

            $urlRouterProvider.otherwise("/facturas/list");

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
            }).state('facturaDetail', {
                url: '/{facturaId:int}/detail',
                parent: 'facturas',
                param: {
                    biciId: null
                },
                views: {
                    'listView': {
                        templateUrl: basePath + 'facturas.list.html',
                        controller: 'facturaDetailCtrl',
                        controllerAs: 'ctrl'
                    },
                    'detailView': {
                        templateUrl: basePath + 'facturas.detail.html',
                        controller: 'facturaDetailCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('facturaCreate', {
                url: '/create',
                parent: 'facturas',
                views: {
                    'listView': {
                        templateUrl: basePath + '/new/facturas.new.html',
                        controller: 'facturaNewCtrl'
                    }
                }
            });
        }]);
})(window.angular)