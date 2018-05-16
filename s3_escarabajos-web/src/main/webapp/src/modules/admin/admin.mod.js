(function (ng) {
var mod = ng.module("adminModule", []);
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/admin/';
            
            $stateProvider.state('adminList', {
                url: '/admin',
                views: {
                    'mainView': {
                        controller: 'adminCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'admin.list.html'
                    }
                }
            }).state('adminCategorias', {
                url: '/admin',
                views: {
                    'mainView': {
                        controller: 'adminCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'admin.modeloLista.html'
                    }
                }
            }).state('adminModelosDetail', {
                url: '/admin',
                views: {
                    'mainView': {
                        controller: 'modelosDetailsCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'admin.modelosDetail.html'
                    }
                }
            }).state('adminCreate', {
                url: '/modeloCreate',
                views: {
                    'mainView': {
                        controller: 'adminCreateCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'admin.createModelo.html'
                    }
                }
            })
            .state('adminPromocion', {
                url: '/adminPromocion',
                views: {
                    'mainView': {
                        controller: 'adminPromocionesCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'admin.promocion.html'
                    }
                }
            }).state('adminCategoria', {
                url: '/adminCategoria',
                views: {
                    'mainView': {
                        controller: 'adminCategoriaCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'admin.categoria.html'
                    }
                }
            }).state('adminModelo', {
                url: '/adminCategoria',
                views: {
                    'mainView': {
                        controller: 'adminModeloCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'admin.modelo.html'
                    }
                }
            });
        }]);

})(window.angular);


