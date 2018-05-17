(function (ng) {
    var mod = ng.module("reclamosModule");
    mod.constant("reclamosContext", "api/clientes/reclamos");
    /**
     * @ngdoc controller
     * @name reclamos.controller:reclamosDetailCtrl
     * @description
     * Definición del controlador de Angular del módulo reclamos. 
     * Se crea el controlador con el cual se maneja el módulo.
     * En el controlador se definen los atributos y métodos que pueden
     * ser accedidos desde el HTML utilizando el $scope.
     * @param {Object} $scope Referencia injectada al Scope definida para este
     * controlador, el scope es el objeto que contiene las variables o 
     * funciones que se definen en este controlador y que son utilizadas 
     * desde el HTML.
     * @param {Object} $http Objeto injectado para la manejar consultas HTTP
     * @param {Object} reclamoContext Constante injectada que contiene la ruta
     * donde se encuentra el API de Reclamos en el Backend.
     * @param {Object} $state Dependencia injectada en la que se recibe el 
     * estado actual de la navegación definida en el módulo.
     */
    mod.controller('reclamosCtrl', ['$scope', '$rootScope', '$http', 'reclamosContext',
        function ($scope, $rootScope, $http, reclamosContext) {
            /**
             * @ngdoc function
             * @name getReclamos
             * @methodOf reclamos.controller:reclamosCtrl
             * @description
             * Esta función utiliza el protocolo HTTP para obtener el recurso 
             * donde se encuentran los reclamos en formato JSON. El recurso
             * puede ser un archivo o un API Rest. La función se ejecuta
             * automáticamente cuando el controlador es accedido desde el
             * navegador.
             * @param {String} URL Dirección donde se encuentra el recurso
             * de los reclamos o API donde se puede consultar. Se utiliza el
             * contexto definido anteriormente.
             */
            $http.get(reclamosContext + "/detail/" + sessionStorage.getItem("id")).then(function (response) {
                $scope.reclamosRecords = response.data;
            }, function (response) {
                $rootScope.showError(response);
            });
        }
    ]);
    /**
     * @ngdoc controller
     * @name reclamos.controller:reclamosDetailCtrl
     * @description
     * Definición del controlador de Angular del módulo reclamos. 
     * Se crea el controlador con el cual se maneja el módulo.
     * En el controlador se definen los atributos y métodos que pueden
     * ser accedidos desde el HTML utilizando el $scope.
     * @param {Object} $scope Referencia injectada al Scope definida para este
     * controlador, el scope es el objeto que contiene las variables o 
     * funciones que se definen en este controlador y que son utilizadas 
     * desde el HTML.
     * @param {Object} $http Objeto injectado para la manejar consultas HTTP
     * @param {Object} reclamosContext Constante injectada que contiene la ruta
     * donde se encuentra el API de Reclamos en el Backend.
     * @param {Object} $state Dependencia injectada en la que se recibe el 
     * estado actual de la navegación definida en el módulo.
     */
    mod.controller("reclamosDetailCtrl", ['$scope', '$rootScope', 'reclamosContext', '$http', '$state',
        function ($scope, $rootScope, reclamosContext, $http, $state) {
            if ($state.params.reclamoId !== undefined && $state.params.reclamoId !== null) {
                $scope.data = {};
                /**
                 * @ngdoc function
                 * @name getReclamos
                 * @methodOf reclamos.controller:reclamosDetailCtrl
                 * @description
                 * Esta función utiliza el protocolo HTTP para obtener el recurso 
                 * donde se encuentran los reclamos en formato JSON. El recurso
                 * puede ser un archivo o un API Rest. La función se ejecuta
                 * automáticamente cuando el controlador es accedido desde el
                 * navegador.
                 * @param {String} URL Dirección donde se encuentra el recurso
                 * de los reclamos o API donde se puede consultar. Se utiliza el
                 * contexto definido anteriormente.
                 */
                $http.get(reclamosContext + "/" + $state.params.reclamoId).then(function (response) {
                    $scope.reclamoActual = response.data;
                }, function (response) {
                    $rootScope.showError(response);
                });
                $scope.terminar = function () {
                    /**
                     * @ngdoc function
                     * @name putReclamos
                     * @methodOf reclamos.controller:reclamosDetailCtrl
                     * @description
                     * Esta función utiliza el protocolo HTTP para obtener el recurso 
                     * donde se encuentran los reclamos en formato JSON. El recurso
                     * puede ser un archivo o un API Rest. La función se ejecuta
                     * automáticamente cuando el controlador es accedido desde el
                     * navegador.
                     * @param {String} URL Dirección donde se encuentra el recurso
                     * de los reclamos o API donde se puede consultar. Se utiliza el
                     * contexto definido anteriormente.
                     */
                    $http.put(reclamosContext + "/" + $state.params.reclamoId + "/finalizar").then(
                            $state.go('^.reclamosDetail', {reclamoId: $state.params.reclamoId}, {reload: true}));
                };
                $scope.renaudar = function () {
                    /**
                     * @ngdoc function
                     * @name putReclamos
                     * @methodOf reclamos.controller:reclamosDetailCtrl
                     * @description
                     * Esta función utiliza el protocolo HTTP para obtener el recurso 
                     * donde se encuentran los reclamos en formato JSON. El recurso
                     * puede ser un archivo o un API Rest. La función se ejecuta
                     * automáticamente cuando el controlador es accedido desde el
                     * navegador.
                     * @param {String} URL Dirección donde se encuentra el recurso
                     * de los reclamos o API donde se puede consultar. Se utiliza el
                     * contexto definido anteriormente.
                     */
                    $http.put(reclamosContext + "/" + $state.params.reclamoId + "/renaudar").then(
                            $state.go('^.reclamosDetail', {reclamoId: $state.params.reclamoId}, {reload: true}));
                };
                $scope.updateMensaje = function () {
                    /**
                     * @ngdoc function
                     * @name putReclamos
                     * @methodOf reclamos.controller:reclamosDetailCtrl
                     * @description
                     * Esta función utiliza el protocolo HTTP para obtener el recurso 
                     * donde se encuentran los reclamos en formato JSON. El recurso
                     * puede ser un archivo o un API Rest. La función se ejecuta
                     * automáticamente cuando el controlador es accedido desde el
                     * navegador.
                     * @param {String} URL Dirección donde se encuentra el recurso
                     * de los reclamos o API donde se puede consultar. Se utiliza el
                     * contexto definido anteriormente.
                     */
                    $http.put(reclamosContext + "/" + $state.params.reclamoId, $scope.data).then(function (response) {
                        $state.go('^.reclamosDetail', {reclamoId: $state.params.reclamoId}, {reload: true});
                    }, function (response) {
                        $rootScope.showError(response);
                    });
                };
            }
        }]);
    /**
     * @ngdoc controller
     * @name reclamos.controller:reclamoCreateCtrl
     * @description
     * Definición del controlador de Angular del módulo reclamos. 
     * Se crea el controlador con el cual se maneja el módulo.
     * En el controlador se definen los atributos y métodos que pueden
     * ser accedidos desde el HTML utilizando el $scope.
     * @param {Object} $scope Referencia injectada al Scope definida para este
     * controlador, el scope es el objeto que contiene las variables o 
     * funciones que se definen en este controlador y que son utilizadas 
     * desde el HTML.
     * @param {Object} $http Objeto injectado para la manejar consultas HTTP
     * @param {Object} reclamosContext Constante injectada que contiene la ruta
     * donde se encuentra el API de Reclamos en el Backend.
     * @param {Object} $state Dependencia injectada en la que se recibe el 
     * estado actual de la navegación definida en el módulo.
     */
    mod.controller('reclamoCreateCtrl', ['$scope', '$http', 'reclamosContext', '$state', '$rootScope',
        function ($scope, $http, reclamosContext, $state, $rootScope) {
            $rootScope.edit = false;
            $scope.data = {};
            $scope.facturaId = null;
            if ((sessionStorage.getItem("id") !== undefined) && (sessionStorage.getItem("id") !== null)) {
                /**
                 * @ngdoc function
                 * @name getReclamos
                 * @methodOf reclamos.controller:reclamoCreateCtrl
                 * @description
                 * Esta función utiliza el protocolo HTTP para obtener el recurso 
                 * donde se encuentran los reclamos en formato JSON. El recurso
                 * puede ser un archivo o un API Rest. La función se ejecuta
                 * automáticamente cuando el controlador es accedido desde el
                 * navegador.
                 * @param {String} URL Dirección donde se encuentra el recurso
                 * de los reclamos o API donde se puede consultar. Se utiliza el
                 * contexto definido anteriormente.
                 */
                $http.get('api/facturas/cliente/' + sessionStorage.getItem("id")).then(function (response) {
                    $scope.facturas2 = response.data;
                }, function (response) {
                    $rootScope.showError(response);
                });


            }
            ;
            $scope.createReclamo = function () {
                /**
                 * @ngdoc function
                 * @name postReclamos
                 * @methodOf reclamos.controller:reclamoCreateCtrl
                 * @description
                 * Esta función utiliza el protocolo HTTP para obtener el recurso 
                 * donde se encuentran los reclamos en formato JSON. El recurso
                 * puede ser un archivo o un API Rest. La función se ejecuta
                 * automáticamente cuando el controlador es accedido desde el
                 * navegador.
                 * @param {String} URL Dirección donde se encuentra el recurso
                 * de los reclamos o API donde se puede consultar. Se utiliza el
                 * contexto definido anteriormente.
                 */
                $http.post(reclamosContext + '/' + sessionStorage.getItem("id") + '/facturas/' + $scope.facturaId, $scope.data).then(function (response) {
                    $state.go('reclamosList', {reclamoId: response.data.id}, {reload: true});
                }, function (response) {
                    $rootScope.showError(response);
                });
            };
        }
    ]);
    /**
     * @ngdoc controller
     * @name reclamos.controller:'reclamosAdminCtrl
     * @description
     * Definición del controlador de Angular del módulo reclamos. 
     * Se crea el controlador con el cual se maneja el módulo.
     * En el controlador se definen los atributos y métodos que pueden
     * ser accedidos desde el HTML utilizando el $scope.
     * @param {Object} $scope Referencia injectada al Scope definida para este
     * controlador, el scope es el objeto que contiene las variables o 
     * funciones que se definen en este controlador y que son utilizadas 
     * desde el HTML.
     * @param {Object} $http Objeto injectado para la manejar consultas HTTP
     * @param {Object} reclamoContext Constante injectada que contiene la ruta
     * donde se encuentra el API de Reclamos en el Backend.
     * @param {Object} $state Dependencia injectada en la que se recibe el 
     * estado actual de la navegación definida en el módulo.
     */
    mod.controller('reclamosAdminCtrl', ['$scope', '$rootScope', '$http', 'reclamosContext',
        function ($scope, $rootScope, $http, reclamosContext) {
            /**
             * @ngdoc function
             * @name getReclamos
             * @methodOf reclamos.controller:reclamosAdminCtrl
             * @description
             * Esta función utiliza el protocolo HTTP para obtener el recurso 
             * donde se encuentran los reclamos en formato JSON. El recurso
             * puede ser un archivo o un API Rest. La función se ejecuta
             * automáticamente cuando el controlador es accedido desde el
             * navegador.
             * @param {String} URL Dirección donde se encuentra el recurso
             * de los reclamos o API donde se puede consultar. Se utiliza el
             * contexto definido anteriormente.
             */
            $http.get(reclamosContext).then(function (response) {
                $scope.reclamosRecords = response.data;
            }, function (response) {
                $rootScope.showError(response);
            });
        }
    ]);
}
)(window.angular);