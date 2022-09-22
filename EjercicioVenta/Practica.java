import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Clase Principal
 */
public class Practica {

    /**
     * Lista para guardar todos los productos ingresados
     */
    public static List<Producto> listaProducto = new ArrayList<>();

    /**
     * Lista para guardar los detalles de la factura
     */
    public static List<Factura> listaFactura;
    public static Scanner sc;
    public static String nomCliente;
    public static double total = 0;
    public static double totalIva = 0;

    /**
     * Llama a los demás métodos dependiendo del retorno del método mostrarOpciones().
     * Valida el descuento del 5% si el total es mayor o igual a 1000
     * @param args
     */
    public static void main(String[] args) {
        boolean estado = true;
        do {
            String caso = mostrarOpciones();
            switch (caso) {
                case "1": ingresarProductos();
                    break;
                case "2": listarProductos();
                    break;
                case "3":
                    if(!listaProducto.isEmpty()) {
                        generarFactura();
                        if (total >= 1000) {
                            totalIva = totalIva - (totalIva * 0.05);
                            System.out.println("======Usted tiene un descuento del 5%======");
                        }
                        mostrarFactura();
                    }else {System.out.println("No hay ningún producto registrado");}
                    break;
                case "4": mostrarDatosPersonales();
                    break;
                case "5": estado = false;
                    break;
                default: System.out.println("Ingrese una opción válida.");
                    break;
            }
        } while (estado);
    }

    /**
     * Muestra las opciones que tiene el usuario
     * @return retorna la selección del usuario
     */
    public static String mostrarOpciones(){
        sc = new Scanner(System.in);
        System.out.println();
        System.out.println("Ingrese una opción:");
        System.out.println("1.Ingresar productos");
        System.out.println("2.Ver lista de productos");
        System.out.println("3.Facturar producto");
        System.out.println("4.Datos personales");
        System.out.println("5.Salir");
        String caso = sc.nextLine();
        return caso;
    }

    /**
     * Permite ingresar productos a una lista(campos cantidad y precio validados)
     */
    public static void ingresarProductos(){
        System.out.println("Cuantos productos desea ingresar?");
        String entrada = sc.nextLine();
        if(esEntero(entrada)) {
            int canPro = Integer.parseInt(entrada);
            for (int i = 1; i <= canPro; i++) {
                sc = new Scanner(System.in);
                Producto producto = new Producto();
                System.out.println("Producto " + i);
                producto.setCodigo(listaProducto.size() + 1);
                System.out.print("Nombre:");
                producto.setNombre(sc.nextLine());
                for(int j = 0; j < 1; j++) {
                    System.out.print("Cantidad:");
                    String entradaCantidad = sc.nextLine();
                    if(!esEntero(entradaCantidad)){
                        System.out.println("Ingrese un dato válido");
                        j--;
                    }else {
                        producto.setCantidad(Integer.parseInt(entradaCantidad));
                    }
                }
                for(int j = 0; j < 1; j++) {
                    System.out.print("Precio de costo:");
                    String entradaCosto = sc.nextLine();
                    if(!esDecimal(entradaCosto) && !esEntero(entradaCosto)){
                        System.out.println("Ingrese un dato válido");
                        j--;
                    }else {
                        producto.setPrecioCosto(Double.parseDouble(entradaCosto));
                    }
                }
                listaProducto.add(producto);
            }
        }else {
            System.out.println("Ingrese una opción válida");
        }
    }

    /**
     * Devuelve una lista de todos los productos ingresados
     */
    public static void listarProductos(){
        System.out.println();
        System.out.println("======LISTADO DE PRODUCTOS======");
        System.out.println();
        for (Producto p : listaProducto) {

            System.out.println("*****************************");
            System.out.println("Código: " + p.getCodigo());
            System.out.println("Producto: " + p.getNombre());
            System.out.println("Cantidad: " + p.getCantidad());
            System.out.println("Precio Costo: " + p.getPrecioCosto());
            System.out.println("Precio Venta: " + p.getPrecioVenta());
        }

        System.out.println("*****************************");
        System.out.println("================================");
    }

    /**
     * Le pide al usuario el nombre del cliente y el código del producto que desea comprar.
     * Genera la factura incluyendo el total y el totalIva
     */
    public static void generarFactura(){
            boolean estado = true;
            listaFactura = new ArrayList<Factura>();
            sc = new Scanner(System.in);
            System.out.print("Ingrese el nombre del cliente:");
            nomCliente = sc.nextLine();
            do{
                Factura factura = new Factura();
                System.out.print("Ingrese el código del producto: ");
                String entrada = sc.nextLine();
                if(esEntero(entrada)) {
                    int cod = Integer.parseInt(entrada);
                    if (cod > 0 && cod <= listaProducto.size()) {
                        factura.setNombreProducto(listaProducto.get(cod - 1).getNombre());
                        for(int i = 0; i < 1; i++){
                            System.out.print("Ingrese la cantidad a comprar: ");
                            String entradaCantidad = sc.nextLine();
                            if(!esEntero(entradaCantidad)){
                                System.out.println("Ingrese una cantidad válida");
                                i--;
                            }else {
                                factura.setCantidad(Integer.parseInt(entradaCantidad));
                            }
                        }
                        factura.setPrecioProducto(listaProducto.get(cod - 1).getPrecioVenta());
                        factura.setSubtotal(factura.getPrecioProducto(), factura.getCantidad());
                        total += factura.getSubtotal();

                        listaFactura.add(factura);

                        for(int i = 0; i < 1; i++) {
                            System.out.println("1.Ingresar otro producto");
                            System.out.println("2.Finalizar");

                            String opcion = sc.nextLine();

                            if (opcion.equals("2")) {
                                estado = false;
                            } else if (!opcion.equals("1")) {
                                System.out.println("Ingrese una opción válida");
                                i--;
                            }
                        }
                    } else {
                        System.out.println("Ese producto no existe");
                    }
                }else {
                    System.out.println("Ingrese un código válido");
                }

            }while(estado);
            totalIva = (total * 0.18) + total;
    }

    /**
     * Muestra todos los detalles de la factura
     */
    public static void mostrarFactura(){
        System.out.println("Cliente: " + nomCliente);
        for(Factura f: listaFactura){
            System.out.println();
            System.out.println("     Producto: " + f.getNombreProducto());
            System.out.println("     Precio Unitario: " + f.getPrecioProducto());
            System.out.println("     Cantidad comprada: " + f.getCantidad());
            System.out.println("     Subtotal: " + Math.round(f.getSubtotal() * 100.0) / 100.0);
        }
        System.out.println();
        System.out.println("Total a pagar: " + Math.round(totalIva * 100.0) / 100.0);
        String ope;
        do{
            sc = new Scanner(System.in);
            System.out.println("Salir(0)");
            ope = sc.nextLine();
            if(ope.equals("0")){break;}
        }while(!ope.equals("0"));
    }

    /**
     * Muestra datos del autor
     */
    public static void mostrarDatosPersonales(){
        System.out.println("Nombre: Eduardo Rojas");
        System.out.println("Carrera: Computación e Informática");
        System.out.println("Ciclo: 5to");
        System.out.println("Sexo: Masculino");
        System.out.println("Para salir escriba (salir)");
        String op;
        do{
            sc = new Scanner(System.in);
            op = sc.nextLine();
            if(op.equals("salir")){
                break;
            }else{
                System.out.println("Para salir escriba (salir)");
            }
        }while(!op.equals("salir"));
    }

    /**
     * Verifica si un valor es entero
     * @param texto Se ingresa el valor como texto
     * @return si es entero retorna true, de lo contrario retorna false
     */
    public static boolean esEntero(String texto){
        try {
            Integer.parseInt(texto);
            return true;
        } catch (NumberFormatException excepcion) {
            return false;
        }
    }

    /**
     * Verifica si un valor es decimal
     * @param texto Se ingresa el valor como texto
     * @return si es decimal retorna true, de lo contrario retorna false
     */
    public static boolean esDecimal(String texto) {
        try {
            Double.parseDouble(texto);
            return true;
        } catch(NumberFormatException excepcion) {
            return false;
        }
    }


}
