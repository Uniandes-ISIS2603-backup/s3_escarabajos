(function (ng) {
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