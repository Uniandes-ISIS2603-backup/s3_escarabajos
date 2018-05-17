(function (ng) {
    /**
     * @ngdoc controller
     * @name calificaciones.controller:calificacionesListCtrl
     * @description
     * Definición del controlador de Angular del módulo Calificaciòn. 
     * Se crea el controlador con el cual se maneja el módulo.
     * En el controlador se definen los atributos y métodos que pueden
     * ser accedidos desde el HTML utilizando el $scope.
     * @param {Object} $scope Referencia injectada al Scope definida para este
     * controlador, el scope es el objeto que contiene las variables o 
     * funciones que se definen en este controlador y que son utilizadas 
     * desde el HTML.
     * @param {Object} $http Objeto injectado para la manejar consultas HTTP
     * @param {Object} accesorioContext Constante injectada que contiene la ruta
     * donde se encuentra el API de Accesorios en el Backend.
     * @param {Object} $state Dependencia injectada en la que se recibe el 
     * estado actual de la navegación definida en el módulo.
     */
    var mod = ng.module("modelosModule");


    mod.controller("calificacionesListCtrl", ['$scope', '$rootScope', '$http', '$state',
        function ($scope, $rootScope, $http, $state) {
            $scope.data = {};
            if ($state.params.modeloId !== undefined && $state.params.modeloId !== null) {
                try {
                    /**
                     * @ngdoc function
                     * @name getCalificaciones
                     * @methodOf calificaciones.list.controller:calificacionesListCtrl
                     * @description
                     * Esta función utiliza el protocolo HTTP para obtener el recurso 
                     * donde se encuentran todas las calificaciones del modelo especificado en formato JSON. El recurso
                     * puede ser un archivo o un API Rest. La función se ejecuta
                     * automáticamente cuando el controlador es accedido desde el
                     * navegador.
                     * @param {String} URL Dirección donde se encuentra el recurso
                     * del accesorio especificado o API donde se puede consultar. Se utiliza el
                     * contexto definido anteriormente.
                     */
                    $http.get('api/modelos/' + $state.params.modeloId + '/clientes/calificaciones', ).then(function (response) {
                        $scope.calificaciones = response.data;
                    }, function (response) {
                        $rootScope.showError(response);
                    });
                    $scope.createCalificacion = function ()
                    {
                        /**
                         * @ngdoc function
                         * @name postCalificacion
                         * @methodOf calificaciones.post.controller:calificacionesListCtrl
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
                        $http.post('api/modelos/' + $state.params.modeloId + '/clientes/' + sessionStorage.getItem("id") + '/calificaciones', $scope.data).then(function (response) {
                            $state.go('modeloDetail', {modeloId: $state.params.modeloId}, {reload: true})
                        }, function (response) {
                            $rootScope.showError(response);
                        });
                    };
                } catch (e) {
                    alert(e);
                }
            }
        }]);

})(window.angular);