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
     * donde se encuentra el API de Accesorios en el Backend.
     * @param {Object} $state Dependencia injectada en la que se recibe el 
     * estado actual de la navegación definida en el módulo.
     */
    var mod = ng.module("AccesoriosMod");

    mod.constant("calificacionContext", "api/modelos");

    mod.controller("calificacionesListCtrl2", ['$scope', 'calificacionContext', '$http', '$state',
        function ($scope, calificacionContext, $http, $state) {
            $scope.data = {};
            if ($state.params.accesorioId !== undefined && $state.params.accesorioId !== null) {

                /**
                 * @ngdoc function
                 * @name getCalificaciones
                 * @methodOf calificaciones.list.controller:calificacionesListCtrl2
                 * @description
                 * Esta función utiliza el protocolo HTTP para obtener el recurso 
                 * donde se encuentran todas las calificaciones del modelo especificado en formato JSON. El recurso
                 * puede ser un archivo o un API Rest. La función se ejecuta
                 * automáticamente cuando el controlador es accedido desde el
                 * navegador.
                 * @param {String} URL Dirección donde se encuentra el recurso
                 * del accesorio especificado o API donde se puede consultar. Se utiliza el
                 * contexto definido anteriormente.
                 */
                $http.get(calificacionContext + '/' + $state.params.accesorioId + '/calificaciones').then(function (response) {
                    $scope.calificaciones = response.data;
                });
                $scope.createCalificacion = function ()
                {
                    /**
                     * @ngdoc function
                     * @name postCalificacion
                     * @methodOf calificaciones.post.controller:calificacionesListCtrl2
                     * @description
                     * Esta función utiliza el protocolo HTTP para realizar un POST del recurso 
                     * creado a partir de la informaciòn ingresada. El recurso
                     * puede ser un archivo o un API Rest. La función se ejecuta
                     * automáticamente cuando el controlador es accedido desde el
                     * navegador.
                     * @param {String} URL Dirección donde se encuentra el recurso
                     * del accesorio especificado o API donde se puede consultar. Se utiliza el
                     * contexto definido anteriormente.
                     */
                    $http.post(calificacionContext + '/' + $state.params.accesorioId + '/calificaciones', $scope.data).then(
                            $state.go('^.accesorioDetail', {accesorioId: $state.params.accesorioId}, {reload: true}));
                };
            }
        }]);

})(window.angular);