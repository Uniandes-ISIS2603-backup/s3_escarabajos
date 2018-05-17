(function (ng) {
    /**
     * @ngdoc controller
     * @name bicicleta.create.controller:bicicletaCreateCtrl
     * @description
     * Definición del controlador de Angular del módulo del create de un Bicicleta. 
     * Se crea el controlador con el cual se maneja el módulo.
     * En el controlador se definen los atributos y métodos que pueden
     * ser accedidos desde el HTML utilizando el $scope.
     * @param {Object} $scope Referencia injectada al Scope definida para este
     * controlador, el scope es el objeto que contiene las variables o 
     * funciones que se definen en este controlador y que son utilizadas 
     * desde el HTML.
     * @param {Object} $http Objeto injectado para la manejar consultas HTTP
     * @param {Object} bicicletaContext Constante injectada que contiene la ruta
     * donde se encuentra el API de Bicicletas en el Backend.
     * @param {Object} $state Dependencia injectada en la que se recibe el 
     * estado actual de la navegación definida en el módulo.
     */
    var mod = ng.module("moduloBicis");
    
    mod.constant("biciContext","api/bicis");
    
    mod.controller('biciCreateCtrl',['$scope','$http','biciContext','$state',
        function($scope,$http,biciContext,$state){

            $scope.data = {};

            /**
             * @ngdoc function
             * @name createBici
             * @methodOf bicis.controller:biciCreateCtrl
             * @description
             * Esta función utiliza el protocolo HTTP para crear la bicicleta.
             * @param {Object} bici Objeto con la nueva de la bicicleta.
             */
            
            $scope.createBici = function () {
                var input = {};
                
                input.id = 9999;
                
                input.precio = parseInt($scope.data.precio);
                
                input.modeloId = parseInt($scope.data.modeloId);
                
                input.color = $scope.data.color;
                
                input.categoria = $scope.data.categoria;
                
                input.album  = $scope.data.album;
                
                input.usada = false;
                
                input.disponible  = true;
                
                $http.post(biciContext, input).then(function (response) {
                    $state.go('bicisList', {biciId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);