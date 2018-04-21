/**
 * @ngdoc overview
 * @name books.module:bookModule
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
            $urlRouterProvider.otherwise("/catalogo/");
            $stateProvider.state('catalogoParent', {
                url: '/catalogo',
                abstract: true,
                views: {
                    'mainView': {
                        template: '<ui-view/>'
                    }
                }
            }).state('catalogo', {
                 url: '/',
                parent: 'catalogo',
                param: {
                    catalogoPg: 1,
                    catalogoQty: 1
                },
                views: {
                    'catalogoView': {
                        templateUrl: basePath + '/catalogo/catalogo.html',
                        controller: 'catalogoCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('bicicletasParent', {
                url: '/bicicletas',
                parent: 'catalogo',
                abstract: true,
                views: {
                    'listParentView': {
                        templateUrl: basePath + '/list/lists.html'
                    }
                }
            }).state('bicicletasNoAdv', {
                parent: 'bicicletasParent',
                param: {
                    bicicletasNoAdvPg: 1,
                    bicicletasNoAdvRws:3,
                    bicicletasNoAdvFilters: ""
                },
                views: {
                    'noAdvView': {
                        templateUrl: basePath + '/list/noAdv.html',
                        controller: 'catalogoBicicletasNoAdvCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('bicicletasAdv', {
                parent: 'bicicletasParent',
                param: {
                    bicicletasAdvPg: 1,
                    bicicletasAdvRws:3,
                    bicicletasAdvFilters: ""
                },
                views: {
                    'advView': {
                        templateUrl: basePath + '/list/adv.html',
                        controller: 'catalogoBicicletasAdvCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('accesoriosParent', {
                url: '/accesorios',
                parent: 'catalogo',
                abstract: true,
                views: {
                    'listParentView': {
                        templateUrl: basePath + '/list/lists.html'
                    }
                }
            }).state('accesoriosNoAdv', {
                parent: 'accesoriosParent',
                param: {
                    accesoriosNoAdvPg: 1,
                    accesoriosNoAdvRws:3,
                    accesoriosNoAdvFilters: ""
                },
                views: {
                    'noAdvView': {
                        templateUrl: basePath + '/list/noAdv.html',
                        controller: 'catalogoAccesoriosNoAdvCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('accesoriosAdv', {
                parent: 'accesoriosParent',
                param: {
                    accesoriosAdvPg: 1,
                    accesoriosAdvRws:3,
                    accesoriosAdvFilters: ""
                },
                views: {
                    'advView': {
                        templateUrl: basePath + '/list/adv.html',
                        controller: 'catalogoAccesoriosAdvCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
        }]);
})(window.angular);
