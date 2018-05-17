(function (ng) {
    var mod = ng.module("moduloMediosPago");

    /**
     * @ngdoc controller
     * @name mediosDePago.controller:mediosPagoDeleteCtrl
     * @description
     * Definición del controlador de Angular del módulo mediosDePago. 
     * Se crea el controlador con el cual se maneja el módulo.
     * En el controlador se definen los atributos y métodos que pueden
     * ser accedidos desde el HTML utilizando el $scope.
     * @param {Object} $scope Referencia injectada al Scope definida para este
     * controlador, el scope es el objeto que contiene las variables o 
     * funciones que se definen en este controlador y que son utilizadas 
     * desde el HTML.
     * @param {Object} $http Objeto injectado para la manejar consultas HTTP
     * @param {Object} mediosPagoContext Constante injectada que contiene la ruta
     * donde se encuentra el API de MediosDePago en el Backend.
     * @param {Object} $state Dependencia injectada en la que se recibe el 
     * estado actual de la navegación definida en el módulo.
     */
    mod.controller('mediosPagoDeleteCtrl',['$scope','$http','mediosPagoContext','$state',
        function($scope,$http,mediosPagoContext,$state){

            /**
             * @ngdoc function
             * @name deleteMediosDePago
             * @methodOf mediosDePago.controller:mediosPagoDeleteCtrl
             * @description
             * Esta función utiliza el protocolo HTTP para obtener el recurso 
             * donde se encuentran los mediosDePago en formato JSON. El recurso
             * puede ser un archivo o un API Rest. La función se ejecuta
             * automáticamente cuando el controlador es accedido desde el
             * navegador.
             * @param {String} URL Dirección donde se encuentra el recurso
             * de los mediosDePago o API donde se puede consultar. Se utiliza el
             * contexto definido anteriormente.
             */
            $http.delete('api/mediospago/'+ $state.params.medioPagoId).then(function (response) {
                $state.go('mediosPagoList',{medioPagoId: response.data.id}, {reload: true});
            });
        }
    ]);
}
)(window.angular);