(function (ng) {
    /**
     * @ngdoc controller
     * @name clientes.create.controller:clientesCreateCtrl
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

    mod.controller('clientesCreateCtrl', ['$scope', '$http', 'clientesContext', '$state',
        function ($scope, $http, clientesContext, $state) {
            $scope.data = {};

            $scope.createCliente = function () {
                /**
                 * @ngdoc function
                 * @name createCliente
                 * @methodOf calificaciones.list.controller:calificacionesListCtrl
                 * @description
                 * Esta función utiliza el protocolo HTTP para obtener el recurso 
                 * donde se encuentran todas las calificaciones del modelo especificado en formato JSON. El recurso
                 * puede ser un archivo o un API Rest. La función se ejecuta
                 * automáticamente cuando el controlador es accedido desde el
                 * navegador.
                 * @param {clientesContext} URL Dirección donde se encuentra el recurso
                 * del accesorio especificado o API donde se puede consultar. Se utiliza el
                 * contexto definido anteriormente.
                 * @param {$scope.data} data URL Dirección donde se encuentra el recurso
                 * del accesorio especificado o API donde se puede consultar. Se utiliza el
                 * contexto definido anteriormente.
                 */
                $http.post(clientesContext, $scope.data).then(function (response) {
                    sessionStorage.token = response.data;
                    sessionStorage.setItem("username", response.data.usuario);
                    sessionStorage.setItem("rol", "Administrador");
                    sessionStorage.setItem("id", response.data.id);
                    sessionStorage.setItem("name", response.data.nombre);
                    $state.go('adv', {adv: true, tipo: 'bicicletas', filtros: {marcas: [], categorias: [], colores: [], precioMin: 0.0, precioMax: 999999999.9, calificacionMin: 0.0}}, {reload: true});
                    $http.post("data/usuarios.json", sessionStorage);
                });
            };
        }]);

})(window.angular);

