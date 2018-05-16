(function (ng) {
    var mod = ng.module("ListadeseosMod");
    mod.controller('addListaDeseosCtrl', ['$scope', '$http', '$state',
        function ($scope, $http, $state) {


            $http.get('api/clientes/' + sessionStorage.getItem("id") + '/listadeseos').then(function (response) {


                //realmente es modeloId
                var modeloId = $state.params.itemId;

                var color = $state.params.color;

                var modelo = {};

                $http.get('api/modelos/' + modeloId).then(function (response0) {

                    modelo = response0.data;

                    var modeloItems = modelo.items;


                    var encontro = false;

                    var itemId = modeloItems[0].id;

                    $http.get('api/listadeseos/' + response.data.id + '/items').then(function (response2) {

                        var items = response2.data;

                        for (var m = 0; m < modeloItems.length && !encontro; m++) {



                            var estaEnCarrito = false;

                            for (var a = 0; a < items.length && !estaEnCarrito; a++) {

                                if (items[a].id === modeloItems[m].id) {

                                    estaEnCarrito = true;
                                }
                            }
                            
                            if (!estaEnCarrito) {

                                if (color === null || color === modeloItems[m].color) {

                                    itemId = modeloItems[m].id;

                                    encontro = true;
                                }
                            }

                        }

                        if (encontro){

                            var ruta = 'api/listadeseos/' + response.data.id + '/items/';

                            $http.post(ruta + itemId).then(function (response) {

                                $state.go('listadeseos', {itemId: response.data.id}, {reload: true});
                            }, function (response) {

                                if (response.status === 500) {
                                    $state.go('yaEstaLista', {itemId: response.data.id}, {reload: true});
                                }
                            });
                        }
                        else{
                            
                            $state.go('yaEstaLista', {itemId: response.data.id}, {reload: true});
                        }
                    });




                });
            });
        }
    ]);
}
)(window.angular);