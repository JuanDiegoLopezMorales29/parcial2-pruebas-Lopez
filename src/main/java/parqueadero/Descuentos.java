package parqueadero;

public class Descuentos {

    public int aplicarDescuento(int valor, String tipoCliente) {
        if (valor < 0) {
            throw new IllegalArgumentException("El valor no puede ser negativo");
        }

        if (!"ninguno".equals(tipoCliente)
                && !"frecuente".equals(tipoCliente)
                && !"convenio".equals(tipoCliente)) {
            throw new IllegalArgumentException("Tipo de cliente no válido");
        }

        if ("ninguno".equals(tipoCliente)) {
            return valor;
        }

        if ("convenio".equals(tipoCliente)) {
            return (int) (valor * 0.80);
        }

        return (int) (valor * 0.90);
    }
}