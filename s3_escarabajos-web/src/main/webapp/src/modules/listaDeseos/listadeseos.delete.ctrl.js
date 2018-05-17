(function (ng) {
    var mod = ng.module("ListadeseosMod");
    /**
     * @ngdoc controller
     * @name listaDeDeseos.controller:vaciarListadeseosCtrl
     * @description
     * Definición del controlador de Angular del módulo listaDeDeseos. 
     * Se crea el controlador con el cual se maneja el módulo.
     * En el controlador se definen los atributos y métodos que pueden
     * ser accedidos desde el HTML utilizando el $scope.
     * @param {Object} $scope Referencia injectada al Scope definida para este
     * controlador, el scope es el objeto que contiene las variables o 
     * funciones que se definen en este controlador y que son utilizadas 
     * desde el HTML.
     * @param {Object} $http Objeto injectado para la manejar consultas HTTP
     * @param {Object} listaDeDeseosContext Constante injectada que contiene la ruta
     * donde se encuentra el API de listaDeDeseos en el Backend.
     * @param {Object} $state Dependencia injectada en la que se recibe el 
     * estado actual de la navegación definida en el módulo.
     */
    mod.controller('vaciarListadeseosCtrl', ['$scope', '$http', '$state',
        function ($scope, $http, $state) {

            /**
             * @ngdoc function
             * @name getListaDeDeseos
             * @methodOf listaDeDeseos.controller:vaciarListadeseosCtrl
             * @description
             * Esta función utiliza el protocolo HTTP para obtener el recurso 
             * donde se encuentran los listaDeDeseos en formato JSON. El recurso
             * puede ser un archivo o un API Rest. La función se ejecuta
             * automáticamente cuando el controlador es accedido desde el
             * navegador.
             * @param {String} URL Dirección donde se encuentra el recurso
             * de los listaDeDeseos o API donde se puede consultar. Se utiliza el
             * contexto definido anteriormente.
             */
            $http.get('api/clientes/' + sessionStorage.getItem("id") + '/listadeseos').then(function (response) {

                var ruta = 'api/listadeseos/' + response.data.id + '/items';
                /**
                 * @ngdoc function
                 * @name deleteListaDeDeseos
                 * @methodOf listaDeDeseos.controller:vaciarListadeseosCtrl
                 * @description
                 * Esta función utiliza el protocolo HTTP para obtener el recurso 
                 * donde se encuentran los listaDeDeseos en formato JSON. El recurso
                 * puede ser un archivo o un API Rest. La función se ejecuta
                 * automáticamente cuando el controlador es accedido desde el
                 * navegador.
                 * @param {String} URL Dirección donde se encuentra el recurso
                 * de los listaDeDeseos o API donde se puede consultar. Se utiliza el
                 * contexto definido anteriormente.
                 */
                $http.delete(ruta).then(function () {
                    $state.go('listadeseos', {reload: true});
                });

            });
        }
    ]);
}
)(window.angular);