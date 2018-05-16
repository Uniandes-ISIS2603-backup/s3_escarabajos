(function (ng) {
    var mod = ng.module("adminModule");

    mod.constant("adminContextPromocion", "api/catalogo/promociones");

    mod.controller("adminPromocionesCtrl", ['$scope','$http','adminContextPromocion', '$state',
        function ($scope, $http, adminContextPromocion, $state) {  
            
            $scope.crearPromocion = function () {
                        console.log($scope);
                        $http.put(adminContextPromocion + '/' + $scope.modelo.id + '/'+ $scope.descuento.d).then(function () {
                            $state.go('adminCategorias',{reload: true});
                        });
                    };
        }]);
    
})(window.angular);


