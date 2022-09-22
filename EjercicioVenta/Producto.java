/**
 * Clase para referenciar a un Producto
 */
public class Producto {

    private int codigo;
    private String nombre;
    private double precioCosto;
    private int cantidad;
    private double precioVenta;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecioCosto() {
        return precioCosto;
    }

    /**
     * Ingresa el precio de costo del producto
     * Calcula automaticamente el precio de venta
     * @param precioCosto
     */
    public void setPrecioCosto(double precioCosto) {
        this.precioCosto = precioCosto;
        this.precioVenta = (precioCosto * 0.10) + precioCosto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

}
