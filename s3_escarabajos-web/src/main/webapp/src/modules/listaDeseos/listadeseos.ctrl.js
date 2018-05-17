(function (ng) {
    var mod = ng.module("ListadeseosMod");
    /**
     * @ngdoc controller
     * @name listaDeDeseos.controller:listadeseosCtrl
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
    mod.controller('listadeseosCtrl', ['$scope', '$http',
        function ($scope, $http) {
             /**
                 * @ngdoc function
                 * @name getListaDeDeseos
                 * @methodOf listaDeDeseos.controller:listadeseosCtrl
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
                 * @name getListaDeDeseos
                 * @methodOf listaDeDeseos.controller:listadeseosCtrl
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
                $http.get(ruta).then(function (response) {
                    $scope.listadeseos = response.data;

                    var lista2 = [];

                    var lista = $scope.listadeseos;

                    var total = 0;

                    for (var i = 0; i < lista.length; i++) {

                        total = total + lista[i].precio;

                        var repetido = false;

                        for (var j = 0; j < lista2.length; j++) {

                            if (lista[i].color === lista[j].color && lista[i].modelo === lista[j].modelo) {

                                if (lista[i].precio === lista[j].precio && lista[i].tipo === lista[j].tipo && lista[i].referencia === lista[j].referencia) {

                                    lista2[j].cantidad = lista2[j].cantidad + 1;

                                    repetido = true;
                                }
                            }
                        }

                        if (repetido === false) {

                            lista[i].cantidad = 1;

                            lista2.push(lista[i]);
                        }
                    }

                    $scope.total = total;

                    $scope.listadeseos = lista2;

                });

            });

        }
    ]);
}
)(window.angular);