(function (ng) {
    /**
     * @ngdoc overview
     * @name accesorios.module:adminModule
     * @description
     * Definición del módulo de Angular de Accesorios. El módulo encapsula todos los 
     * controladores y los templates HTML que estén relacionados con los Accesorios 
     * directamente. En la configuración del módulo se injecta la dependencia de 
     * ui.router que es la que se utiliza para la configuración de las URLs bajo las
     * cuales se accede al módulo. Por ejemplo, para mostrar los autores en la 
     * URL: 'localhost:8080/admin' es necesario configurar el router por 
     * medio del stateProvider que informa a AngularJS de la relación entre la URL, 
     * un estado definido, el controlador y la vista 
     * correspondiente.
     */
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
            }).state('adminMarca', {
                url: '/adminMarca',
                views: {
                    'mainView': {
                        controller: 'adminMarcaCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'admin.marca.html'
                    }
                }
            });
        }]);

})(window.angular);


