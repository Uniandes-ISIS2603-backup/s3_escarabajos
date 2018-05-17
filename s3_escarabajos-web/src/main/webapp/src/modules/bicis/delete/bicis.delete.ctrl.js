(function (ng) {
    /**
     * @ngdoc controller
     * @name bicicleta.detail.delete.controller:bicicletaDeleteCtrl
     * @description
     * Definición del controlador de Angular del módulo del delete de un Bicicleta. 
     * Se crea el controlador con el cual se maneja el módulo.
     * En el controlador se definen los atributos y métodos que pueden
     * ser accedidos desde el HTML utilizando el $scope.
     * @param {Object} $scope Referencia injectada al Scope definida para este
     * controlador, el scope es el objeto que contiene las variables o 
     * funciones que se definen en este controlador y que son utilizadas 
     * desde el HTML.
     * @param {Object} $http Objeto injectado para la manejar consultas HTTP
     * @param {Object} bicicletaContext Constante injectada que contiene la ruta
     * donde se encuentra el API de Bicicletas en el Backend.
     * @param {Object} $state Dependencia injectada en la que se recibe el 
     * estado actual de la navegación definida en el módulo.
     */
    var mod = ng.module("moduloBicis");

    mod.constant("biciContext","api/bicis");
    
    mod.controller('biciDeleteCtrl',['$scope','$http','biciContext','$state',
        function($scope,$http,biciContext,$state){

        /**
             * @ngdoc function
             * @name getBicicleta
             * @methodOf bicicletas.detail.detlete.controller:bicicletaDeleteCtrl
             * @description
             * Esta función utiliza el protocolo HTTP para eliminar el recurso 
             * donde se encuentra el bicicleta en formato JSON. El recurso
             * puede ser un archivo o un API Rest. La función se ejecuta
             * automáticamente cuando el controlador es accedido desde el
             * navegador.
             * @param {String} URL Dirección donde se encuentra el recurso
             * de los bicicletas o API donde se puede consultar. Se utiliza el
             * contexto definido anteriormente.
             */
            $http.delete(biciContext + "/" + $state.params.biciId).then(function (response) {
                $state.go('bicisList',{biciId: response.data.id}, {reload: true});
            });
        }
    ]);
}
)(window.angular);