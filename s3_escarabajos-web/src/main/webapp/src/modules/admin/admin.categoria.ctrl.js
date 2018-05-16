(function (ng) {
    var mod = ng.module("adminModule");

    mod.constant("adminContextCategoria", "api/catalogo/promociones");

    mod.controller("adminCategoriaCtrl", ['$scope','$http','adminContextCategoria', '$state',
        function ($scope, $http, adminContextCategoria, $state) {  
            
            $scope.crearPromocion = function () {
                        console.log($scope);
                        $http.put(adminContextCategoria + '/' + $scope.modelo.id + '/'+ $scope.descuento.d).then(function () {
                            $state.go('adminCategorias',{reload: true});
                        });
                    };
        }]);
    
})(window.angular);