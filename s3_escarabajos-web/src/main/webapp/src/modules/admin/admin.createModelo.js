(function (ng) {
/**
     * @ngdoc controller
     * @name admin.create.modelo.controller:adminCreateCtrl
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
     * @param {Object} modeloContext Constante injectada que contiene la ruta
     * donde se encuentra el API de modelos en el Backend.
     * @param {Object} $state Dependencia injectada en la que se recibe el 
     * estado actual de la navegación definida en el módulo.
     */
    var mod = ng.module("adminModule");
    
    mod.constant("modeloContext","api/modelos");
    
    mod.controller('adminCreateCtrl',['$scope','$http','modeloContext', '$state',
        function($scope,$http,modeloContext, $state){
            $scope.modeloActual = {};         
            $scope.createModelo = function () {
                
                var input = {};
                input.calificacionMedia = 0;
                input.id = 0;
                input.tipoModelo = $scope.modeloActual.tipoModelo;
                input.referencia = $scope.modeloActual.referencia;
                input.marca = $scope.modeloActual.marca;
                
                console.log.data;
                $http.post(modeloContext, input).then(function () {
                    $state.go('adminCategorias',{reload: true});
                });
            };
    }]);

})(window.angular);
