(function (ng) {

    var mod = ng.module("clientesModule");
    
    mod.constant("clientesContext","api/clientes");
    
    mod.controller('clientesCreateCtrl',['$scope','$http','clientesContext', '$state',
        function($scope,$http,clientesContext, $state){
            $scope.data = {};            
            $scope.createCliente = function () {
                $http.post(clientesContext, $scope.data).then(function (response) {
                    sessionStorage.token = response.data;
                    sessionStorage.setItem("username", response.data.usuario);
                    sessionStorage.setItem("rol", "Administrador");
                    sessionStorage.setItem("id", response.data.id);
                    sessionStorage.setItem("name", response.data.nombre);
                    $state.go('adv',{adv:true,tipo:'bicicletas',filtros:{marcas:[],categorias:[],colores:[],precioMin:0.0,precioMax:999999999.9,calificacionMin:0.0}}, {reload: true});
                    $http.post("data/usuarios.json", sessionStorage);
                });
            };
    }]);

})(window.angular);

