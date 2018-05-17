(function (ng) {
    /**
     * @ngdoc controller
     * @name accesorio.detail.controller:accesorioDetailCtrl
     * @description
     * Definición del controlador de Angular del módulo del detalle de un Accesorio. 
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

    mod.constant("accesorioContext", "api/accesorios");

    mod.controller("accesorioDetailCtrl", ['$scope', 'accesorioContext', '$http', '$state',
        function ($scope, accesorioContext, $http, $state) {
            
            if ($state.params.accesorioId !== undefined && $state.params.accesorioId !== null) {
             /**
             * @ngdoc function
             * @name getAccesorios
             * @methodOf accesorios.detail.controller:accesorioCtrl
             * @description
             * Esta función utiliza el protocolo HTTP para obtener el recurso 
             * donde se encuentra el accesorio especificado en formato JSON. El recurso
             * puede ser un archivo o un API Rest. La función se ejecuta
             * automáticamente cuando el controlador es accedido desde el
             * navegador.
             * @param {String} URL Dirección donde se encuentra el recurso
             * del accesorio especificado o API donde se puede consultar. Se utiliza el
             * contexto definido anteriormente.
             */
                $http.get(accesorioContext + '/' + $state.params.accesorioId).then(function (response) {
                    $scope.accesorioActual = response.data;
                });
            }
        }]);
    
})(window.angular);