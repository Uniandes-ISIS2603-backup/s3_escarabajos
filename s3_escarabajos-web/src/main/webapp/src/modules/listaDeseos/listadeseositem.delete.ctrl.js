(function (ng) {
    var mod = ng.module("ListadeseosMod");
    /**
     * @ngdoc overview
     * @name listaDeDeseos.module:deleteListaDeseosCtrl
     * @description
     * Definición del módulo de Angular de listaDeDeseos. El módulo encapsula todos los 
     * controladores y los templates HTML que estén relacionados con los listaDeDeseos 
     * directamente. En la configuración del módulo se injecta la dependencia de 
     * ui.router que es la que se utiliza para la configuración de las URLs bajo las
     * cuales se accede al módulo. Por ejemplo, para mostrar los listaDeDeseos en la 
     * URL: 'localhost:8080/api/listadeseos/{id: \\d+}/items/{id: \\d+}' es necesario configurar el router por 
     * medio del stateProvider que informa a AngularJS de la relación entre la URL, 
     * un estado definido (estado de mostrar listaDeDeseos), el controlador y la vista 
     * correspondiente.
     */
    mod.controller('deleteListaDeseosCtrl', ['$scope', '$http', '$state',
        function ($scope, $http, $state) {

            /**
             * @ngdoc function
             * @name getListaDeDeseos
             * @methodOf listaDeDeseos.controller:deleteListaDeseosCtrl
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

                var ruta = 'api/listadeseos/' + response.data.id + '/items/';

                var itemId = $state.params.itemId;

                /**
                 * @ngdoc function
                 * @name deleteListaDeDeseos
                 * @methodOf listaDeDeseos.controller:deleteListaDeseosCtrl
                 * @description
                 * Esta función utiliza el protocolo HTTP para obtener el recurso 
                 * donde se encuentran los listaDeDeseos en formato JSON. El recurso
                 * puede ser un archivo o un API Rest. La función se ejecuta
                 * automáticamente cuando el controlador es accedido desde el
                 * navegador.
                 * @param {ruta + itemId } URL Dirección donde se encuentra el recurso
                 * de los listaDeDeseos o API donde se puede consultar. Se utiliza el
                 * contexto definido anteriormente.
                 */
                $http.delete(ruta + itemId).then(function (response) {
                    $state.go('listadeseos', {itemId: response.data.id}, {reload: true});
                });


            });
        }
    ]);
}
)(window.angular);