package homework.world;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CuentaCorrienteTest {
    private CuentaCorriente cuenta;

    @BeforeEach
    void setUp() {
        cuenta = new CuentaCorriente("123-ABC1", "Jean Reano", "Chapinero", true);
    }


    @Test
    void cambiarSiTieneCuatroPorMil() {
        assertTrue(cuenta.tieneCuatroPorMil());
        cuenta.cambiarSiTieneCuatroPorMil(false);
        assertFalse(cuenta.tieneCuatroPorMil());

        cuenta.cambiarSiTieneCuatroPorMil(true);
        assertTrue(cuenta.tieneCuatroPorMil());

        System.out.println("Prueba superada 👍🏽");
    }

    @Test
    void depositar() {
        cuenta.depositar(-20_000.0);
        assertEquals(0.0, cuenta.darSaldo(), 1e-2);

        cuenta.depositar(200_000.0);
        assertEquals(199_200.0, cuenta.darSaldo(), 1e-2);

        cuenta.depositar(-300_000.0);
        assertEquals(199_200.0, cuenta.darSaldo(), 1e-2);

        cuenta.depositar(100_000.0);
        assertEquals(298_800.0, cuenta.darSaldo(), 1e-2);

        cuenta.cambiarSiTieneCuatroPorMil(false);
        assertFalse(cuenta.tieneCuatroPorMil());

        cuenta.depositar(50_000.0);
        assertEquals(348_800.0, cuenta.darSaldo(), 1e-2);

        cuenta.cambiarSiTieneCuatroPorMil(true);
        assertTrue(cuenta.tieneCuatroPorMil());

        cuenta.depositar(50_000.0);
        assertEquals(398_600.0, cuenta.darSaldo(), 1e-2);

        System.out.println("Prueba superada 👍🏽");
    }
}