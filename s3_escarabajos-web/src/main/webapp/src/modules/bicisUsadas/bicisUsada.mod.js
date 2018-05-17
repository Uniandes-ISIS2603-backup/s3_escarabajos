(function (ng) {
    /**
     * @ngdoc overview
     * @name bicisUsada.module:bicicletaModule
     * @description
     * Definición del módulo de Angular de Bicicletas. El módulo encapsula todos los 
     * controladores y los templates HTML que estén relacionados con los Bicicletas 
     * directamente. En la configuración del módulo se injecta la dependencia de 
     * ui.router que es la que se utiliza para la configuración de las URLs bajo las
     * cuales se accede al módulo. Por ejemplo, para mostrar los autores en la 
     * URL: 'localhost:8080/bicicletas' es necesario configurar el router por 
     * medio del stateProvider que informa a AngularJS de la relación entre la URL, 
     * un estado definido (estado de mostrar autores), el controlador y la vista 
     * correspondiente.
     */
    var mod = ng.module("moduloBicisUsadas", ['clientesModule','ui.router']);

    mod.constant("biciUsadaContext", "api/clientes");

    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {

            var basePath = 'src/modules/bicisUsadas/';

            $urlRouterProvider.otherwise("/bicis/list");
            //Tendria que tener de parent:'vendedorDetail' pero como no esta completa la parte de vendedor 
            //pondre la url de un vendedor especifico
            
            $stateProvider.state('bicisUsada', {
                url: '/bicis',
                abstract: true,
                parent:'clientesDetail',
                views: {
                    //Deberia ser en un childrenView pero como no esta completa la parte de vendedor se mostrara
                    //en el mainView
                    'childrenView': {
                        templateUrl: basePath + 'bicisUsada.html'
                    }
                }
            }).state('bicisUsadaList', {
                url: '/list',
                parent: 'bicisUsada',
                views: {
                    'listView': {
                        templateUrl: basePath + 'bicisUsada.list.html',
                        controller:'biciUsadaCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('biciUsadaDetail', {
                url: '/{biciUsadaId:int}/detail',
                parent: 'bicisUsada',
                param: {
                    biciId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'bicisUsada.detail.html',
                        controller: 'biciUsadaDetailCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('biciUsadaCreate', {
                url: '/create',
                parent: 'bicisUsada',
                views: {
                    'listView': {
                        templateUrl: basePath + '/crear/bicisUsada.create.html',
                        controller: 'biciUsadaCreateCtrl'
                    }
                }
            }).state('biciUsadaDelete', {
                parent: 'bicisUsada',
                params:{
                    biciUsadaId: null
                },
                views: {
                    'listView': {
                        templateUrl: basePath + '/bicisUsada.list.html',
                        controller: 'biciUsadaDeleteCtrl'
                    }
                }
            }).state('biciUsadaUpdate', {
                url: '/update/{biciUsadaId:int}',
                parent: 'bicisUsada',
                params: {
                    biciUsadaId: null
                },
                views: {
                    'listView': {
                        templateUrl: basePath + '/editar/bicisUsada.edit.html',
                        controller: 'biciUsadaEditCtrl'
                    }
                }
            });
        }]);
})(window.angular)