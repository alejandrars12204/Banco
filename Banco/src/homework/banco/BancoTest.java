package homework.banco;

import homework.world.Cuenta;
import homework.world.CuentaCorriente;
import homework.world.CuentaDeAhorros;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class BancoTest {
    private List<Cuenta> cuentas;
    private Banco banco;
    private static String ARCHIVO_CUENTAS = "./data/cuentas.csv";

    private Cuenta leerCuentaDesdeLinea(String linea){
        Cuenta c = null;

        try (Scanner sc = new Scanner(linea)){
            sc.useDelimiter(";");
            String numero = sc.next();
            String propietario = sc.next();
            String sucursal = sc.next();
            String tipo = sc.next();
            double interes = sc.nextDouble();
            String cuatropormil = sc.next();

            if (tipo.equals("true")) { // Corriente
                c = new CuentaCorriente(numero, propietario, sucursal, cuatropormil.equals("true"));
            }
            else if (tipo.equals("false")) {
                c = new CuentaDeAhorros(numero, propietario, sucursal, interes);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return c;
    }

    private String saldoMayor(String propietario) {
        String nc = null;
        double saldo = 0.0;
        Random random = new Random();

        for (Cuenta c : cuentas) {
            if (c.darPropietario().equals(propietario)) {
                int s = random.nextInt(200_000, 500_000);
                c.depositar((double) s);
                if (s > saldo) {
                    nc = c.darNumero();
                    saldo = s;
                }
            }
        }
        return nc;
    }

    private double promedioSucursal(String sucursal) {
        double suma = 0.0;
        double cont = 0.0;
        Random random = new Random();
        for (Cuenta c : cuentas) {
            if (c.darSucursal().equals(sucursal) && c instanceof CuentaDeAhorros) {
                int s = random.nextInt(200_000, 500_000);
                c.depositar((double) s);
                suma += s;
                cont += 1.0;
            }
        }
        return suma / cont;
    }

    @BeforeEach
    public void leerCuentas(){
        cuentas = new LinkedList<>();
        try (Scanner sc = new Scanner(new File(ARCHIVO_CUENTAS))){
            int i = 1;
            while (sc.hasNext()){
                if (i == 1) {
                    sc.nextLine();
                }
                else {
                    cuentas.add(leerCuentaDesdeLinea(sc.nextLine()));
                }
                i++;
            }
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        banco = new Banco("Banco del Parque", cuentas);
    }

    @Test
    public void existeCuenta() {
        assertFalse(banco.existeCuenta("F0094DCB-0380-8A57-7175-7DE255795301"));
        assertTrue(banco.existeCuenta("FA894DCB-0380-8A57-7175-7DE255795301"));
        assertFalse(banco.existeCuenta("1234567890"));
        assertTrue(banco.existeCuenta("4AA15079-7BD5-2CEC-270C-BC6AC50D6238"));

        System.out.println("Prueba superada 👍🏽");
    }

    @Test
    public void abrirCuenta() {
        Cuenta cuenta1 = new CuentaDeAhorros("123-456", "Pablo Marmol", "Piedradura", 10.0);

        assertFalse(banco.abrirCuenta(cuentas.get(20)));

        assertTrue(banco.abrirCuenta(cuenta1));
        assertEquals(51, cuentas.size());
        assertEquals(cuenta1.darNumero(), cuentas.getLast().darNumero());
        assertEquals(0.0, cuentas.getLast().darSaldo(), 1e-2);

        assertFalse(banco.abrirCuenta(cuenta1));
        assertEquals(51, cuentas.size());

        System.out.println("Prueba superada 👍🏽");
    }

    @Test
    public void posicionCuenta() {
        assertEquals(-1, banco.posicionCuenta("ABC123"));

        assertEquals(44, banco.posicionCuenta("D202803D-2D13-EE25-A898-1A89EEB8A83F"));
        assertEquals(49, banco.posicionCuenta("C8CCF0AA-22E4-9B64-62B8-DC1B3F27194C"));
        assertEquals(0, banco.posicionCuenta("E6B97AE5-7AE4-A4C8-3E2C-BC523353A282"));

        System.out.println("Prueba superada 👍🏽");
    }

    @Test
    public void cerrarCuenta() {
        assertFalse(banco.cerrarCuenta("ABC123"));
        assertEquals(50, cuentas.size());

        assertTrue(banco.cerrarCuenta("EE3824E1-1773-AC17-528E-16F192A49B58"));
        assertEquals(49, cuentas.size());
        assertEquals("AAD79B87-7B80-1ACB-B11B-208D3EA04DC9", cuentas.get(14).darNumero());
        assertEquals("D29766E5-2CB2-B167-3665-279C50D9BEB1", cuentas.get(15).darNumero());

        assertFalse(banco.cerrarCuenta("EE3824E1-1773-AC17-528E-16F192A49B58"));
        assertEquals(49, cuentas.size());

        assertTrue(banco.cerrarCuenta("C8CCF0AA-22E4-9B64-62B8-DC1B3F27194C"));
        assertEquals(48, cuentas.size());

        assertEquals("CB52D5D7-D74E-C6D3-953B-EAA279C946C0", cuentas.getLast().darNumero());
        assertFalse(banco.cerrarCuenta("C8CCF0AA-22E4-9B64-62B8-DC1B3F27194C"));
        assertEquals(48, cuentas.size());

        System.out.println("Prueba superada 👍🏽");
    }

    @Test
    public void porcentajeCuentasTipo() {
        assertEquals(56.0, banco.porcentajeCuentasTipo("CORRIENTE"), 1e-2);
        assertEquals(44.0, banco.porcentajeCuentasTipo("AHORROS"));
        assertEquals(0.0, banco.porcentajeCuentasTipo("BANCO"));

        System.out.println("Prueba superada 👍🏽");
    }

    @Test
    public void depositarCuenta() {
        assertFalse(banco.depositarCuenta("ABC123", 100_000));
        assertFalse(banco.depositarCuenta("E6D2F092-583B-AA48-D4F9-C2E2AEA54143", -100_000.0));
        assertTrue(banco.depositarCuenta("E6D2F092-583B-AA48-D4F9-C2E2AEA54143", 100_000));
        assertEquals(100_000.0, cuentas.get(40).darSaldo(), 1e-2);

        assertFalse(banco.depositarCuenta("E6D2F092-583B-AA48-D4F9-C2E2AEA54143", -10_000));
        assertTrue(banco.depositarCuenta("E6D2F092-583B-AA48-D4F9-C2E2AEA54143", 10_000));
        assertEquals(110_000.0, cuentas.get(40).darSaldo(), 1e-2);

        assertTrue(banco.depositarCuenta("94125BD8-0896-CE4A-7DDC-28DD49440534", 100_000.0));
        assertEquals(99_600.0, cuentas.get(39).darSaldo(), 1e-2);

        assertFalse(banco.depositarCuenta("94125BD8-0896-CE4A-7DDC-28DD49440534", -100_000.0));
        assertEquals(99_600.0, cuentas.get(39).darSaldo(), 1e-2);

        assertTrue(banco.depositarCuenta("7B83509C-1985-28B5-C907-097AC9EF96A0", 20_000.0));
        assertEquals(20_000.0, cuentas.get(32).darSaldo(), 1e-2);

        System.out.println("Prueba superada 👍🏽");

    }

    @Test
    public void depositarInteresesCuenta() {
        assertFalse(banco.depositarInteresesCuenta("ABC123"));

        cuentas.get(31).depositar(200_000.0);
        assertTrue(banco.depositarInteresesCuenta("4EABF556-5AD1-43C4-17B5-97228777BF8A"));
        assertEquals(200_000.0, cuentas.get(31).darSaldo(), 1e-2);

        cuentas.get(29).depositar(1_000_000.0);
        assertFalse(banco.depositarInteresesCuenta("AD486CD7-4923-10E8-8D4F-5573D52213DB"));
        assertEquals(1_000_000.0, cuentas.get(29).darSaldo(), 1e-2);

        cuentas.get(28).depositar(1_000_000.0);
        assertTrue(banco.depositarInteresesCuenta("EB25F6D1-3B13-D356-7BFB-B633678D898F"));
        assertEquals(1_200_000.0, cuentas.get(28).darSaldo(), 1e-2);

        System.out.println("Prueba superada 👍🏽");
    }

    @Test
    void retirarCuenta() {
        assertFalse(banco.retirarCuenta("ABC123", 100_000));
        assertFalse(banco.retirarCuenta("2D11600D-B62E-3B2C-66AB-9238D26EE779", -10_000.0));

        assertTrue(banco.retirarCuenta("442E1CAD-2658-7614-A038-D8C8641DB7A6", 50_000.0));
        assertEquals(-50_000.0, cuentas.get(18).darSaldo(), 1e-2);

        assertFalse(banco.retirarCuenta("C48C73FF-B163-8728-E696-872451CA1741", 50_000.0));
        assertEquals(0.0, cuentas.get(19).darSaldo(), 1e-2);
        cuentas.get(19).depositar(1_000_000.0);
        assertTrue(banco.retirarCuenta("C48C73FF-B163-8728-E696-872451CA1741", 50_000.0));
        assertEquals(950_000.0, cuentas.get(19).darSaldo(), 1e-2);

        System.out.println("Prueba superada 👍🏽");
    }

    @Test
    void transferirEntreCuentas() {
        assertFalse(banco.transferirEntreCuentas("ABC123", "ABC123", 1_000_000.0));
        assertFalse(banco.transferirEntreCuentas("ABC123", "CDE456", 1_000_000.0));
        assertFalse(banco.transferirEntreCuentas("EC5A0596-B5D3-EECC-B773-88F52E8E1E8A", "CDE456", 1_000_000.0));
        assertFalse(banco.transferirEntreCuentas("EC5A0596-B5D3-EECC-B773-88F52E8E1E8A", "CDE456", -1_000_000.0));
        assertFalse(banco.transferirEntreCuentas("19C8A683-4C39-C7B3-D11C-7876425855A5", "EC5A0596-B5D3-EECC-B773-88F52E8E1E8A", -1_000.0));
        assertFalse(banco.transferirEntreCuentas("C5D727C5-BDDD-93DC-2988-DC0247402752", "C5D727C5-BDDD-93DC-2988-DC0247402752", 1_000.0));
        assertFalse(banco.transferirEntreCuentas("C5D727C5-BDDD-93DC-2988-DC0247402752", "C5D727C5-BDDD-93DC-2988-DC0247402752", 0.0));

        cuentas.get(10).depositar(1_000_000.0);
        assertEquals(996_000.0, cuentas.get(10).darSaldo(), 1e-2);
        assertEquals(0.0, cuentas.get(0).darSaldo(), 1e-2);
        assertTrue(banco.transferirEntreCuentas("C5D727C5-BDDD-93DC-2988-DC0247402752", "E6B97AE5-7AE4-A4C8-3E2C-BC523353A282", 200_000.0));
        assertEquals(796_000.00, cuentas.get(10).darSaldo(), 1e-2);
        assertEquals(200_000.0, cuentas.get(0).darSaldo(), 1e-2);

        assertTrue(banco.transferirEntreCuentas("E6B97AE5-7AE4-A4C8-3E2C-BC523353A282", "1714DADC-096D-7D76-E559-D1682E67B995", 100_000.0));
        assertEquals(100_000.0, cuentas.get(0).darSaldo(), 1e-2);
        assertEquals(99_600.0, cuentas.get(13).darSaldo(), 1e-2);

        assertFalse(banco.transferirEntreCuentas("E6B97AE5-7AE4-A4C8-3E2C-BC523353A282", "AAD79B87-7B80-1ACB-B11B-208D3EA04DC9", 250_000.0));
        assertEquals(100_000.0, cuentas.get(0).darSaldo(), 1e-2);
        assertEquals(0.0, cuentas.get(14).darSaldo(), 1e-2);

        assertTrue(banco.transferirEntreCuentas("E6B97AE5-7AE4-A4C8-3E2C-BC523353A282", "AAD79B87-7B80-1ACB-B11B-208D3EA04DC9", 50_000.0));
        assertEquals(50_000.0, cuentas.get(0).darSaldo(), 1e-2);
        assertEquals(50_000.0, cuentas.get(14).darSaldo(), 1e-2);

        assertTrue(banco.transferirEntreCuentas("AAD79B87-7B80-1ACB-B11B-208D3EA04DC9", "1714DADC-096D-7D76-E559-D1682E67B995", 200_000));
        assertEquals(-150_000.0, cuentas.get(14).darSaldo(), 1e-2);
        assertEquals(298_800.0, cuentas.get(13).darSaldo(), 1e-2);

        System.out.println("Prueba superada 👍🏽");
    }

    @Test
    void darSaldoCuenta() {
        assertEquals(-1.0, banco.darSaldoCuenta("ABC123"));
        assertEquals(0.0, banco.darSaldoCuenta("D29766E5-2CB2-B167-3665-279C50D9BEB1"));

        cuentas.get(16).depositar(1_000_000.0);
        assertEquals(1_000_000.0, banco.darSaldoCuenta("D29766E5-2CB2-B167-3665-279C50D9BEB1"), 1e-2);

        cuentas.get(15).depositar(10_000.0);
        assertEquals(9_960.0, banco.darSaldoCuenta("EE3824E1-1773-AC17-528E-16F192A49B58"), 1e-2);

        cuentas.get(15).retirar(80_000.0);
        assertEquals(-70_040, banco.darSaldoCuenta("EE3824E1-1773-AC17-528E-16F192A49B58"));

        System.out.println("Prueba superada 👍🏽");
    }

    @Test
    void todasCuentasCorrienteNoTienenSaldoRojo() {
        assertTrue(banco.todasCuentasCorrienteNoTienenSaldoRojo());

        cuentas.get(15).depositar(1_000_000.0);
        assertTrue(banco.todasCuentasCorrienteNoTienenSaldoRojo());

        cuentas.get(37).retirar(80_000.0);
        assertFalse(banco.todasCuentasCorrienteNoTienenSaldoRojo());

        System.out.println("Prueba superada 👍🏽");
    }

    @Test
    void saldosSuperior() {
        Random random = new Random();

        cuentas.getLast().depositar(200_000.0);
        List<String> ctas = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            int n = random.nextInt(50);
            if (n == 49) {
                continue;
            }
            int s = random.nextInt(200_000, 500_000);
            cuentas.get(n).depositar((double) s);
            if (s > 200_000 && !ctas.contains(cuentas.get(n).darNumero())) {
                ctas.add(cuentas.get(n).darNumero());
            }
        }
        List<String> numCtas = banco.saldosSuperior("C8CCF0AA-22E4-9B64-62B8-DC1B3F27194C");
        assertEquals(ctas.size(), numCtas.size());
        for (String num : ctas) {
            assertTrue(numCtas.contains(num));
        }
        System.out.println("Prueba superada 👍🏽");
    }

    @Test
    void cuentaMayorSaldo() {
        String nc = saldoMayor("Pablo Marmol");
        assertEquals(nc, banco.cuentaMayorSaldo("Pablo Marmol"));

        nc = saldoMayor("Camila Torres");
        assertEquals(nc, banco.cuentaMayorSaldo("Camila Torres"));

        nc = saldoMayor("Sofia Llanos");
        assertEquals(nc, banco.cuentaMayorSaldo("Sofia Llanos"));

        System.out.println("Prueba superada 👍🏽");
    }

    @Test
    void promedioCuentas() {
        double prom = promedioSucursal("Centro");
        assertEquals(prom, banco.promedioCuentasAhorroSucursal("Centro"), 1e-2);

        prom = promedioSucursal("Suba");
        assertEquals(prom, banco.promedioCuentasAhorroSucursal("Suba"), 1e-2);

        prom = promedioSucursal("Bosa");
        assertEquals(prom, banco.promedioCuentasAhorroSucursal("Bosa"), 1e-2);

        System.out.println("Prueba superada 👍🏽");
    }

    @Test
    void sucursalMayorPromedio() {
        double[] proms = { promedioSucursal("Centro"), promedioSucursal("Suba"), promedioSucursal("Bosa"), promedioSucursal("Chapinero") };
        String[] sucs = { "Centro", "Suba", "Bosa", "Chapinero" };
        double p = proms[0];
        String s = sucs[0];
        for (int i = 1; i < proms.length; i++) {
            if (proms[i] > p) {
                p = proms[i];
                s = sucs[i];
            }
        }
        assertEquals(s, banco.sucursalMayorPromedio());
        System.out.println("Prueba superada 👍🏽");
    }


}
