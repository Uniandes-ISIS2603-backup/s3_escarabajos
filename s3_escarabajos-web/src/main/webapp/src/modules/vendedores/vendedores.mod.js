(function (ng) {
var mod = ng.module("vendedoresModule", []);
    mod.constant("vendedoresContext", "api/clientes/vendedores");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/vendedores/';
            $urlRouterProvider.otherwise("/vendedoresList");

            $stateProvider.state('vendedoresList', {
                url: '/vendedores',
                parent:'adminList',
                views: {
                    'childrenView': {
                        controller: 'vendedoresCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'vendedores.list.html'
                    }
                }
            }).state('vendedoresCreate', {
                url: '/vendedoresCreate',
                views: {
                    'mainView': {
                        controller: 'vendedoresCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'vendedores.create.html'
                    }
                }
            });
        }]);

})(window.angular);

