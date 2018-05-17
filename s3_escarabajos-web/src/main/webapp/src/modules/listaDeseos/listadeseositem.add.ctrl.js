(function (ng) {
    var mod = ng.module("ListadeseosMod");
    /**
     * @ngdoc controller
     * @name listaDeDeseos.controller:addListaDeseosCtrl
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
    mod.controller('addListaDeseosCtrl', ['$scope', '$http', '$state',
        function ($scope, $http, $state) {

            /**
             * @ngdoc function
             * @name getListaDeDeseos
             * @methodOf listaDeDeseos.controller:addListaDeseosCtrl
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

                var modeloId = $state.params.itemId;

                var color = $state.params.color;

                var modelo = {};

                /**
                 * @ngdoc function
                 * @name getListaDeDeseos
                 * @methodOf listaDeDeseos.controller:addListaDeseosCtrl
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
                $http.get('api/modelos/' + modeloId).then(function (response0) {

                    modelo = response0.data;

                    var modeloItems = modelo.items;


                    var encontro = false;

                    var itemId = modeloItems[0].id;

                    /**
                     * @ngdoc function
                     * @name getListaDeDeseos
                     * @methodOf listaDeDeseos.controller:addListaDeseosCtrl
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
                    $http.get('api/listadeseos/' + response.data.id + '/items').then(function (response2) {

                        var items = response2.data;

                        for (var m = 0; m < modeloItems.length && !encontro; m++) {



                            var estaEnCarrito = false;

                            for (var a = 0; a < items.length && !estaEnCarrito; a++) {

                                if (items[a].id === modeloItems[m].id) {

                                    estaEnCarrito = true;
                                }
                            }

                            if (!estaEnCarrito) {

                                if (color === null || color === modeloItems[m].color) {

                                    itemId = modeloItems[m].id;

                                    encontro = true;
                                }
                            }

                        }

                        if (encontro) {

                            var ruta = 'api/listadeseos/' + response.data.id + '/items/';

                            /**
                             * @ngdoc function
                             * @name postListaDeDeseos
                             * @methodOf listaDeDeseos.controller:addListaDeseosCtrl
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
                            $http.post(ruta + itemId).then(function (response) {

                                $state.go('listadeseos', {itemId: response.data.id}, {reload: true});
                            }, function (response) {

                                if (response.status === 500) {
                                    $state.go('yaEstaLista', {itemId: response.data.id}, {reload: true});
                                }
                            });
                        } else {

                            $state.go('yaEstaLista', {itemId: response.data.id}, {reload: true});
                        }
                    });




                });
            });
        }
    ]);
}
)(window.angular);