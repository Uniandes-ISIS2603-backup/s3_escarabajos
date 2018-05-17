(function (ng) {
    var mod = ng.module("modelosModule");
    mod.constant("modelosContext", "api/modelos");
    /**
     * @ngdoc controller
     * @name modelo.controller:modeloEditCtrl
     * @description
     * Definición del controlador de Angular del módulo modelos. 
     * Se crea el controlador con el cual se maneja el módulo.
     * En el controlador se definen los atributos y métodos que pueden
     * ser accedidos desde el HTML utilizando el $scope.
     * @param {Object} $scope Referencia injectada al Scope definida para este
     * controlador, el scope es el objeto que contiene las variables o 
     * funciones que se definen en este controlador y que son utilizadas 
     * desde el HTML.
     * @param {Object} $http Objeto injectado para la manejar consultas HTTP
     * @param {Object} bicicletaContext Constante injectada que contiene la ruta
     * donde se encuentra el API de Modelos en el Backend.
     * @param {Object} $state Dependencia injectada en la que se recibe el 
     * estado actual de la navegación definida en el módulo.
     */
    mod.controller("modeloEditCtrl", ['$scope', 'modelosContext', '$http', '$state',
        function ($scope, modeloContext, $http, $state) {
            if ($state.params.modeloId !== undefined && $state.params.modeloId !== null) {
                /**
                 * @ngdoc function
                 * @name getModelos
                 * @methodOf modelos.controller:modeloEditCtrl
                 * @description
                 * Esta función utiliza el protocolo HTTP para obtener el recurso 
                 * donde se encuentran los modelos en formato JSON. El recurso
                 * puede ser un archivo o un API Rest. La función se ejecuta
                 * automáticamente cuando el controlador es accedido desde el
                 * navegador.
                 * @param {String} URL Dirección donde se encuentra el recurso
                 * de los modelos o API donde se puede consultar. Se utiliza el
                 * contexto definido anteriormente.
                 */
                $http.get(modeloContext + '/' + $state.params.modeloId).then(function (response) {
                    $scope.modeloActual = response.data;

                    $scope.updateModelo = function () {

                        var input = {};

                        input.id = $scope.modeloActual.id;

                        input.precio = $scope.modeloActual.precio;

                        input.modeloId = $scope.modeloActual.modeloId;

                        input.color = $scope.modeloActual.color;

                        input.categoria = $scope.modeloActual.categoria;

                        input.album = $scope.modeloActual.album;

                        input.usada = false;

                        input.disponible = true;

                        /**
                         * @ngdoc function
                         * @name putModelos
                         * @methodOf modelos.controller:modeloEditCtrl
                         * @description
                         * Esta función utiliza el protocolo HTTP para obtener el recurso 
                         * donde se encuentran los modelos en formato JSON. El recurso
                         * puede ser un archivo o un API Rest. La función se ejecuta
                         * automáticamente cuando el controlador es accedido desde el
                         * navegador.
                         * @param {String} URL Dirección donde se encuentra el recurso
                         * de los modelos o API donde se puede consultar. Se utiliza el
                         * contexto definido anteriormente.
                         */
                        $http.put(modeloContext + '/' + $state.params.modeloId, input).then(function (response) {
                            $state.go('principal:pagina1', {modeloId: response.data.id}, {reload: true});
                        });
                    };
                });
            }
        }
    ]);
})(window.angular);