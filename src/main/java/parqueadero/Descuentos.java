package parqueadero;

public class Descuentos {

    public int aplicarDescuento(int valor, String tipoCliente) {
        if ("ninguno".equals(tipoCliente)) {
            return valor;
        }
        return (int) (valor * 0.90);
    }
}