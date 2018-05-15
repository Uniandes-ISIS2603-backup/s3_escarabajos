(function (ng) {
    var mod = ng.module("ListadeseosMod");
    mod.controller('addListaDeseosCtrl', ['$scope', '$http', '$state',
        function ($scope, $http, $state) {

            // cambiar el 1 de la ruta por el id del cliente que este logeado
            $http.get('api/clientes/' + sessionStorage.getItem("id") + '/listadeseos').then(function (response) {

                var ruta = 'api/listadeseos/' + response.data.id + '/items/';

                var itemId = $state.params.itemId;

                $http.post(ruta + itemId).then(function (response) {
                    
                    $state.go('listadeseos', {itemId: response.data.id}, {reload: true});
                }, function(response){
                    
                    if(response.status === 500){
                        $state.go('yaEstaLista', {itemId: response.data.id}, {reload: true});
                    }
                });


            });
        }
    ]);
}
)(window.angular);