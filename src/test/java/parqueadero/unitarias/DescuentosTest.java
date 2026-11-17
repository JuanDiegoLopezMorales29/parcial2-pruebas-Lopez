package parqueadero.unitarias;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import parqueadero.Descuentos;

class DescuentosTest {

    private final Descuentos descuentos = new Descuentos();

    @Test
    @DisplayName("Un cliente frecuente recibe diez por ciento de descuento")
    void aplicaDescuentoFrecuente() {
        assertEquals(2700, descuentos.aplicarDescuento(3000, "frecuente"));
    }

    @Test
    @DisplayName("Un cliente sin beneficio paga el valor completo")
    void conservaValorSinDescuento() {
        assertEquals(3000, descuentos.aplicarDescuento(3000, "ninguno"));
    }

    @Test
    @DisplayName("Un cliente de convenio recibe veinte por ciento de descuento")
    void aplicaDescuentoConvenio() {
        assertEquals(20000, descuentos.aplicarDescuento(25000, "convenio"));
    }
}