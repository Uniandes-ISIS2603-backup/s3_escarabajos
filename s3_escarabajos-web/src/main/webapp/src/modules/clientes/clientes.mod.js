(function (ng) {
     /**
     * @ngdoc overview
     * @name clientes.module:clientesModule
     * @description
     * Definición del módulo de Angular de Bicicletas. El módulo encapsula todos los 
     * controladores y los templates HTML que estén relacionados con los Bicicletas 
     * directamente. En la configuración del módulo se injecta la dependencia de 
     * ui.router que es la que se utiliza para la configuración de las URLs bajo las
     * cuales se accede al módulo. Por ejemplo, para mostrar los autores en la 
     * URL: 'localhost:8080/clientes' es necesario configurar el router por 
     * medio del stateProvider que informa a AngularJS de la relación entre la URL, 
     * un estado definido (estado de mostrar autores), el controlador y la vista 
     * correspondiente.
     */
    var mod = ng.module("clientesModule", []);
    mod.constant("clientesContext", "api/clientes");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/clientes/';

            $urlRouterProvider.otherwise("/clientesList");

            $stateProvider.state('clientesList', {
                url: '/clientes',
                views: {
                    'mainView': {
                        controller: 'clientesCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'clientes.list.html'
                    }
                }
            }).state('clientesSignUp', {
                url: '/signUp',
                views: {
                    'mainView': {
                        controller: 'clientesCreateCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'clientes.signUp.html'
                    }
                }
            }).state('clientesProfile', {
                url: '/clientesProfile',
                views: {
                    'mainView': {
                        controller: 'clientesProfileCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'clientes.profile.html'
                    }
                }
            }).state('clientesSignIn', {
                url: '/login',
                data: {
                    requireLogin: false
                },
                views: {
                    'mainView': {
                        controller: 'loginCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'clientes.signIn.html'
                    }
                }
            }).state('clientesDetail', {
                url: '/{clienteId:int}/clientesDetail',
                param: {
                    clienteId: null
                },
                views: {
                    'mainView': {
                        controller: 'clientesDetailCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'clientes.detail.html'
                    }
                }
            });
        }]);

})(window.angular);

