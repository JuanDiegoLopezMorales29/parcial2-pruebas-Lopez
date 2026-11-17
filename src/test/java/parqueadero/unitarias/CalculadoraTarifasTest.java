package parqueadero.unitarias;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import parqueadero.CalculadoraTarifas;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

/** Pruebas unitarias iniciales de calcularTarifa. Están incompletas a propósito. */
class CalculadoraTarifasTest {

    private final CalculadoraTarifas calculadora = new CalculadoraTarifas();

    @Test
    @DisplayName("Un carro con una hora y un minuto paga dos horas")
    void carroConUnaHoraYUnMinutoPagaDosHoras() {
        assertEquals(6000, calculadora.calcularTarifa(61, "carro"));
    }

    @Test
    @DisplayName("Una moto con media hora paga una hora")
    void motoConMediaHoraPagaUnaHora() {
        assertEquals(1500, calculadora.calcularTarifa(30, "moto"));
    }

    @Test
    @DisplayName("Rechaza un tipo de vehículo desconocido")
    void rechazaUnTipoDeVehiculoDesconocido() {
        assertThrows(IllegalArgumentException.class, () -> calculadora.calcularTarifa(30, "bicicleta"));
    }

    @ParameterizedTest
    @CsvSource({
            "0, carro, 0",
            "14, carro, 0",
            "15, carro, 0",
            "16, carro, 3000",
            "0, moto, 0",
            "14, moto, 0",
            "15, moto, 0",
            "16, moto, 1500"
    })
    @DisplayName("Respeta los minutos de gracia para carros y motos")
    void respetaLosMinutosDeGracia(
            int minutos, String tipoVehiculo, int esperado) {

        assertEquals(esperado, calculadora.calcularTarifa(minutos, tipoVehiculo));
    }

    @ParameterizedTest
    @CsvSource({
            "59, carro, 3000",
            "60, carro, 3000",
            "61, carro, 6000",
            "59, moto, 1500",
            "60, moto, 1500",
            "61, moto, 3000"
    })
    @DisplayName("Cobra cada hora o fracción")
    void cobraCadaHoraOFraccion(
            int minutos, String tipoVehiculo, int esperado) {

        assertEquals(esperado, calculadora.calcularTarifa(minutos, tipoVehiculo));
    }

    @ParameterizedTest
    @CsvSource({
            "479, carro, 24000",
            "480, carro, 24000",
            "481, carro, 25000",
            "540, carro, 25000",
            "541, carro, 25000",
            "1439, carro, 25000",
            "1440, carro, 25000",
            "420, moto, 10500",
            "421, moto, 12000",
            "479, moto, 12000",
            "480, moto, 12000",
            "481, moto, 12000",
            "1439, moto, 12000",
            "1440, moto, 12000"
    })
    @DisplayName("Respeta el tope diario y admite el máximo de minutos")
    void respetaElTopeDiario(
            int minutos, String tipoVehiculo, int esperado) {

        assertEquals(esperado, calculadora.calcularTarifa(minutos, tipoVehiculo));
    }

    @ParameterizedTest
    @CsvSource({
            "-1, carro",
            "1441, carro",
            "-1, moto",
            "1441, moto"
    })
    @DisplayName("Rechaza minutos fuera del rango permitido")
    void rechazaMinutosFueraDelRango(int minutos, String tipoVehiculo) {
        IllegalArgumentException error = assertThrows(
                IllegalArgumentException.class,
                () -> calculadora.calcularTarifa(minutos, tipoVehiculo));

        assertEquals("Los minutos deben estar entre 0 y 1440", error.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"bicicleta", "camion", ""})
    @DisplayName("Rechaza tipos de vehículo no permitidos")
    void rechazaTiposDeVehiculoNoPermitidos(String tipoVehiculo) {
        IllegalArgumentException error = assertThrows(
                IllegalArgumentException.class,
                () -> calculadora.calcularTarifa(30, tipoVehiculo));

        assertEquals("Tipo de vehículo no válido", error.getMessage());
    }

}