(function (ng) {
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
