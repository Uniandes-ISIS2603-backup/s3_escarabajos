(function (ng) {
    /**
     * @ngdoc controller
     * @name accesorio.categoria.controller:adminCategoriaCtrl
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
     * @param {Object} adminContextCategoria Constante injectada que contiene la ruta
     * donde se encuentra el API de Categorias en el Backend.
     * @param {Object} $state Dependencia injectada en la que se recibe el 
     * estado actual de la navegación definida en el módulo.
     */
    var mod = ng.module("adminModule");

    mod.constant("adminContextCategoria", "api/catalogo/categorias");

    mod.controller("adminCategoriaCtrl", ['$scope','$http','adminContextCategoria', '$state',
        function ($scope, $http, adminContextCategoria, $state) {  
            
            $scope.deleteCategoria = function () {
                        $http.delete(adminContextCategoria + '/' + $scope.categoria.nombre).then(function () {
                            $state.go('adminCategorias',{reload: true});
                        });
                    };
        }]);
    
})(window.angular);