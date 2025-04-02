package homework.world;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CuentaDeAhorrosTest {
    CuentaDeAhorros cuentaDeAhorros;

    @BeforeEach
    void setUp() {
        cuentaDeAhorros = new CuentaDeAhorros("123-456", "Pablo Marmol", "Piedradura", 10.0);
    }

    @Test
    void cambiarTasaInteres() {
        assertEquals(10.0, cuentaDeAhorros.darTasaInteres(), 1e-2);

        cuentaDeAhorros.cambiarTasaInteres(15.0);
        assertEquals(15.0, cuentaDeAhorros.darTasaInteres(), 1e-2);

        System.out.println("Prueba superada 👍🏽");
    }

    @Test
    void retirar() {
        assertEquals(0.0, cuentaDeAhorros.darSaldo(), 1e-2);
        cuentaDeAhorros.depositar(-100_000.0);
        assertEquals(0.0, cuentaDeAhorros.darSaldo(), 1e-2);

        cuentaDeAhorros.depositar(100_000.0);
        assertEquals(100_000.0, cuentaDeAhorros.darSaldo(), 1e-2);

        cuentaDeAhorros.retirar(30_000.0);
        assertEquals(70_000.0, cuentaDeAhorros.darSaldo(), 1e-2);

        cuentaDeAhorros.retirar(80_000.0);
        assertEquals(70_000.0, cuentaDeAhorros.darSaldo(), 1e-2);
        assertFalse(cuentaDeAhorros.enSaldoRojo());

        cuentaDeAhorros.retirar(-10_000.0);
        assertEquals(70_000.0, cuentaDeAhorros.darSaldo(), 1e-2);
        assertFalse(cuentaDeAhorros.enSaldoRojo());

        cuentaDeAhorros.retirar(20_000.0);
        assertEquals(50_000.0, cuentaDeAhorros.darSaldo(), 1e-2);

        System.out.println("Prueba superada 👍🏽");
    }

    @Test
    void depositarIntereses() {
        assertEquals(0.0, cuentaDeAhorros.darSaldo(), 1e-2);
        cuentaDeAhorros.depositar(-100_000.0);
        assertEquals(0.0, cuentaDeAhorros.darSaldo(), 1e-2);

        cuentaDeAhorros.depositar(100_000.0);
        assertEquals(100_000.0, cuentaDeAhorros.darSaldo(), 1e-2);

        cuentaDeAhorros.depositarIntereses();
        assertEquals(110_000.0, cuentaDeAhorros.darSaldo(), 1e-2);

        cuentaDeAhorros.cambiarTasaInteres(20.0);
        cuentaDeAhorros.depositarIntereses();
        assertEquals(132_000.0, cuentaDeAhorros.darSaldo(), 1e-2);

        System.out.println("Prueba superada 👍🏽");
    }
}