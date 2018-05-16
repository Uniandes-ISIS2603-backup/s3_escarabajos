(function () {
    var mod = angular.module('ListadeseosMod', ['ui.router']);
    var baseUrl = '/clientes/' + sessionStorage.getItem("id");
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
                url: baseUrl + '/listaDeDesos',
                params: {
                    itemId: null
                },

                views: {
                    url: baseUrl + '/listaDeDesos',

                    mainView: {
                        templateUrl: "src/modules/listaDeseos/listadeseos.view.html",
                        controller: "deleteListaDeseosCtrl"
                    }
                }
            }).state('vaciarListadeseos', {
                url: baseUrl + '/listaDeDesos',

                views: {
                    mainView: {
                        templateUrl: "src/modules/listaDeseos/listadeseos.view.html",
                        controller: "vaciarListadeseosCtrl"
                    }
                }
            }).state('addItemListaDeseos', {
                url: baseUrl + '/listaDeDesos',

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
                views: {
                    'mainView': {
                        templateUrl: 'src/modules/listaDeseos/listadeseos.error.yaesta.view.html'
                    }
                }
            })
        }]);

})();