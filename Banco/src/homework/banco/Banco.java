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
package homework.banco;

import homework.world.Cuenta;
import homework.world.CuentaCorriente;
import homework.world.CuentaDeAhorros;

import java.util.ArrayList;
import java.util.List;


public class Banco {

    private String nombre;


    /**
     * Crea un nuevo objeto de la clase banco
     */
    public Banco(String nombre, List<Cuenta> cuentas) {
    }



    /**
     * Obtiene el nombre del banco
     */
    public String darNombre() {
        return "";
    }

    /**
     * Retorna true si existe una cuenta en la lista
     * con el número recibido como parámetro. False
     * en caso contrario.
     */
    public boolean existeCuenta(String numero) {
        return false;
    }

    /**
     * Retorna la cuenta que tiene el número que
     * se pasa como parámetro. Retorna null si no
     * encontró la cuenta con el número dado.
     */
    public Cuenta darCuenta(String numero) {
        return null;
    }

    /**
     * Si no existe una cuenta con el número de la
     * cuenta pasada como parámetro, agrega una cuenta al final de la lista
     * de cuentas del banco y retorna true; si la cuenta
     * ya existe, no se agrega y retorne false.
     */
    public boolean abrirCuenta(Cuenta cuenta) {
        return false;
    }

    /**
     * Obtiene la posición en la que se encuentra
     * la cuenta que tiene el número que se pasa
     * como parámetro. Si no existe la cuenta con
     * ese número, retorne -1.
     */
    public int posicionCuenta(String numero) {
        return -1;
    }

    /**
     * Elimina la cuenta que tiene el número
     * que se recibe como parámetro. Retorna
     * true si se pudo eliminar la cuenta.
     * False si la cuenta no existe.
     */
    public boolean cerrarCuenta(String numCuenta) {
        return false;
    }

    /**
     * Retorna el porcentaje de cuentas del
     * banco que pertenecen al tipo pasado
     * como parámetro. Si tipo es igual a
     * "CORRIENTE", retorna el porcentaje
     * de cuentas corrientes en el banco;
     * Si tipo es "AHORROS", retorna el
     * porcentaje de cuentas de ahorros en
     * el banco. El porcentaje debe estar
     * entre 0.0 y 100.0
     */
    public double porcentajeCuentasTipo(String tipo) {
        return 0.0;
    }

    /**
     * Deposita un valor dado en la cuenta que tiene
     * el número que se recibe como parámetro.
     *
     * Algoritmo:
     * 1) Dejar en una variable la cuenta que
     *    tiene el número pasado como parámetro.
     * 2) Si encontró la cuenta y el valor es positivo
     *    deposite el valor en la cuenta (use el método
     *    depositar de la cuenta). Retorne true
     * 3) En cualquier otro caso, retorne false;
     */
    public boolean depositarCuenta(String numero, double valor) {
        return false;
    }

    /**
     * Deposita los intereses a la cuenta de ahorros
     * que tiene el número de cuenta dado como parametro.
     *
     * Algoritmo
     * 1) Busque la cuenta que tiene el número recibido
     *    como parámetro.
     * 2) Si no encontró la cuenta o si la cuenta encontrada
     *    no es una cuenta de ahorros, retorne falso
     * 3) En cualquier otro caso, convierta la cuenta encontrada
     *    a la clase CuentaDeAhorros y use el método de depositar
     *    intereses; no olvide retornar true.
     */
    public boolean depositarInteresesCuenta(String numero) {
        return false;
    }

    /**
     * Retira el valor de la cuenta que tiene el número dado.
     *
     * OJO: debe retornar false si se da los siguientes casos
     * El valor es negativo o
     * No existe la cuenta con el número dado o
     * La cuenta es de ahorros y el valor es superior al saldo
     *
     * En cualquier otro caso, retire el valor de la cuenta y
     * retorne true. (USE EL MÉTODO DE LA CLASE CUENTA QUE HACE RETIRO).
     */
    public boolean retirarCuenta(String numero, double valor) {
        return false;
    }

    /**
     * Transfiere un valor de la cuenta de origen
     * a la cuenta de destino.
     *
     * Debe retornarse false si se da alguna de las
     * siguientes condiciones:
     * - el valor es negativo o cero
     * - la cuenta de origen no existe
     * - la cuenta de destino no existe
     * - la cuenta de origen y la de destino son la misma
     * - la cuenta de origen es una cuenta de ahorros y el
     *   valor es superior al saldo de la cuenta
     *
     * En cualquier otro caso, retire el valor de la
     * cuenta de origen y deposite ese valor en la
     * cuenta de destino; retorne true al final
     * USE LOS MÉTODOS DE LA CLASE CUENTA.
     */
    public boolean transferirEntreCuentas(String origen, String destino, double valor) {
        return false;
    }

    /**
     * Retornar el saldo de la cuenta que tiene
     * el número dado.
     * Si la cuenta no existe, retorne -1.0
     * INTENTE USAR ALGÚN MÉTODO
     * ESCRITO ANTERIORMENTE.
     */
    public double darSaldoCuenta(String numero) {
        return 0.0;
    }


    /**
     * Retorne true si todos las cuentas corrientes
     * no tienen saldo en rojo. False en caso contrario.
     */
    public boolean todasCuentasCorrienteNoTienenSaldoRojo() {
        return false;
    }

    /**
     * Retorna la lista de los números de las
     * cuentas que tienen un saldo superior al
     * saldo de la cuenta que tiene el número
     * que se recibe como parámetro. Retorne null
     * si no existe una cuenta con el número
     * pasado como parámetro.
     */
    public List<String> saldosSuperior(String numero) {
        return null;
    }

    /**
     * Retorne el número de la cuenta que pertenece
     * al propietario que se pasa como parámetro
     * y que no tenga saldo en rojo y que tenga el saldo más alto
     */
    public String cuentaMayorSaldo(String propietario) {
        return "";
    }

    /**
     * Halla el promedio de los saldos de las cuentas de ahorro
     * que pertenecen a la sucursal que se pasa como parámetro y
     * tienen un saldo por encima de cero.
     * Si la cantidad de cuentas de la sucursal es cero, retorne
     * cero.
     */
    public double promedioCuentasAhorroSucursal(String sucursal) {
        return 0.0;
    }

    /**
     * Hay 4 sucursales del banco en la ciudad: Bosa, Centro, Chapinero y Suba.
     * Retorne el nombre de la sucursal que tenga el promedio de saldos más
     * alto de aquellas cuentas de ahorros con saldo mayor a cero.
     * USE EL MÉTODO ANTERIOR.
     * ACUÉRDESE COMO ENCONTRAR EL MAYOR ENTRE 4, YA LO HIZO UNA VEZ.
     * NO HAY FOR, NO HAY LISTAS, NO HAY ARREGLOS. A MANO!!
     */
    public String sucursalMayorPromedio() {
        return "";
    }


}
