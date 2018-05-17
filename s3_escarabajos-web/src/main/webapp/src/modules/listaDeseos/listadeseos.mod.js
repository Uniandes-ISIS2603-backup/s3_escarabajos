(function () {
         /**
     * @ngdoc overview
     * @name listaDeDeseos.module:ListadeseosMod
     * @description
     * Definición del módulo de Angular de listaDeDeseos. El módulo encapsula todos los 
     * controladores y los templates HTML que estén relacionados con los listaDeDeseos 
     * directamente. En la configuración del módulo se injecta la dependencia de 
     * ui.router que es la que se utiliza para la configuración de las URLs bajo las
     * cuales se accede al módulo. Por ejemplo, para mostrar los autores en la 
     * URL: 'localhost:8080/api/clientes/{id: \\d+}/listaDeDeseos' es necesario configurar el router por 
     * medio del stateProvider que informa a AngularJS de la relación entre la URL, 
     * un estado definido (estado de mostrar listaDeDeseos), el controlador y la vista 
     * correspondiente.
     */             
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