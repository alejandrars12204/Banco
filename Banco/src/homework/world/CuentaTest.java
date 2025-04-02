package homework.world;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CuentaTest {
    Cuenta cuenta;

    @BeforeEach
    void setUp() {
        cuenta = new Cuenta("123-456-7890", "Juan Perez", "Centro");
    }

    @Test
    void darNumero() {
        assertEquals("123-456-7890", cuenta.darNumero());
        assertEquals("Juan Perez", cuenta.darPropietario());
        assertEquals("Centro", cuenta.darSucursal());
        assertEquals(0.0, cuenta.darSaldo(), 1e-2);
        System.out.println("Prueba superada 👏🏽");
    }

    @Test
    void depositar() {
        cuenta.depositar(100_000.0);
        assertEquals(100_000.0, cuenta.darSaldo(), 1e-2);
        cuenta.depositar(200_000.0);
        assertEquals(300_000.0, cuenta.darSaldo(), 1e-2);
        cuenta.depositar(-500_000.0);
        assertEquals(300_000.0, cuenta.darSaldo(), 1e-2);
        System.out.println("Prueba superada 👏🏽");
    }

    @Test
    void retirar() {
        cuenta.retirar(100_000.0);
        assertEquals(-100_000.0, cuenta.darSaldo(), 1e-2);
        assertTrue(cuenta.enSaldoRojo());

        cuenta.depositar(150_000.0);
        assertEquals(50_000.0, cuenta.darSaldo(), 1e-2);
        assertFalse(cuenta.enSaldoRojo());

        cuenta.retirar(15_000.0);
        assertEquals(35_000.0, cuenta.darSaldo(), 1e-2);

        cuenta.retirar(50_000.0);
        assertEquals(-15_000.0, cuenta.darSaldo(), 1e-2);
        assertTrue(cuenta.enSaldoRojo());

        cuenta.retirar(35_000.0);
        assertEquals(-50_000.0, cuenta.darSaldo(), 1e-2);
        assertTrue(cuenta.enSaldoRojo());

        cuenta.retirar(-35_000.0);
        assertEquals(-50_000.0, cuenta.darSaldo(), 1e-2);
        assertTrue(cuenta.enSaldoRojo());

        System.out.println("Prueba superada 👏🏽");
    }

}