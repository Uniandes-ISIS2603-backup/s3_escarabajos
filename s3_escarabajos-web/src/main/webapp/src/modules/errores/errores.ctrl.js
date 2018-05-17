(function (ng) {
     /**
     * @ngdoc controller
     * @name error.controller:errorCtrl
     * @description
     * Definición del controlador de Angular del módulo error. 
     * Se crea el controlador con el cual se maneja el módulo.
     * En el controlador se definen los atributos y métodos que pueden
     * ser accedidos desde el HTML utilizando el $scope.
     * @param {Object} $scope Referencia injectada al Scope definida para este
     * controlador, el scope es el objeto que contiene las variables o 
     * funciones que se definen en este controlador y que son utilizadas 
     * desde el HTML.
     * @param {Object} $state Dependencia injectada en la que se recibe el 
     * estado actual de la navegación definida en el módulo.
     */

    var mod = ng.module("errorMod");
    
    mod.controller('errorCtrl', ['$scope', '$state',
        function ($scope, $state) {
            document.getElementById("errorCode").innerHTML = $state.params.status;
            document.getElementById("errorMessage").innerHTML = $state.params.mensaje;
        }]);

})(window.angular);

