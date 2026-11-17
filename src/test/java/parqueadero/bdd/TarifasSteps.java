package parqueadero.bdd;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;

import parqueadero.CalculadoraTarifas;
import parqueadero.Descuentos;

public class TarifasSteps {

    private final CalculadoraTarifas calculadora = new CalculadoraTarifas();
    private final Descuentos descuentos = new Descuentos();

    private String tipoVehiculo;
    private int valorTarifa;
    private int valorInicial;
    private String tipoCliente;
    private int valorFinal;

    @Dado("un vehículo de tipo {string}")
    public void unVehiculoDeTipo(String tipo) {
        tipoVehiculo = tipo;
    }

    @Cuando("permanece {int} minutos")
    public void permaneceMinutos(int minutos) {
        valorTarifa = calculadora.calcularTarifa(minutos, tipoVehiculo);
    }

    @Entonces("debe pagar {int} pesos")
    public void debePagarPesos(int esperado) {
        assertEquals(esperado, valorTarifa);
    }

    @Dado("un cobro de {int} pesos para un cliente {string}")
    public void unCobroParaUnCliente(int valor, String cliente) {
        valorInicial = valor;
        tipoCliente = cliente;
    }

    @Cuando("se aplica el descuento")
    public void seAplicaElDescuento() {
        valorFinal = descuentos.aplicarDescuento(valorInicial, tipoCliente);
    }

    @Entonces("el valor final es {int} pesos")
    public void elValorFinalEs(int esperado) {
        assertEquals(esperado, valorFinal);
    }
}