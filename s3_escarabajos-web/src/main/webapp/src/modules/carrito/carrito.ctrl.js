(function (ng) {
    var mod = ng.module("CarritoMod");
    mod.controller('carritoCtrl', ['$scope', '$http',
        function ($scope, $http) {
            $http.get('api/carrito/'+1+'/items').then(function (response) {
                $scope.carrito = response.data;
                
                var lista2 = [];
                
                var lista = $scope.carrito;
                
                var total = 0;
                
                for (i=0;i<lista.length; i++) {
                    
                    total = total + lista[i].precio;
                    
                    var repetido = false;
                    
                    for (j=0;j<lista2.length; j++) {
                        
                        if(lista[i].color===lista[j].color  && lista[i].modelo===lista[j].modelo && lista[i].precio===lista[j].precio && lista[i].tipo===lista[j].tipo && lista[i].referencia===lista[j].referencia){
                            
                            lista2[j].cantidad = lista2[j].cantidad + 1;
                            
                            repetido = true;
                        }
                    }
                    
                    if (repetido === false){
                        
                        lista[i].cantidad = 1;
                        
                        lista2.push(lista[i]);
                    }
                }
               
               $scope.total = total;
                
                $scope.carrito = lista2;

            });
            
            
        }
    ]);
}
)(window.angular);