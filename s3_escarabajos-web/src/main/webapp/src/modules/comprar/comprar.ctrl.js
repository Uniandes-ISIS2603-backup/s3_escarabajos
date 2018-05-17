(function (ng) {
    /**
     * @ngdoc controller
     * @name comprar.controller:comprarCtrl
     * @description
     * Definición del controlador de Angular del módulo carrito. 
     * Se crea el controlador con el cual se maneja el módulo.
     * En el controlador se definen los atributos y métodos que pueden
     * ser accedidos desde el HTML utilizando el $scope.
     * @param {Object} $scope Referencia injectada al Scope definida para este
     * controlador, el scope es el objeto que contiene las variables o 
     * funciones que se definen en este controlador y que son utilizadas 
     * desde el HTML.
     * @param {Object} $http Objeto injectado para la manejar consultas HTTP
     * @param {Object} $state Dependencia injectada en la que se recibe el 
     * estado actual de la navegación definida en el módulo.
     */
    var mod = ng.module("moduloComprar");
    mod.controller('comprarCtrl', ['$scope', '$http', '$state',
        function ($scope, $http, $state) {

            /**
            * @ngdoc function
            * @name postClientesItemCarritoFactura
            * @methodOf comprar.controller:comprarCtrl
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
            $http.post('api/clientes/' + sessionStorage.getItem("id") + '/carrito/factura').then(function (response) {
                
                $scope.facturaCreada = response.data;
            });

        }
    ])
}
)(window.angular);