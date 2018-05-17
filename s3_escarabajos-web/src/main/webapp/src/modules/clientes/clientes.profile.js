(function (ng) {
    /**
     * @ngdoc controller
     * @name clientes.profile:clientesProfileCtrl
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

    mod.constant("clientesContext", "api/clientes");

    mod.controller("clientesProfileCtrl", ['$scope','$http','clientesContext', '$state',
        function ($scope, $http, clientesContext, $state) {  
            
            if ((sessionStorage.getItem("id") !== undefined)&& (sessionStorage.getItem("id") !== null)) {
                /**
                * @ngdoc function
                * @name getClientes
                * @methodOf clientes.profile:clientesProfileCtrl
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
                $http.get(clientesContext + '/' + sessionStorage.getItem("id")).then(function (response) {
                    $scope.clienteActual = response.data;
                });
                /**
                * @ngdoc function
                * @name updateCliente
                * @methodOf clientes.profile:clientesProfileCtrl
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
                $scope.updateCliente = function () {

                        var input = {};

                        input.usuario = $scope.clienteActual.usuario;
                        
                        input.nombre = $scope.clienteActual.nombre;

                        input.cedula = $scope.clienteActual.cedula;

                        input.correo = $scope.clienteActual.correo;
                        
                        input.direccion = $scope.clienteActual.direccion;
                        
                        input.telefono = $scope.clienteActual.telefono;
                        
                        $http.put(clientesContext + '/' + sessionStorage.getItem("id"), input).then(function (response) {
                            $state.go('clientesList', {clienteId: response.data.id}, {reload: true});
                        });
                    };
            }
        }]);
    
})(window.angular);