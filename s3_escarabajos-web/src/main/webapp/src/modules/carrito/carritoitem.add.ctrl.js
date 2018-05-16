(function (ng) {
    var mod = ng.module("CarritoMod");
    mod.controller('addCarritoCtrl', ['$scope', '$http', '$state',
        function ($scope, $http, $state) {


            $http.get('api/clientes/' + sessionStorage.getItem("id") + '/carrito').then(function (response) {


                //realmente es modeloId
                var modeloId = $state.params.itemId;

                var color = $state.params.color;

                console.log(modeloId);

                console.log(color);

                var modelo = {};

                $http.get('api/modelos/' + modeloId).then(function (response0) {

                    modelo = response0.data;

                    var modeloItems = modelo.items;


                    var encontro = false;

                    var itemId = modeloItems[0].id;

                    console.log(modeloItems);



                    $http.get('api/carrito/' + response.data.id + '/items').then(function (response2) {

                        var items = response2.data;

                        for (var m = 0; m < modeloItems.length && !encontro; m++) {



                            var estaEnCarrito = false;

                            console.log(m);

                            for (var a = 0; a < items.length && !estaEnCarrito; a++) {

                                console.log(m + " " + a);

                                if (items[a].id === modeloItems[m].id) {

                                    estaEnCarrito = true;
                                }
                            }

                            if (!estaEnCarrito) {


                                if (color === null || color === modeloItems[m].color) {

                                    itemId = modeloItems[m].id;

                                    console.log("encontro: " + itemId);

                                    encontro = true;
                                }
                            }

                        }

                        var ruta = 'api/carrito/' + response.data.id + '/items/';

                        console.log("itemId: " + itemId);

                        $http.post(ruta + itemId).then(function (response) {

                            $state.go('carrito', {itemId: response.data.id}, {reload: true});
                        }, function (response) {

                            if (response.status === 500) {
                                $state.go('yaEsta', {itemId: response.data.id}, {reload: true});
                            }
                        });
                    });




                });
            });
        }
    ]);
}
)(window.angular);