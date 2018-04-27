(function (ng) {
    //Tendria como dependecia el modulo del vendedor pero como no esta completo lo omitire
    var mod = ng.module("moduloBicisUsadas", ['ui.router']);

    mod.constant("biciUsadaContext", "api/clientes");

    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {

            var basePath = 'src/modules/bicisUsadas/';

            $urlRouterProvider.otherwise("/clientes/1/bicis/list");
            //Tendria que tener de parent:'vendedorDetail' pero como no esta completa la parte de vendedor 
            //pondre la url de un vendedor especifico
            $stateProvider.state('bicisUsada', {
                url: '/clientes/1/bicis',
                abstract: true,
                views: {
                    //Deberia ser en un childrenView pero como no esta completa la parte de vendedor se mostrara
                    //en el mainView
                    'mainView': {
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
            });
        }]);
})(window.angular)