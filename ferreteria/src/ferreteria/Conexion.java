/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ferreteria;

import java.security.*;
import java.sql.*;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;




public class Conexion {
public String bd = "system";
public String login = "root";
public String password = "";
public String url = "jdbc:mysql://localhost/"+bd;


Connection conn = null; 
    
   public Conexion() { 
      try{                 
         Class.forName("com.mysql.jdbc.Driver");          
         conn = DriverManager.getConnection(url,login,password); 
         if (conn!=null){ 

          //  JOptionPane.showMessageDialog(null,"Conexión a base de datos "+bd+" exitosa, bienvenido"); 
         } 
      }catch(SQLException e){ 
         System.out.println(e); 
      }catch(ClassNotFoundException e){ 
         System.out.println(e); 
      } 
   } 
    
   //EL MD5 SE UTILIZA PARA GUARDAR ENCRIPTADO Y COMPARAR EL DATO ENCRIPTADO 
   //EL OTRO METODO ENCRIPTA Y DESENCRIPTA.. OJO
 public static String md5(String msg) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(msg.getBytes());
            byte byteData[] = md.digest();
            //convert the byte to hex format method 1
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            return  sb.toString();
        } catch (Exception ex) {
            return "";
        }
    }  
   public String encriptar(String texto,String clave)
    {
        int tamtext=texto.length();
        int tamclav=clave.length();
        int temp,p=0;
        String encriptado="";
    /* Se crea un array de enteros que contendran los numeros que
       corresponde a los caracteres en Ascii de los String Texto y la Clave */

        int textoAscii[]= new int[tamtext];
        int claveAscii[]= new int[tamclav];

    /* Se guardan los caracteres de cada String en
       numeros correspondientes al Ascii           */
        for(int i=0;i<tamtext;i++)
          textoAscii[i] = texto.charAt(i);

        for(int i=0;i<tamclav;i++)
          claveAscii[i] = clave.charAt(i);

        //Se procede al ENCRIPTADO
       for(int i=0;i<tamtext;i++){
         p++;

         if(p >= tamclav)
          p=0;

         temp =textoAscii[i]+claveAscii[p];

         if (temp > 255)
         temp=temp-255;

         encriptado = encriptado + (char)temp;
        }

     return encriptado;
    }

    public String desencriptar(String texto,String clave){
        int tamtext=texto.length();
        int tamclav=clave.length();
        int temp,p=0;
        String desencriptado="";
    /* Se crea un array de enteros que contendran los numeros que
       corresponde a los caracteres en Ascii de los String Texto y la Clave */

        int textoAscii[]= new int[tamtext];
        int claveAscii[]= new int[tamclav];

    /* Se guardan los caracteres de cada String en
       numeros correspondientes al Ascii           */
        for(int i=0;i<tamtext;i++)
          textoAscii[i] = texto.charAt(i);

        for(int i=0;i<tamclav;i++)
          claveAscii[i] = clave.charAt(i);

        //Se procede al DESENCRIPTADO
        for(int i=0;i<tamtext;i++)
        {
         p++;

          if(p>=tamclav)
          p=0;

         temp=textoAscii[i]-claveAscii[p];

         if (temp < 0)
         temp=temp+256;

         desencriptado=desencriptado + (char)temp;
        }
     return desencriptado;
    }


   
   
   public Connection getConnection(){ 
      return conn; 
      
          
   } 
 
   public void desconectar(){ 
      conn = null; 
   }
   

   
//___________________________________________________________________________________ Soy una barra separadora :)
/* METODO PARA REALIZAR UNA CONSULTA A LA BASE DE DATOS
 * INPUT:  
 *      table => nombre de la tabla donde se realizara la consulta, puede utilizarse tambien INNER JOIN
 *      fields => String con los nombres de los campos a devolver Ej.: campo1,campo2campo_n
 *      where => condicion para la consulta
 * OUTPUT: un object[][] con los datos resultantes, sino retorna NULL
 */
   /**
    * 
    * @param table
    * @param fields
    * @param where
    * @return
    */
   public Object [][] select(String table, String fields, String where){
      int registros = 0;      
      String colname[] = fields.split(",");

      //Consultas SQL
      String q ="SELECT " + fields + " FROM " + table;
      String q2 = "SELECT count(*) as total FROM " + table;
      if(where!=null)
      {
          q+= " WHERE " + where;
          q2+= " WHERE " + where;
      }
       try{
         PreparedStatement pstm = conn.prepareStatement(q2);
         ResultSet res = pstm.executeQuery();
         res.next();
         registros = res.getInt("total");
         res.close();
      }catch(SQLException e){
         System.out.println(e);
      }
    
    //se crea una matriz con tantas filas y columnas que necesite
    Object[][] data = new String[registros][fields.split(",").length];
    //realizamos la consulta sql y llenamos los datos en la matriz "Object"
      try{
         PreparedStatement pstm = conn.prepareStatement(q);
         ResultSet res = pstm.executeQuery();
         int i = 0;
         while(res.next()){
            for(int j=0; j<=fields.split(",").length-1;j++){
                data[i][j] = res.getString( colname[j].trim() );
            }
            i++;         }
         res.close();
          }catch(SQLException e){
         System.out.println(e);
    }
    return data;
 }
   
   
   public boolean ingresar(String user, String contra)
    {    //user=encriptar(user,"123"); 
         //contra=encriptar(contra,"123");  
        Object[][] res = this.select("LOGINES", " CEDULA , CONTRASEÑA ", " CEDULA='"+user+"' AND CONTRASEÑA='"+contra+"' ");
        if( res.length > 0)
        {   
            return true;
        }        
        else
            return false;
    }
   
   public void NuevaPersona(String name, String contra){
       try {   
           //name=encriptar(name,"123");
           //contra=encriptar(contra,"123");
            PreparedStatement pstm =this.getConnection().prepareStatement("insert into " + 
                    "tablausers(USUARIO,CONTRASENA) " +
                    " values(?,?)"); 
            pstm.setString(1, name);
            pstm.setString(2, contra);                      
            pstm.execute();
            pstm.close();
            name=desencriptar(name,"123");
            JOptionPane.showMessageDialog(null, "usuario"+" "+name+" "+"creado correctamente");
            
         }catch(SQLException e){
         System.out.println(e);
      }
   }

}
