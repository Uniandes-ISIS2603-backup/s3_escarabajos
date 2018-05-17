(function (ng) {
    var mod = ng.module("adminModule");

    mod.constant("adminContextMarca", "api/catalogo/marcas");

    mod.controller("adminMarcaCtrl", ['$scope','$http','adminContextMarca', '$state',
        function ($scope, $http, adminContextMarca, $state) {  
            
            $scope.deleteMarca = function () {
                        console.log($scope);
                        $http.delete(adminContextMarca + '/' + $scope.marca.nombre).then(function () {
                            $state.go('adminCategorias',{reload: true});
                        });
                    };
        }]);
    
})(window.angular);
