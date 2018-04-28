
(function (ng) {
    var mod = ng.module("modeloModule", ['ui.router']);
    mod.constant("modeloContext", "api/modelos");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/modelos/';
            $stateProvider.state('modelos', {
                url: '/modelos',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'modelos.html',
                    }
                }
            }).state('modeloDetail', {
                url: '/{modeloId:int}/detail',
                parent: 'modelos',
                params: {
                    modeloId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'modelos.detail.html',
                        controller: 'modeloDetailCtrl',
                        controllerAs: 'ctrl'
                    },
                    'clasificacionesView': {
                        templateUrl: 'src/modules/calificaciones/calificaciones.list.html',
                        controller: 'calificacionCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
        }]);
})(window.angular);
