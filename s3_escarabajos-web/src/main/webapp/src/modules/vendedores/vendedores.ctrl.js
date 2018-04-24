(function (ng) {

    var mod = ng.module("vendedoresModule");

    mod.controller("vendedoresCtrl", ['$scope', '$state', '$stateParams', '$http', 'vendedoresContext', function ($scope, $state, $stateParams, $http, context) {
    
    
     $http.get('http://localhost:8080/s3_escarabajos-web/api/vendedores').then(function (response) {
                    $scope.records = response.data;
     });         
     
     this.deleteRecord = function (id) {
                $http.delete('http://localhost:8080/s3_escarabajos-web/api/vendedores/'+ id);
                $state.reload('vendedoresList');
     };

        }]);
})(window.angular);

