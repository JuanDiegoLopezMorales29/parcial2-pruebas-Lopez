package parqueadero;

public class Descuentos {

    public int aplicarDescuento(int valor, String tipoCliente) {
        if ("ninguno".equals(tipoCliente)) {
            return valor;
        }

        if ("convenio".equals(tipoCliente)) {
            return (int) (valor * 0.80);
        }

        return (int) (valor * 0.90);
    }
}