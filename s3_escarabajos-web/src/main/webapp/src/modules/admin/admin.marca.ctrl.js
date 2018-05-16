(function (ng) {
    var mod = ng.module("adminModule");

    mod.constant("adminContextMarca", "api/catalogo/promociones");

    mod.controller("adminMarcaCtrl", ['$scope','$http','adminContextMarca', '$state',
        function ($scope, $http, adminContextMarca, $state) {  
            
            $scope.crearPromocion = function () {
                        console.log($scope);
                        $http.put(adminContextMarca + '/' + $scope.modelo.id + '/'+ $scope.descuento.d).then(function () {
                            $state.go('adminCategorias',{reload: true});
                        });
                    };
        }]);
    
})(window.angular);
