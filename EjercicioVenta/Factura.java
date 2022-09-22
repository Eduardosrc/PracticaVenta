/**
 * Clase para referenciar a los detalles de una factura
 */
public class Factura {
    private int cantidad;
    private String nombreProducto;
    private double subtotal;
    private double precioProducto;

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public double getSubtotal() {
        return subtotal;
    }

    /**
     * Este método recibe el precio y la cantidad para calcular el subtotal
     * @param precio
     * @param cantidad
     */
    public void setSubtotal(double precio, int cantidad) {
        this.subtotal = precio * cantidad;
    }

    public double getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(double precioProducto) {
        this.precioProducto = precioProducto;
    }

}
