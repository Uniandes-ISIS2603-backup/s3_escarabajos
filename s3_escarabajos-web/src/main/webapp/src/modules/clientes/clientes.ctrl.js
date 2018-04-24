(function (ng) {

    var mod = ng.module("clientesModule");

    mod.controller("clientesCtrl", ['$scope', '$state', '$stateParams', '$http', 'clientesContext', function ($scope, $state, $stateParams, $http, context) {
    
    
     $http.get('http://localhost:8080/s3_escarabajos-web/api/clientes').then(function (response) {
                    $scope.records = response.data;
     });         
     
     this.deleteRecord = function (id) {
                $http.delete('http://localhost:8080/s3_escarabajos-web/api/clientes/'+ id);
                $state.reload('clientesList');
     };
     
     this.getRecord = function (id) {
                $http.get('http://localhost:8080/s3_escarabajos-web/api/clientes/'+ id);
     };

// Código continua con las funciones de despliegue de errores


        }]);
})(window.angular);

