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
                    $state.go('adv',{}, {reload: true});
                    $http.post("data/usuarios.json", sessionStorage);
                });
            };
    }]);

})(window.angular);

