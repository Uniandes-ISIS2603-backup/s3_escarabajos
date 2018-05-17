(function (ng) {
    /**
     * @ngdoc controller
     * @name factura.detail.update.controller:facturaEditCtrl
     * @description
     * Definición del controlador de Angular del módulo del update de un Factura. 
     * Se crea el controlador con el cual se maneja el módulo.
     * En el controlador se definen los atributos y métodos que pueden
     * ser accedidos desde el HTML utilizando el $scope.
     * @param {Object} $scope Referencia injectada al Scope definida para este
     * controlador, el scope es el objeto que contiene las variables o 
     * funciones que se definen en este controlador y que son utilizadas 
     * desde el HTML.
     * @param {Object} $http Objeto injectado para la manejar consultas HTTP
     * @param {Object} accesorioContext Constante injectada que contiene la ruta
     * donde se encuentra el API de Facturas en el Backend.
     * @param {Object} $state Dependencia injectada en la que se recibe el 
     * estado actual de la navegación definida en el módulo.
     */
    var mod = ng.module("moduloFacturas");

    mod.constant("facturaContext", "api/facturas");

    mod.controller('facturaEditCtrl', ['$scope', '$http', 'facturaContext', '$state',
        function ($scope, $http, facturaContext, $state) {


            if ($state.params.facturaId !== undefined && $state.params.facturaId !== null) {

            /**
                 * @ngdoc function
                 * @name getFactura
                 * @methodOf accesorios.detail.controller:FacturaEditCtrl
                 * @description
                 * Esta función utiliza el protocolo HTTP para obtener el recurso 
                 * donde se encuentra el accesorio en formato JSON. El recurso
                 * puede ser un archivo o un API Rest. La función se ejecuta
                 * automáticamente cuando el controlador es accedido desde el
                 * navegador.
                 * @param {String} URL Dirección donde se encuentra el recurso
                 * de los facturas o API donde se puede consultar. Se utiliza el
                 * contexto definido anteriormente.
                 */

                $http.get(facturaContext + '/' + $state.params.facturaId).then(function (response) {

                    $scope.facturaActual = response.data;

                    $scope.updateFactura = function () {

                        var input = {};

                        input.id = $scope.facturaActual.id;

                        input.dinero = $scope.facturaActual.dinero;
                        
                        $http.put(facturaContext + '/' + $state.params.facturaId, input).then(function (response) {
                            $state.go('facturasList', {facturaId: response.data.id}, {reload: true});
                        });
                    };
                });
            }
            ;


        }
    ]);
}
)(window.angular);