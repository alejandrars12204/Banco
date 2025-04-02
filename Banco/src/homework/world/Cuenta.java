
package homework.world;

/**
 * La clase mantiene la información básica de una cuenta bancaria
 */
public class Cuenta {

    protected String numero;
    protected String propietario;
    protected String sucursal;
    protected double saldo;

    /**
     * Crea un objeto de la clase cuenta. El saldo inicial es cero.
     */
    public Cuenta(String numero, String propietario, String sucursal) {
        this.numero = numero;
        this.propietario = propietario;
        this.sucursal = sucursal;
        double saldo = 0;
    }



    /**
     * Obtener el número de la cuenta
     */
    public String darNumero() {
        return this.numero;
    }

    /**
     * Obtener el nombre del propietario de la cuenta
     */
    public String darPropietario() {
        // TODO: Escriba aquí el código
        return this.propietario;
    }

    /**
     * Obtener la sucursal del banco donde está la cuenta
     */
    public String darSucursal() {
        return this.sucursal;
    }

    /**
     * Obtener el saldo actual de la cuenta
     */
    public double darSaldo() {
        return this.saldo;
    }

    /**
     * Determinar si la cuenta está en
     * saldo en rojo, es decir, el saldo
     * es negativo.
     */
    public boolean enSaldoRojo() {
        if(saldo < 0){
            return true;
        }
        return false;
    }

    /**
     * Si el valor es positivo, le suma ese valor recibido
     * como parámetro al saldo de la cuenta.
     * @param valor el valor a depositar en la cuenta
     */
    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
        }
    }

    /**
     * Si el valor recibido como parámetro es positivo,
     * le resta ese valor al saldo de la cuenta.
     */
    public void retirar(double valor) {
        if (valor > 0 ){
            saldo -= valor;
        }
    }


}
