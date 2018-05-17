(function (ng) {
     /**
     * @ngdoc overview
     * @name vendedores.module:vendedoresModule
     * @description
     * Definición del módulo de Angular de Vendedores. El módulo encapsula todos los 
     * controladores y los templates HTML que estén relacionados con los Vendedores 
     * directamente. En la configuración del módulo se injecta la dependencia de 
     * ui.router que es la que se utiliza para la configuración de las URLs bajo las
     * cuales se accede al módulo. Por ejemplo, para mostrar los vendedores en la 
     * URL: 'localhost:8080/clientes/vendedores ' es necesario configurar el router por 
     * medio del stateProvider que informa a AngularJS de la relación entre la URL, 
     * un estado definido (estado de mostrar vendedores), el controlador y la vista 
     * correspondiente.
     */
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

