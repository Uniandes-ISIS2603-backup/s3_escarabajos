(function (ng) {
// Definición del módulo
    var mod = ng.module("calificacionModule", ['ui.router']);
    mod.constant("calificacionesContext", "api/modelos/{modeloId}/calificaciones");
    // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/calificaciones/';
            // Mostrar la lista de editoriales será el estado por defecto del módulo
            // Definición del estado 'editorialsList' donde se listan los editoriales
            $stateProvider.state('calificacionesList', {
                // Url que aparecerá en el browser
                param:{

                },
                views: {
                    'gaitanView': {
                        templateUrl: basePath + 'calificaciones.list.html',
                    },
                }
            });
        }
    ]);
})(window.angular);
