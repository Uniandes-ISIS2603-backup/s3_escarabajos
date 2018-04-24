(function (ng) {
    var app = angular.module('mainApp', [
        // External dependencies
        'ui.router',
        // Internal modules dependencies       
          'moduloBicis',
          'moduloBicisUsadas',
          'CarritoMod',
          'reclamosModule',
          'ListadeseosMod',
          'AccesoriosMod',
          'clientesModule',
          'moduloFacturas',
          'vendedoresModule',
          'catalogoModule'
        



    ]);
    // Resuelve problemas de las promesas
    app.config(['$qProvider', function ($qProvider) {
            $qProvider.errorOnUnhandledRejections(false);
        }]);
})(window.angular);

