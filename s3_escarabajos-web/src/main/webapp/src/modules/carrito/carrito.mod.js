(function (ng) {
    /**
     * @ngdoc overview
     * @name carrito.module:CarritoMod
     * @description
     * Definición del módulo de Angular de Bicicletas. El módulo encapsula todos los 
     * controladores y los templates HTML que estén relacionados con los Bicicletas 
     * directamente. En la configuración del módulo se injecta la dependencia de 
     * ui.router que es la que se utiliza para la configuración de las URLs bajo las
     * cuales se accede al módulo. Por ejemplo, para mostrar los autores en la 
     * URL: 'localhost:8080/cliente/id/carrito' es necesario configurar el router por 
     * medio del stateProvider que informa a AngularJS de la relación entre la URL, 
     * un estado definido (estado de mostrar autores), el controlador y la vista 
     * correspondiente.
     */
    var mod = ng.module('CarritoMod', ['ui.router']);
    var urlCarrito = '/cliente/' + sessionStorage.getItem("id") + '/carrito';
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {

            $urlRouterProvider.otherwise("/carrito");

            $stateProvider.state('carrito', {
                url: urlCarrito,
                views: {
                    mainView: {
                        templateUrl: "src/modules/carrito/carrito.view.html",
                        controller: "carritoCtrl"
                    }
                }
            }).state('deleteItemCarrito', {
                url: urlCarrito,

                params: {
                    itemId: null
                },

                views: {
                    mainView: {
                        templateUrl: "src/modules/carrito/carrito.view.html",
                        controller: "deleteCarritoCtrl"
                    }
                }

            }).state('addItemCarrito', {
                url: urlCarrito,

                params: {
                    itemId: null,
                    color: null
                },

                views: {
                    mainView: {
                        templateUrl: "src/modules/carrito/carrito.view.html",
                        controller: "addCarritoCtrl"
                    }
                }

            }).state('vaciarCarrito', {
                url: urlCarrito,

                views: {
                    mainView: {
                        templateUrl: "src/modules/carrito/carrito.view.html",
                        controller: "vaciarCarritoCtrl"
                    }
                }
            }).state('comprar', {
                                url: urlCarrito,

                views: {
                    'mainView': {
                        controller: 'comprarCtrl',
                        templateUrl: 'src/modules/mediosPago/mediosPago.list.html'
                    }
                }
            }).state('yaEsta', {
                url: urlCarrito + '/error',

                views: {
                    'mainView': {
                        templateUrl: 'src/modules/carrito/carrito.error.yaesta.view.html'
                    }
                }
            })
        }]);

})(window.angular);
