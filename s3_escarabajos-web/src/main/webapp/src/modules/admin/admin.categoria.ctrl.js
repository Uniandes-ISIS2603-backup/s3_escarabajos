(function (ng) {
    var mod = ng.module("adminModule");

    mod.constant("adminContextCategoria", "api/catalogo/categorias");

    mod.controller("adminCategoriaCtrl", ['$scope','$http','adminContextCategoria', '$state',
        function ($scope, $http, adminContextCategoria, $state) {  
            
            $scope.deleteCategoria = function () {
                        console.log($scope);
                        $http.delete(adminContextCategoria + '/' + $scope.categoria.nombre).then(function () {
                            $state.go('adminCategorias',{reload: true});
                        });
                    };
        }]);
    
})(window.angular);