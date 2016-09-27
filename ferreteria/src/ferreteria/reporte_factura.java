/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ferreteria;

/**
 *
 * @author alberto
 */
public class reporte_factura {
    
    private String Articulo;
    private String Cantidad;

    public reporte_factura(String Articulo, String Cantidad) {
        this.Articulo = Articulo;
        this.Cantidad = Cantidad;
    }

    public String getArticulo() {
        return Articulo;
    }

    public void setArticulo(String Articulo) {
        this.Articulo = Articulo;
    }

    public String getCantidad() {
        return Cantidad;
    }

    public void setCantidad(String Cantidad) {
        this.Cantidad = Cantidad;
    }
   
    
        
}
