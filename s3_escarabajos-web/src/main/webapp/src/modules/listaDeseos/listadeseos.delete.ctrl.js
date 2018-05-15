(function (ng) {
    var mod = ng.module("ListadeseosMod");
    mod.controller('vaciarListadeseosCtrl', ['$scope', '$http', '$state',
        function ($scope, $http, $state) {
            
              // cambiar el 1 de la ruta por el id del cliente que este logeado
            $http.get('api/clientes/' + sessionStorage.getItem("id") + '/listadeseos').then(function (response) {

                ruta = 'api/listadeseos/' + response.data.id + '/items';
                
                $http.delete(ruta).then(function (response) {
                    $state.go('listadeseos', {reload: true});
                });

            });
        }
    ]);
}
)(window.angular);