(function (ng) {
        /**
     * @ngdoc controller
     * @name calificaciones.controller:calificacionesListCtrl2
     * @description
     * Definición del controlador de Angular del módulo Calificaciòn. 
     * Se crea el controlador con el cual se maneja el módulo.
     * En el controlador se definen los atributos y métodos que pueden
     * ser accedidos desde el HTML utilizando el $scope.
     * @param {Object} $scope Referencia injectada al Scope definida para este
     * controlador, el scope es el objeto que contiene las variables o 
     * funciones que se definen en este controlador y que son utilizadas 
     * desde el HTML.
     * @param {Object} $http Objeto injectado para la manejar consultas HTTP
     * @param {Object} accesorioContext Constante injectada que contiene la ruta
     * donde se encuentra el API de bicicletas en el Backend.
     * @param {Object} $state Dependencia injectada en la que se recibe el 
     * estado actual de la navegación definida en el módulo.
     */
    var mod = ng.module("moduloBicis");

    mod.constant("calificacionContext", "api/modelos");

    mod.controller("calificacionesListCtrl", ['$scope', 'calificacionContext', '$http', '$state',
        function ($scope, calificacionContext, $http, $state) {
            $scope.data = {};
            if ($state.params.biciId !== undefined && $state.params.biciId !== null) {

                $http.get(calificacionContext + '/' + $state.params.biciId + '/calificaciones').then(function (response) {
                    $scope.calificaciones = response.data;
                });
                $scope.createCalificacion = function ()
                {
                    $http.post(calificacionContext + '/' + $state.params.biciId + '/calificaciones',  $scope.data).then(
                            $state.go('^.biciDetail', {biciId: $state.params.biciId}, {reload: true}));
                };
            }
        }]);

})(window.angular);