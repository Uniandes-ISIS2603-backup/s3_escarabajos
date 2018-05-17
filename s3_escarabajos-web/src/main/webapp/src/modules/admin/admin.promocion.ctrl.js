(function (ng) {
     /**
     * @ngdoc controller
     * @name accesorio.promocion.controller:adminCategoriaCtrl
     * @description
     * Definición del controlador de Angular del módulo de la vista de las categorias para
     * el administrador. 
     * Se crea el controlador con el cual se maneja el módulo.
     * En el controlador se definen los atributos y métodos que pueden
     * ser accedidos desde el HTML utilizando el $scope.
     * @param {Object} $scope Referencia injectada al Scope definida para este
     * controlador, el scope es el objeto que contiene las variables o 
     * funciones que se definen en este controlador y que son utilizadas 
     * desde el HTML.
     * @param {Object} $http Objeto injectado para la manejar consultas HTTP
     * @param {Object} adminContextPromocion Constante injectada que contiene la ruta
     * donde se encuentra el API de promocion en el Backend.
     * @param {Object} $state Dependencia injectada en la que se recibe el 
     * estado actual de la navegación definida en el módulo.
     */
    var mod = ng.module("adminModule");

    mod.constant("adminContextPromocion", "api/catalogo/promociones");

    mod.controller("adminPromocionesCtrl", ['$scope','$http','adminContextPromocion', '$state',
        function ($scope, $http, adminContextPromocion, $state) {  
            
            $scope.crearPromocion = function () {
                        $http.put(adminContextPromocion + '/' + $scope.modelo.id + '/'+ $scope.descuento.d).then(function () {
                            $state.go('adminCategorias',{reload: true});
                        });
                    };
        }]);
    
})(window.angular);


