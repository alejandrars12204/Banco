/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad Ean (Bogotá - Colombia)
 * Facultad de Ingeniería
 * Licenciado bajo el esquema Academic Free License version 2.1
 * <p>
 * Project: Cuentas del Banco
 * Exercise: Clase Cuenta
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package homework.world;

/**
 * Las cuentas corrientes son cuentas que tienen el impuesto del 4x1000
 */
public class CuentaCorriente extends Cuenta {


    // TODO: Escriba aquí los atributos de la clase CuentaCorrienta
    private boolean tieneCuatroPorMil;



    /**
     * Crea un nuevo objeto de la clase CuentaCorriente
     */
    public CuentaCorriente(String numero, String propietario, String sucursal, boolean tieneCuatroPorMil) {
        super(numero, propietario, sucursal);
        this.tieneCuatroPorMil = tieneCuatroPorMil;
    }


    /**
     * Permite saber si esta cuenta tiene 4 por mil
     */
    public boolean tieneCuatroPorMil() {
        // TODO: Escribe tu código aquí
        return this.tieneCuatroPorMil;
    }

    /**
     * Cambia el 4x1000 para esta cuenta
     */
    public void cambiarSiTieneCuatroPorMil(boolean tieneCuatroPorMil) {
        this.tieneCuatroPorMil = tieneCuatroPorMil;
    }

    public boolean isTieneCuatroPorMil() {
        return tieneCuatroPorMil;
    }

    /**
     * En las cuentas corrientes, depositar debe tener en cuenta
     * si tiene cuatro por mil. Si no tiene cuatro por mil
     * el saldo se le suma el valor si este valor es positivo;
     * Si la cuenta tiene cuatro por mil, entonces
     * si el valor es positivo, se le suma este valor al saldo
     * y se le resta el valor * 4 dividido entre 1000. Ejemplo,
     * si el valor es $40_000, se le suma 40_000 - 40_000 * 4 / 1000
     * NO USE GETTERS!!!
     */

    public void depositar (double valor){
        if (tieneCuatroPorMil == false){
            if (valor > 0){
                saldo += valor;
            }
        } else {
            if (valor > 0){
                saldo += valor - (valor * 4 / 1000);
            }
        }
    }
}
