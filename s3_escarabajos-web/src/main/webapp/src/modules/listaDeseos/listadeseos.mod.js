(function () {
    var mod = angular.module('ListadeseosMod', ['ui.router']);
    var baseUrl = '/clientes/' + sessionStorage.getItem("id") + '/listaDeDeseos';
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            $urlRouterProvider.otherwise("/listadeseos");
            $stateProvider.state('listadeseos', {
                url: baseUrl + '/listaDeDesos',
                views: {
                    mainView: {
                        templateUrl: "src/modules/listaDeseos/listadeseos.view.html",
                        controller: "listadeseosCtrl"
                    }
                }
            }).state('deleteItemListadeseos', {
                url: baseUrl,
                params: {
                    itemId: null
                },

                views: {
                    url: baseUrl,

                    mainView: {
                        templateUrl: "src/modules/listaDeseos/listadeseos.view.html",
                        controller: "deleteListaDeseosCtrl"
                    }
                }
            }).state('vaciarListadeseos', {
                url: baseUrl,

                views: {
                    mainView: {
                        templateUrl: "src/modules/listaDeseos/listadeseos.view.html",
                        controller: "vaciarListadeseosCtrl"
                    }
                }
            }).state('addItemListaDeseos', {
                url: baseUrl,

                params: {
                    itemId: null,
                    color: null
                },

                views: {
                    mainView: {
                        templateUrl: "src/modules/listaDeseos/listadeseos.view.html",
                        controller: "addListaDeseosCtrl"
                    }
                }

            }).state('yaEstaLista', {
                url : baseUrl + '/error',
                views: {
                    'mainView': {
                        templateUrl: 'src/modules/listaDeseos/listadeseos.error.yaesta.view.html'
                    }
                }
            })
        }]);

})();