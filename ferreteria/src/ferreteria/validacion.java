/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ferreteria;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author alberto
 */
public class validacion {
    static String strSQL="";
static String ced="";
private static final int NUM_PROVINCIAS = 24;
public static boolean vacio=true;

public  static boolean ValidaCedula(String cedula) {
    if (!((cedula.length() == 10) && cedula.matches("^[0-9]{10}$"))) {
          return false;
       }
        int prov = Integer.parseInt(cedula.substring(0, 2));
        if (!((prov > 0) && (prov <= NUM_PROVINCIAS))) {
            return false;
        }
        int[] d = new int[10];
        for (int i = 0; i < d.length; i++) {
            d[i] = Integer.parseInt(cedula.charAt(i) + "");
        }
        int imp = 0;
        int par = 0;
        for (int i = 0; i < d.length; i += 2) {
            d[i] = ((d[i] * 2) > 9) ? ((d[i] * 2) - 9) : (d[i] * 2);
            imp += d[i];
        }
        for (int i = 1; i < (d.length - 1); i += 2) {
            par += d[i];
        }
        int suma = imp + par;
        int d10 = Integer.parseInt(String.valueOf(suma + 10).substring(0, 1) +
        "0") - suma;
        d10 = (d10 == 10) ? 0 : d10;
        return d10 == d[9];
  }

    public static boolean cedula(JLabel lb_cedula,JTextField txt_cedula){
                   boolean cedula=true;
                   boolean b = ValidaCedula(txt_cedula.getText());
                   if(!b ==false || txt_cedula.getText().equals(""))
                   {
                       lb_cedula.setText("");
                       txt_cedula.setBackground(Color.white);
                       cedula=true;
                   }
                   else
                   {
                       lb_cedula.setText("Cedula inválida");
                       txt_cedula.setBackground(Color.red);
                       txt_cedula.requestFocus();
                       cedula=false;
                   }
                   return cedula;
}
    public static void keyType(KeyEvent evt, char n, int op){
 switch (op){

     case 1: if(((n < '0') || (n >'9')) && ((n!= KeyEvent.VK_BACK_SPACE))){
             evt.consume();
             }
             break;
     case 2: if(((n < '0') ||(n > '9')) && (n != KeyEvent.VK_BACK_SPACE) &&( n!='.')) {
              evt.consume();
             }
              break;
     case 3: if(((n < 'a') ||(n > 'z')) && ((n < 'A') ||(n > 'Z')) && (n!=(char)KeyEvent.VK_SPACE)){
               evt.consume();
             }break;

    }
    }

public static boolean ValidaCorreo(JTextField txt_correo, JLabel lb_correo){
      boolean correo=true;
      String email = txt_correo.getText();
      Pattern p = Pattern.compile("^[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+\\.[a-zA-Z]{2,4}$");
      Matcher m = p.matcher(email);
      boolean c = m.matches();
      if(!c ==false || txt_correo.getText().equals(""))
      {
            lb_correo.setText("");
            //txt_correo.setBackground(Color.white);
            correo=true;
      }
      else
      {      
            lb_correo.setText("Formato inválido");
            txt_correo.setBackground(Color.red);
            correo=false;
      }
      return correo;
   }

public static Integer validaEdad(String fecha){

                Date fechaNac=null;
	        try {
	            fechaNac = new SimpleDateFormat("dd-MM-yyyy").parse(fecha);
	        } catch (Exception ex) {
	            System.out.println("Error:"+ex);
	        }
	        Calendar fechaNacimiento = Calendar.getInstance();
	        //Se crea un objeto con la fecha actual
	        Calendar fechaActual = Calendar.getInstance();
	        //Se asigna la fecha recibida a la fecha de nacimiento.
	        fechaNacimiento.setTime(fechaNac);
	        //Se restan la fecha actual y la fecha de nacimiento
	        int año = fechaActual.get(Calendar.YEAR)- fechaNacimiento.get(Calendar.YEAR);
	        int mes =fechaActual.get(Calendar.MONTH)- fechaNacimiento.get(Calendar.MONTH);
	        int dia = fechaActual.get(Calendar.DATE)- fechaNacimiento.get(Calendar.DATE);
	        //Se ajusta el año dependiendo el mes y el día
	        if(mes<0 || (mes==0 && dia<0)){
	            año--;
	        }
	        return año;
    }

}
