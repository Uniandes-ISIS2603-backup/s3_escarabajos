(function (ng) {
    // Definición del módulo
    var mod = ng.module("reclamosModule", ['ui.router']);
    mod.constant("reclamosContext","api/reclamos");
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
                views: {
                    'mainView': {
                        templateUrl: basePath + 'reclamos.create.html',
                    }
                }
            });
        }
    ]);
})(window.angular);
