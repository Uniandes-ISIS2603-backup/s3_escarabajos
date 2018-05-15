(function () {
    var mod = angular.module('CarritoMod', ['ui.router']);

    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {

            $urlRouterProvider.otherwise("/carrito");

            $stateProvider.state('carrito', {
                views: {
                    mainView: {
                        templateUrl: "src/modules/carrito/carrito.view.html",
                        controller: "carritoCtrl"
                    }
                }
            }).state('deleteItemCarrito', {

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

                params: {
                    itemId: null
                },

                views: {
                    mainView: {
                        templateUrl: "src/modules/carrito/carrito.view.html",
                        controller: "addCarritoCtrl"
                    }
                }

            }).state('vaciarCarrito', {

                views: {
                    mainView: {
                        templateUrl: "src/modules/carrito/carrito.view.html",
                        controller: "vaciarCarritoCtrl"
                    }
                }
            }).state('comprar', {
                views: {
                    'mainView': {
                        controller: 'carritoCtrl',
                        templateUrl: 'src/modules/comprar/comprar.list.html'
                    }
                }
            }).state('yaEsta', {
                views: {
                    'mainView': {
                        templateUrl: 'src/modules/carrito/carrito.error.yaesta.view.html'
                    }
                }
            })
        }]);

})(window.angular);