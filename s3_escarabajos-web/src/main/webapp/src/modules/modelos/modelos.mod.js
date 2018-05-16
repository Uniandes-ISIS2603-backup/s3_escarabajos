
(function (ng) {
    var mod = ng.module("modelosModule", ['ui.router']);
    mod.constant("modelosContext", "api/modelos");
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
                    modeloId: null,
                    exception: false
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'modelos.detail.html',
                        controller: 'modeloDetailCtrl',
                        controllerAs: 'ctrl'
                    },
                    'clasificacionesView': {
                        templateUrl: 'src/modules/calificaciones/calificaciones.list.html',
                        controller: 'calificacionesListCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
        }]);
})(window.angular);
