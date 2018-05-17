
(function (ng) {
    /**
     * @ngdoc overview
     * @name modelos.module:modelosModule
     * @description
     * Definición del módulo de Angular de modelos. El módulo encapsula todos los 
     * controladores y los templates HTML que estén relacionados con los modelos 
     * directamente. En la configuración del módulo se injecta la dependencia de 
     * ui.router que es la que se utiliza para la configuración de las URLs bajo las
     * cuales se accede al módulo. Por ejemplo, para mostrar los modelos en la 
     * URL: 'localhost:8080/api/modelos' es necesario configurar el router por 
     * medio del stateProvider que informa a AngularJS de la relación entre la URL, 
     * un estado definido (estado de mostrar modelos), el controlador y la vista 
     * correspondiente.
     */
    var mod = ng.module("modelosModule", ['ui.router']);
    mod.constant("modelosContext", "api/modelos");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/modelos/';
            
            $stateProvider.state('modelos', {
                url: '/modelos',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'modelos.html',
                    }
                }
            }).state('modeloDetail', {
                url: '/{modeloId:int}/detail',
                parent: 'modelos',
                params: {
                    modeloId: null,
                    exception: false
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'modelos.detail.html',
                        controller: 'modeloDetailCtrl',
                        controllerAs: 'ctrl'
                    },
                    'clasificacionesView': {
                        templateUrl: 'src/modules/calificaciones/calificaciones.list.html',
                        controller: 'calificacionesListCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('modeloUpdate', {
                url: '/update/{modeloId:int}',
                parent: 'modelos',
                params: {
                    modeloId: null
                },
                views: {
                    'listView': {
                        templateUrl: basePath + '/editar/bicis.edit.html',
                        controller: 'biciEditCtrl'
                    }
                }
            });
        }]);
})(window.angular);
