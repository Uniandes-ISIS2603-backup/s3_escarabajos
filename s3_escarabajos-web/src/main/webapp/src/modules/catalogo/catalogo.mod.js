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
 * |                  |                            |                         |
 * | catalogo         | /catalogo/                 |catalogoView:            |
 * |                  |                            | /catalogo/catalogo.html |
 * |                  |                            |                         |
 * | bicicletasParent | /catalogo/bicicletas       | listParentView:         |
 * |                  |                            | /list/lists.html        |
 * |                  |                            |                         |
 * | bicicletasNoAdv  | /catalogo/bicicletas       | noAdvView:              |
 * |                  |                            | /list/noAdv.html        |
 * |                  |                            |                         |
 * | bicicletasAdv    | /catalogo/bicicletas       | advView:                |
 * |                  |                            | /list/adv.html          |
 * |                  |                            |                         |
 * | accesoriosParent | /catalogo/accesorios       | listParentView:         |
 * |                  |                            | /list/lists.html        |
 * |                  |                            |                         |
 * | accesoriosNoAdv  | /catalogo/accesorios       | noAdvView               |
 * |                  |                            | /list/noAdv.html        |
 * |                  |                            |                         |
 * | accesoriosAdv    | /catalogo/accesorios       | advView:                |
 * |                  |                            | /list/adv.html          |
 * |------------------|----------------------------|-------------------------|
 *```
 */
(function (ng) {
    var mod = ng.module("catalogoModule", ['ui.router']);
    mod.constant("catalogoContext", "api/catalogo");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/catalogo/';
//            $urlRouterProvider.otherwise("/catalogo/");
            $stateProvider.state('catalogoParent', {
                url: '/catalogo',
                abstract: true,
                params: {
                   paginaParam: 1,
                   filasParam: 3,
                   filtrosParam: {"categorias":[],"marcas":[],"precios":{"min":0,"max":-1},"calificacion":0.0,"colores":[]} ///Cambiar esto para que sea el filtro de mayor calificacion desc.
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'catalogoParent.html'
                    }
                }
            }).state('catalogo', {
                url: '/',
                parent: 'catalogoParent',
                views: {
                    'catalogoView': {
                        templateUrl: basePath + 'catalogo/catalogo.html',
                        controller: 'catalogoCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('listParent', {
                parent: 'catalogoParent',
                abstract: true,
                views: {
                    'listParentView': {
                        templateUrl: basePath + 'list/lists.html'
                    }
                }
            }).state('bicicletasParent', {
                url: '/bicicletas',
                parent: 'listParent',
                abstract: true,
                views: {
                    'bicicletasParentView': {
                        templateUrl: basePath + 'list/bicicletas/bicicletas.html'
                    }
                }
            }).state('bicicletasNoAdv', {
                parent: 'bicicletasParent',
                views: {
                    'bicicletasNoAdvView': {
                        templateUrl: basePath + 'list/bicicletas/catalogo.bicicletas.noAdv.html',
                        controller: 'catalogoBicicletasNoAdvCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('bicicletasAdv', {
                parent: 'bicicletasParent',
                views: {
                    'bicicletasAdvView': {
                        templateUrl: basePath + 'list/bicicletas/catalogo.bicicletas.adv.html',
                        controller: 'catalogoBicicletasAdvCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('accesoriosParent', {
                url: '/accesorios',
                parent: 'listParent',
                abstract: true,
                views: {
                    'accesoriosParentView': {
                        templateUrl: basePath + 'list/accesorios/accesorios.html'
                    }
                }
            }).state('accesoriosNoAdv', {
                parent: 'accesoriosParent',
                views: {
                    'accesoriosNoAdvView': {
                        templateUrl: basePath + 'list/accesorios/catalogo.accesorios.noadv.html',
                        controller: 'catalogoAccesoriosNoAdvCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('accesoriosAdv', {
                parent: 'accesoriosParent',
                views: {
                    'accesoriosAdvView': {
                        templateUrl: basePath + 'list/accesorios/catalogo.accesorios.adv.html',
                        controller: 'catalogoAccesoriosAdvCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
        }]);
})(window.angular);
