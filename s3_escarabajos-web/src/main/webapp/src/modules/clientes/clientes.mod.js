(function (ng) {
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

