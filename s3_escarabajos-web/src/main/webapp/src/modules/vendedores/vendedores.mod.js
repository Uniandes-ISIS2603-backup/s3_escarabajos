(function (ng) {
var mod = ng.module("vendedoresModule", []);
    mod.constant("vendedoresContext", "api/clientes/vendedores");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/vendedores/';
            $urlRouterProvider.otherwise("/vendedoresList");

            $stateProvider.state('vendedoresList', {
                url: '/vendedores',
                views: {
                    'mainView': {
                        controller: 'vendedoresCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'vendedores.list.html'
                    }
                }
            });
        }]);

})(window.angular);

