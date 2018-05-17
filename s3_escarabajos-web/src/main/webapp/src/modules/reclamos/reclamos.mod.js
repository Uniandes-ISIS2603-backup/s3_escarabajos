(function (ng) {
         /**
     * @ngdoc overview
     * @name reclamos.module:reclamosModule
     * @description
     * Definición del módulo de Angular de Reclamos. El módulo encapsula todos los 
     * controladores y los templates HTML que estén relacionados con los Reclamos 
     * directamente. En la configuración del módulo se injecta la dependencia de 
     * ui.router que es la que se utiliza para la configuración de las URLs bajo las
     * cuales se accede al módulo. Por ejemplo, para mostrar los vendedores en la 
     * URL: 'localhost:8080/api/clientes/reclamos ' es necesario configurar el router por 
     * medio del stateProvider que informa a AngularJS de la relación entre la URL, 
     * un estado definido (estado de mostrar vendedores), el controlador y la vista 
     * correspondiente.
     */
    // Definición del módulo
    var mod = ng.module("reclamosModule", ['ui.router']);
    mod.constant("reclamosContext", "api/clientes/reclamos");
    // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/reclamos/';
            // Mostrar la lista de editoriales será el estado por defecto del módulo
            $urlRouterProvider.otherwise("/reclamos");
            // Definición del estado 'editorialsList' donde se listan los editoriales
            $stateProvider.state('reclamosList', {
                // Url que aparecerá en el browser
                url: '/reclamos/list',
                views: {
                    'mainView': {
                        templateUrl: basePath + 'reclamos.list.html',
                        controller: 'reclamosCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $stateProvider.state('reclamosCreate', {
                // Url que aparecerá en el browser
                url: '/reclamos/create',
                param: {
                    facturaId: null
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'reclamos.create.html',
                        controller: 'reclamoCreateCtrl',
                        controllerAs: 'ctrl'
                    }
                },
                data: {

                }
            });
            $stateProvider.state('reclamosDetail', {
                // Url que aparecerá en el browser
                url: '/clientes/reclamos/{reclamoId:int}',
                param: {
                    reclamoId: null
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'reclamos.detail.html',
                        controller: 'reclamosDetailCtrl',
                        controllerAs: 'ctrl'
                    }
                },
                data: {
                }
            });
            $stateProvider.state('reclamosAdminList', {
                // Url que aparecerá en el browser
                url: '/admin/reclamos/list',
                views: {
                    'mainView': {
                        templateUrl: basePath + 'reclamos.admin.list.html',
                        controller: 'reclamosAdminCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
        }
    ]);
})(window.angular);
