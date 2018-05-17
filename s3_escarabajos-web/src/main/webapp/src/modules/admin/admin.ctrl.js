(function (ng) {
/**
     * @ngdoc controller
     * @name admin.controller:adminCreateCtrl
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
     * @param {Object} accesorioContext Constante injectada que contiene la ruta
     * donde se encuentra el API de modelos en el Backend.
     * @param {Object} $state Dependencia injectada en la que se recibe el 
     * estado actual de la navegación definida en el módulo.
     */
    var mod = ng.module("adminModule");
    
    mod.constant("adminContext","api/modelos");
    
    mod.controller('adminCtrl',['$scope','$http','adminContext', '$state',
        function($scope,$http,adminContext, $state){
            
            $scope.listaClientes ={};
            
            $http.get(adminContext).then(function(response){
                $scope.records = response.data;
            });
            
            this.deleteRecord = function (id) {
                $http.delete('http://localhost:8080/s3_escarabajos-web/api/modelos/'+ id);
                $state.reload('adminCategorias');
            };
     
            this.getRecord = function (id) {
                $http.get('http://localhost:8080/s3_escarabajos-web/api/modelos/'+ id);
            };

    }]);

})(window.angular);


