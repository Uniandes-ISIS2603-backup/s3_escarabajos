(function (ng) {
var mod = ng.module("clientesModule", []);
    mod.constant("clientesContext", "api/clientes");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/clientes/';
            
            $urlRouterProvider.otherwise("/clientesList");

            $stateProvider.state('clientesList', {
                url: '/clientes',
                parent:'adminList',
                views: {
                    'childrenView': {
                        controller: 'clientesCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'clientes.list.html'
                    }
                }
            }).state('clientesSignUp', {
                url: '/clientesSignUp',
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
                        controller: 'clientesCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'clientes.profile.html'
                    }
                }
            }).state('clientesSignIn', {
                url: '/clientesSignIn',
                views: {
                    'mainView': {
                        controller: 'clientesCtrl',
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

