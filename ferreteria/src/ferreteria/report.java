/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ferreteria;

/**
 *
 * @author alberto
 */
public class report {
  private String producto;
    private String cantidad;
    private String precio;
    private String subtotal;
    private String descuento;
    private String total;

   

    
    public report(String producto, String cantidad, String precio, String subtotal, String descuento, String total) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.precio = precio;
        this.subtotal = subtotal;
        this.descuento = descuento;
        this.total = total;
    }

   

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    public String getDescuento() {
        return descuento;
    }

    public void setDescuento(String descuento) {
        this.descuento = descuento;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
   
    
}
