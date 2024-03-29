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
        'catalogoModule',
        'modelosModule',
        'adminModule',
        'moduloComprar',
        'moduloMediosPago',
        'errorMod'
    ]);
    // Resuelve problemas de las promesas
    app.config(['$qProvider', function ($qProvider) {
            $qProvider.errorOnUnhandledRejections(false);

        }]);
    app.run(['$rootScope', "$state", function ($rootScope, $state) {
            $state.go('principal', {pagina: 1}, {reload: true});
            /**
             * @ngdoc function
             * @name isAuthenticated
             * @methodOf mainApp.module:mainApp
             * @description Esta función define si el usuario se encuentra
             * dentro de su cuenta.
             * @returns {Boolean} Verdadero si está dentro de su cuenta.
             */
            $rootScope.isAuthenticated = function () {
                if (sessionStorage.getItem("username") === "null" || sessionStorage.getItem("id") === null) {
                    return false;
                } else {
                    return true;
                }
            };

            /**
             * @ngdoc function
             * @name irBicicletas
             * @methodOf mainApp.module:mainApp
             * @description funcion que se encarga de buscar modelos.
             */
            $rootScope.buscar = function () {
                $state.go('busqueda', {busqueda: $rootScope.busq}, {reload: true});
            };


            /**
             * @ngdoc function
             * @name irBicicletas
             * @methodOf mainApp.module:mainApp
             * @description Esta funcion se usa para ir al catalogo de bicicletas sin filtros.
             */
            $rootScope.irBicicletas = function () {
                $state.go('adv', {adv: true, tipo: 'bicicletas', filtros: {marcas: [], categorias: [], colores: [], precioMin: 0.0, precioMax: 999999999.9, calificacionMin: 0.0}}, {reload: true});
            };

            /**
             * @ngdoc function
             * @name irBicicletas
             * @methodOf mainApp.module:mainApp
             * @description Esta funcion se usa para ir al catalogo de bicicletas sin filtros.
             */
            $rootScope.irAccesorios = function () {
                $state.go('adv', {adv: true, tipo: 'accesorios', filtros: {marcas: [], categorias: [], colores: [], precioMin: 0.0, precioMax: 999999999.9, calificacionMin: 0.0}}, {reload: true});
            };

            /**
             * @ngdoc function
             * @name hasPermissions
             * @methodOf mainApp.module:mainApp
             * @description Esta función define si el usuario tiene permisos
             * para acceder a la aplicación.
             * @returns {Boolean} Verdadero si el usuario tiene permisos.
             */
            $rootScope.hasPermissions = function () {
                if (($rootScope.isAuthenticated) && (sessionStorage.getItem("rol") === "Administrador")) {
                    return true;
                } else {
                    return false;
                }
            };
            $rootScope.isVendedor = function () {
                if (($rootScope.isAuthenticated) && (sessionStorage.getItem("rol") === "Vendedor")) {
                    return true;
                } else {
                    return false;
                }
            };
            $rootScope.logOut = function ()
            {
                sessionStorage.token = null;
                sessionStorage.setItem("username", null);
                sessionStorage.setItem("rol", null);
                sessionStorage.setItem("id", null);
                sessionStorage.setItem("name", null);
                $state.go('principal', {pagina: 1}, {reload: true});
            };
            $rootScope.showError = function (response) {
                if (response.status !== 200)
                {
                    var stat = response.status;
                    var msj = response.data;
                    if (response.status === 412)
                    {
                        $state.go('error', {mensaje: msj, status: "ERROR"});
                    } else
                    {
                        $state.go('error', {mensaje: response.statusText, status: "ERROR - " + stat});
                    }
                }
            };
        }]);
})(window.angular);