(function (ng) {

    var mod = ng.module("vendedoresModule");

    mod.constant("vendedoresContext", "api/clientes/vendedores");
    /**
     * @ngdoc controller
     * @name vendedor.controller:vendedoresCtrl
     * @description
     * Definición del controlador de Angular del módulo vendedor. 
     * Se crea el controlador con el cual se maneja el módulo.
     * En el controlador se definen los atributos y métodos que pueden
     * ser accedidos desde el HTML utilizando el $scope.
     * @param {Object} $scope Referencia injectada al Scope definida para este
     * controlador, el scope es el objeto que contiene las variables o 
     * funciones que se definen en este controlador y que son utilizadas 
     * desde el HTML.
     * @param {Object} $http Objeto injectado para la manejar consultas HTTP
     * @param {Object} vendedoresContext Constante injectada que contiene la ruta
     * donde se encuentra el API de vendedor en el Backend.
     * @param {Object} $state Dependencia injectada en la que se recibe el 
     * estado actual de la navegación definida en el módulo.
     */
    mod.controller("vendedoresCtrl", ['$scope', '$http','$state', 'vendedoresContext',
        function ($scope, $http, vendedoresContext,$state) {


            $scope.listaVendedores = {};
            /**
             * @ngdoc function
             * @name getVendedores
             * @methodOf vendedor.controller:vendedoresCtrl
             * @description
             * Esta función utiliza el protocolo HTTP para obtener el recurso 
             * donde se encuentran los vendedores en el back. El recurso
             * puede ser un archivo o un API Rest. La función se ejecuta
             * automáticamente cuando el controlador es accedido desde el
             * navegador.
             * @param {String} URL Dirección donde se encuentra el recurso
             * de los vendedores o API donde se puede consultar. Se utiliza el
             * contexto definido anteriormente.
             */
            $http.get(vendedoresContext).then(function (response) {
                $scope.records = response.data;
            });

            this.getRecord = function (id) {
                /**
                 * @ngdoc function
                 * @name getVendedor especifico
                 * @methodOf clientes.controller:vendedoresCtrl
                 * @description
                 * Esta función utiliza el protocolo HTTP para obtener el recurso 
                 * donde se encuentran los vendedores en formato JSON. El recurso
                 * puede ser un archivo o un API Rest. La función se ejecuta
                 * automáticamente cuando el controlador es accedido desde el
                 * navegador.
                 * @param {String} URL Dirección donde se encuentra el recurso
                 * de los vendedores o API donde se puede consultar. Se utiliza el
                 * contexto definido anteriormente.
                 */
                $http.get('http://localhost:8080/s3_escarabajos-web/api/clientes/' + id);
            };

            $scope.createVendedor = function () {

                var input = {};

                input.telefono = $scope.clienteActual.usuario;

                input.direccion = $scope.clienteActual.nombre;

                /**
                 * @ngdoc function
                 * @name putVendedor 
                 * @methodOf vendedor.controller:vendedoresCtrl
                 * @description
                 * Esta función utiliza el protocolo HTTP para obtener el recurso 
                 * donde se encuentran los vendedores en formato JSON. El recurso
                 * puede ser un archivo o un API Rest. La función se ejecuta
                 * automáticamente cuando el controlador es accedido desde el
                 * navegador.
                 * @param {String} URL Dirección donde se encuentra el recurso
                 * de los vendedores o API donde se puede consultar. Se utiliza el
                 * contexto definido anteriormente.
                 */
                $http.put("api/clientes" + '/' + $state.params.clienteId, input).then(function (response) {
                    $state.go('clientesList', {clienteId: response.data.id}, {reload: true});
                });
            };

        }]);
})(window.angular);

