package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void virheellinenVarastonTilavuus() {
        assertEquals(0, (new Varasto(-5)).getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void varastonLuontiAlkuSaldolla() {
        Varasto varastoAlkusaldolla = new Varasto(10, 5);
        assertEquals(10, varastoAlkusaldolla.getTilavuus(), vertailuTarkkuus);
        assertEquals(5, varastoAlkusaldolla.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void varastonLuontiAlkuSaldollaNegatiivinenTilavuus() {
        Varasto varastoAlkusaldolla = new Varasto(-1, 5);
        assertEquals(0, varastoAlkusaldolla.getTilavuus(), vertailuTarkkuus);
        assertEquals(0, varastoAlkusaldolla.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void varastonLuontiAlkuSaldollaNegatiivinenSaldo() {
        Varasto varastoAlkusaldolla = new Varasto(1, -1);
        assertEquals(0, varastoAlkusaldolla.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void varastonAlkuSaldoYliTilavuuden() {
        Varasto varasto = new Varasto(5, 10);
        assertEquals(5, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysEiYlitaTilavuutta() {
        varasto.lisaaVarastoon(11);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void negatiivinenMaaraEiMuutaSaldoa() {
        varasto.lisaaVarastoon(-5);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void negatiivisenMaaranOttaminenPalauttaaOikeanMaaran() {
        assertEquals(0, varasto.otaVarastosta(-1), vertailuTarkkuus);
    }

    @Test
    public void ottaminenYliSaldonPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(5);
        assertEquals(5, varasto.otaVarastosta(6), vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void toStringToimii() {
        varasto.lisaaVarastoon(3);
        assertEquals("saldo = 3.0, vielä tilaa 7.0", varasto.toString());
    }
}