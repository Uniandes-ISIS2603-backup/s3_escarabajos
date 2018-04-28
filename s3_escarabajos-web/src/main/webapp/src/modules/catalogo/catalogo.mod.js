/**
 * @ngdoc overview
 * @name catalogo.module:catalogoModule
 * @description
 * Definición del módulo de Angular del catalogo. El módulo encapsula todos los 
 * controladores y los templates HTML que estén relacionados con los modelos
 * directamente. En la configuración del módulo se injecta la dependencia de 
 * ui.router que es la que se utiliza para la configuración de las URLs bajo las
 * cuales se accede al módulo. Por ejemplo, para mostrar el catalog en 
 * URL: 'localhost:8080/catalogo/main' es necesario configurar el router por 
 * medio del stateProvider que informa a AngularJS de la relación entre la URL, 
 * un estado definido (estado de mostrar modelos), el controlador y la vista 
 * correspondiente. Los estados definidos en este modulo son:
 * ```
 * | ESTADO           | URL                        | VISTAS                  |
 * |------------------|----------------------------|-------------------------|
 * | catalogoParent   | /catalogo                  | mainView:               |
 * |                  |                            | '<ui-view/>'            |
 * |                  |                            |                         |     |
 * |------------------|----------------------------|-------------------------|
 *```
 */
(function (ng) {
    var mod = ng.module("catalogoModule", ['ui.router']);
    mod.constant("catalogoContext", "api/catalogo");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/catalogo/';
            $stateProvider.state('catalogo', {
                url: '/catalogo',
                abstract: true,
                params: {
                    filtros:{marcas:[],categorias:[],colores:[],precioMin:null,precioMax:null,calificacionMin:0.0},
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
