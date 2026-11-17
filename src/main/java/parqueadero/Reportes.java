package parqueadero;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Reportes {

    private final CalculadoraTarifas calculadora = new CalculadoraTarifas();

    public Map<String, Integer> resumen(List<Registro> registros) {
        Map<String, Integer> resultado = new HashMap<>();
        resultado.put("carro", 0);
        resultado.put("moto", 0);
        resultado.put("total_recaudado", 0);

        for (Registro registro : registros) {
            String tipoVehiculo = registro.tipo();

            resultado.put(
                    tipoVehiculo,
                    resultado.get(tipoVehiculo) + 1);

            int valor = calculadora.calcularTarifa(
                    registro.minutos(), tipoVehiculo);

            resultado.put(
                    "total_recaudado",
                    resultado.get("total_recaudado") + valor);
        }

        return resultado;
    }
}