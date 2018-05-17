(function (ng) {
    /**
     * @ngdoc controller
     * @name bicicleta.controller:bicicletaCtrl
     * @description
     * Definición del controlador de Angular del módulo bicicleta. 
     * Se crea el controlador con el cual se maneja el módulo.
     * En el controlador se definen los atributos y métodos que pueden
     * ser accedidos desde el HTML utilizando el $scope.
     * @param {Object} $scope Referencia injectada al Scope definida para este
     * controlador, el scope es el objeto que contiene las variables o 
     * funciones que se definen en este controlador y que son utilizadas 
     * desde el HTML.
     * @param {Object} $http Objeto injectado para la manejar consultas HTTP
     * @param {Object} bicicletaContext Constante injectada que contiene la ruta
     * donde se encuentra el API de Bicicleta en el Backend.
     * @param {Object} $state Dependencia injectada en la que se recibe el 
     * estado actual de la navegación definida en el módulo.
     */
    var mod = ng.module("moduloBicis");

    mod.constant("biciContext", "api/bicis");

    mod.controller('biciCtrl', ['$scope', '$http', 'biciContext',
        function ($scope, $http, biciContext) {

            $scope.listaBicis = {};

            /**
             * @ngdoc function
             * @name getBicicletas
             * @methodOf bicicletas.controller:bicicletasCtrl
             * @description
             * Esta función utiliza el protocolo HTTP para obtener el recurso 
             * donde se encuentran los bicicletas en formato JSON. El recurso
             * puede ser un archivo o un API Rest. La función se ejecuta
             * automáticamente cuando el controlador es accedido desde el
             * navegador.
             * @param {String} URL Dirección donde se encuentra el recurso
             * de los bicicletas o API donde se puede consultar. Se utiliza el
             * contexto definido anteriormente.
             */
            $http.get(biciContext).then(function (response) {
                $scope.listaBicis = response.data;
            });
        }]);
})(window.angular)