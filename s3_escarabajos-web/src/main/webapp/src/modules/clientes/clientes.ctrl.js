(function (ng) {
    /**
     * @ngdoc controller
     * @name clientes.controller:clientesCtrl
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
     * @param {Object} clientesContext Constante injectada que contiene la ruta
     * donde se encuentra el API de BicicletaUsada en el Backend.
     * @param {Object} $state Dependencia injectada en la que se recibe el 
     * estado actual de la navegación definida en el módulo.
     */
    var mod = ng.module("clientesModule");
    
    mod.constant("clientesContext","api/clientes");
    
    mod.controller('clientesCtrl',['$scope','$http','clientesContext', '$state',
        function($scope,$http,clientesContext, $state){
            
            $scope.listaClientes ={};
            /**
            * @ngdoc function
            * @name getClientes
            * @methodOf clientes.controller:clientesCtrl
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
            $http.get(clientesContext).then(function(response){
                $scope.records = response.data;
            });
            /**
            * @ngdoc function
            * @name deleteRecord
            * @methodOf clientes.controller:clientesCtrl
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
            this.deleteRecord = function (id) {
                $http.delete('http://localhost:8080/s3_escarabajos-web/api/clientes/'+ id);
                $state.reload('clientesList');
            };
            /**
            * @ngdoc function
            * @name getRecord
            * @methodOf clientes.controller:clientesCtrl
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
            this.getRecord = function (id) {
                $http.get('http://localhost:8080/s3_escarabajos-web/api/clientes/'+ id);
            };

    }]);

})(window.angular);

