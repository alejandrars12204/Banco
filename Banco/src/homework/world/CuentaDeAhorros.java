
package homework.world;

/**
 * Representa la información de una cuenta
 * de ahorros. Siempre saldo positivo o cero.
 */
public class CuentaDeAhorros extends Cuenta {

    private double interes;


    /**
     * Crea un nuevo objeto de la clase cuenta de ahorros.
     * OJO: No olvide llamar al constructor de la clase padre
     * @param numero el número de la cuenta
     * @param propietario el nombre del propietario
     * @param sucursal la sucursal del banco
     * @param interes la tasa de interés
     */
    public CuentaDeAhorros(String numero, String propietario, String sucursal, double interes) {
        super(numero, propietario, sucursal);
        this.interes = interes;
    }

    /**
     * Obtener la tasa de interés de esta cuenta de ahorros
     */
    public double darTasaInteres() {
        return this.interes;
    }

    /**
     * Cambia la tasa de interés de esta cuenta.
     * El cambio solo puede hacerse si está entre
     * 0.0 y 100.0
     */
    public void cambiarTasaInteres(double nuevoInteres) {
        if (nuevoInteres >= 0 && nuevoInteres <= 100){
            interes = nuevoInteres;
        }
    }

    /**
     * El método de retirar para las cuentas de ahorros
     * es un poco diferente. Si el valor a retirar está
     * entre cero y el saldo de la cuenta, se resta el
     * valor del saldo de la cuenta.
     */

    @Override
    public void retirar(double valor) {
        if (valor >= 0 && valor <= saldo){
            saldo -= valor;
        }
    }

    /**
     * Deposita los intereses en la cuenta de ahorros.
     * Para depositar los intereses súmele al saldo
     * la multiplicación del saldo por los intereses
     * dividido por 100. USE LOS ATRIBUTOS, NO USE
     * GETTERS
     */
    public void depositarIntereses() {

        saldo += ((saldo * interes)/100);
    }
}
