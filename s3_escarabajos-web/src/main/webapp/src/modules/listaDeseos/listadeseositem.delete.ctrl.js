(function (ng) {
    var mod = ng.module("ListadeseosMod");
    mod.controller('deleteListaDeseosCtrl', ['$scope', '$http', '$state',
        function ($scope, $http, $state) {
            
            // cambiar el 1 de la ruta por el id del cliente que este logeado
            $http.get('api/clientes/' + 1 + '/listadeseos').then(function (response) {

                ruta = 'api/listadeseos/' + response.data.id + '/items/';
            
                var itemId = $state.params.itemId;

                
                $http.delete(ruta + itemId).then(function (response) {
                    $state.go('listadeseos', {itemId: response.data.id}, {reload: true});
                });


            });
        }
    ]);
}
)(window.angular);