(function (ng) {
    /**
     * @ngdoc controller
     * @name accesorio.detail.update.controller:accesorioEditCtrl
     * @description
     * Definición del controlador de Angular del módulo del update de un Accesorio. 
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

    mod.controller('accesorioEditCtrl', ['$scope', '$http', 'accesorioContext', '$state',
        function ($scope, $http, accesorioContext, $state) {

            if ($state.params.accesorioId !== undefined && $state.params.accesorioId !== null) {
                /**
                 * @ngdoc function
                 * @name getAccesorio
                 * @methodOf accesorios.detail.controller:accesorioEditCtrl
                 * @description
                 * Esta función utiliza el protocolo HTTP para obtener el recurso 
                 * donde se encuentra el accesorio en formato JSON. El recurso
                 * puede ser un archivo o un API Rest. La función se ejecuta
                 * automáticamente cuando el controlador es accedido desde el
                 * navegador.
                 * @param {String} URL Dirección donde se encuentra el recurso
                 * de los accesorios o API donde se puede consultar. Se utiliza el
                 * contexto definido anteriormente.
                 */
                $http.get(accesorioContext + '/' + $state.params.accesorioId).then(function (response) {
                    $scope.accesorioActual = response.data;

                    $scope.updateAccesorio = function () {

                        var input = {};

                        input.id = $scope.accesorioActual.id;

                        input.precio = $scope.accesorioActual.precio;

                        input.modeloId = $scope.accesorioActual.modeloId;

                        input.color = $scope.accesorioActual.color;

                        input.categoria = $scope.accesorioActual.categoria;

                        input.album = $scope.accesorioActual.album;
                        /**
                         * @ngdoc function
                         * @name putAccesorio
                         * @methodOf accesorios.detail.controller:accesorioEditCtrl
                         * @description
                         * Esta función utiliza el protocolo HTTP para actualizar el recurso 
                         * donde se encuentra el accesorio en formato JSON. El recurso
                         * puede ser un archivo o un API Rest. La función se ejecuta
                         * automáticamente cuando el controlador es accedido desde el
                         * navegador.
                         * @param {String} URL Dirección donde se encuentra el recurso
                         * de los accesorios o API donde se puede consultar. Se utiliza el
                         * contexto definido anteriormente.
                         */
                        $http.put(accesorioContext + '/' + $state.params.accesorioId, input).then(function (response) {
                            $state.go('accesoriosList', {accesorioId: response.data.id}, {reload: true});
                        });
                    };
                });
            }
            ;


        }
    ]);
}
)(window.angular);