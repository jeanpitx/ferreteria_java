/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ferreteria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import ferreteria.interfazRealizaVenta;
import static ferreteria.interfazRealizaVenta.tabla;
import java.awt.event.KeyEvent;
//import com.sun.glass.events.KeyEvent;
import javax.swing.table.DefaultTableModel;

public class ActualizarProducto extends javax.swing.JInternalFrame {
public String strSQL="";
public String var="ID_PRODUCTO",busqued="0",var2="NOMBRE";

    public ActualizarProducto() {
        initComponents();
        jButton2.setVisible(false);  
        n_pro.requestFocus();
        jRadioButton2.setSelected(true);
        cambiabusqueda();
           
    }
    
    
public void setbusqued (){
        this.busqued="1";
        jRadioButton1.setSelected(true);
        cambiabusqueda();
        mas.setVisible(false);nc.setVisible(false);jButton2.setVisible(true);
    }
public void buscar()
{
    
        Conexion cc=new Conexion();
        Connection cn= cc.getConnection();
        Statement st=null;
        ResultSet rs;
        try {
            st=(Statement) cn.createStatement();
            rs=st.executeQuery("select * from producto where "+this.var+" = '"+n_pro.getText()+"'");
            if(rs.next())
            { if(this.busqued.equals("0")){
             JOptionPane.showMessageDialog(this, "Producto existente ud puede aumentar su STOCK");
            n_pro.setText(rs.getString(var));
             id_pro.setText(rs.getString(var2));
             p_ven_pro.setText(rs.getString("precio"));
            tip_pro.setText(rs.getString("tipo"));
             can_pro.setText(rs.getString("cantidad"));
             id_pro.setEnabled(false);
           p_ven_pro.setEnabled(false);
           tip_pro.setEnabled(false);   
           can_pro.setEnabled(false);   
            }else{//en caso de adquisisiones
            Double pvt=0.00;
             n_pro.setText(rs.getString(var));
             id_pro.setText(rs.getString(var2));
             p_ven_pro.setText(rs.getString("precio"));
            tip_pro.setText(rs.getString("tipo"));
             can_pro.setText(rs.getString("cantidad"));
             //codigo de cantidad y calculo
             String cantidad = JOptionPane.showInputDialog(this, "Ingrese la Cantidad deseada menor a "+can_pro.getText()+":");
             if(Double.parseDouble(cantidad)>Double.parseDouble(can_pro.getText())||Double.parseDouble(cantidad)<1){
             JOptionPane.showMessageDialog(this, "La cantidad supera el Stock o es inferior a 1");}
             else{pvt=Double.parseDouble(cantidad)*Double.parseDouble(p_ven_pro.getText());pvt=Math.rint(pvt*100)/100;
             id_pro.setEnabled(false);
           p_ven_pro.setEnabled(false);
           tip_pro.setEnabled(false);   
           can_pro.setEnabled(false);
           this.busqued="0";
           jButton2.requestFocus();
   Object datos[]={n_pro.getText(),cantidad,(id_pro.getText()+"-tipo: "+tip_pro.getText()),p_ven_pro.getText(),"0",pvt};
   DefaultTableModel model=(DefaultTableModel)interfazRealizaVenta.tabla.getModel();
   model.removeRow(model.getRowCount () - 1);
   model.addRow(datos);
interfazRealizaVenta.subt(pvt);}
             
             
            }} else {
           JOptionPane.showMessageDialog(this, "El producto no esta registrado, REGISTRELO");
             n_pro.setEnabled(true);
             p_ven_pro.setEnabled(true);
              tip_pro.setEnabled(true);
             can_pro.setEnabled(true);
            mas.setEnabled(false);
             nc.setEnabled(false);
             id_pro.setEnabled(false);
              id_pro.setText("");
                   can_pro.setText("");
                   p_ven_pro.setText("");
                    tip_pro.setText("");
                  
            }
        } catch (SQLException ex) {
            Logger.getLogger(interfazIngresarCliente.class.getName()).log(Level.SEVERE, null, ex);
        }    
}
public void cambiabusqueda(){
    if (jRadioButton1.isSelected())
    {   jLabel2.setText("Nombre:");
        jLabel3.setText("ID:");
        this.var="ID_PRODUCTO";
        this.var2="NOMBRE";
    }else{
        jLabel3.setText("Nombre:");
        jLabel2.setText("ID producto:");
        this.var="NOMBRE";
        this.var2="ID_PRODUCTO";
    }
    
}
    
public void setstock(){
         // TODO add your handling code here:
        int c_n = Integer.parseInt(nc.getText());
        int c_v = Integer.parseInt(can_pro.getText());
        int resul=0;
        resul=c_v+c_n;
        String nuevo= Integer.toString(resul); 
        Conexion cc=new Conexion();
        Connection cn= cc.getConnection();
         
      try
        {
            PreparedStatement pstm = cn.prepareStatement("UPDATE producto SET cantidad='"+nuevo
                    +"' WHERE nombre='"+n_pro.getText()+"'");
            
            pstm.execute();
            pstm.close();
            JOptionPane.showMessageDialog(this, "Cantidad modificada");
                  can_pro.setText("");
                   p_ven_pro.setText("");
                    tip_pro.setText("");
                   id_pro.setText("");
                   nc.setText("");
             n_pro.setEnabled(true);
             p_ven_pro.setEnabled(false);
              tip_pro.setEnabled(false);
             can_pro.setEnabled(false);
            mas.setEnabled(false);
             nc.setEnabled(false);
             id_pro.setEnabled(false);
             guar.setEnabled(true);
        }
        
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(this, "Cantidad no modificada");
           
          
        
       }
}

    public void actualizar()
    {
                Conexion cc=new Conexion();
        Connection cn= cc.getConnection();
        String no= n_pro.getText();
        String p= p_ven_pro.getText();
        String c= can_pro.getText();
        String tipo= tip_pro.getText();
      try
        {
            PreparedStatement pstm = cn.prepareStatement("UPDATE producto SET  cantidad='"+c
                    +"', tipo='"+tipo
                    +"', precio='"+p
                    +"' WHERE nombre='"+n_pro.getText()+"'");
            
            pstm.execute();
            pstm.close();
            JOptionPane.showMessageDialog(this, "Datos modificados con Exito");
                  can_pro.setText("");
                   p_ven_pro.setText("");
                    tip_pro.setText("");
                   id_pro.setText("");
                   nc.setText("");
             n_pro.setEnabled(true);
             p_ven_pro.setEnabled(false);
              tip_pro.setEnabled(false);
             can_pro.setEnabled(false);
            mas.setEnabled(true);
             nc.setEnabled(true);
             id_pro.setEnabled(false);
             guar.setEnabled(true);
        }
        
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(this, "Datos no modificados");
       
       }
    }

    public void Registrar(){
    Conexion cc=new Conexion();
        Connection cn= cc.getConnection();
        
        String no= n_pro.getText();
        
        String c= can_pro.getText();
        String tipo= tip_pro.getText();
         String uti= util.getText();
          String pcom= pven.getText();
        Double suma=    ((Double.parseDouble(util.getText()) /100 )  *Double.parseDouble(pven.getText())   )+Double.parseDouble(pven.getText()) ;
        String p= ""+suma;
        strSQL="insert into Producto (nombre, cantidad, tipo,precio,utilidad,precio_c) values (?,?,?,?,?,?)";            
       try {         
           PreparedStatement pst = cn.prepareStatement(strSQL);
            pst.setString(1, no);
             pst.setString(2, c);
              pst.setString(3, tipo);
               pst.setString(4, p);
              pst.setString(5, uti);
               pst.setString(6, pcom);               
                int n=pst.executeUpdate();
                if(n>0){
                    JOptionPane.showMessageDialog(this, "Datos registrado con exito..!!");
                   n_pro.setText("");
                   p_ven_pro.setText("");
                   can_pro.setText("");
                   tip_pro.setText("");
                   
                
                }
        } catch (SQLException ex) {
            Logger.getLogger(interfazIngresarCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
}

  public boolean mensajeconfirmacion(String mensaje){
Object [] opciones ={"Aceptar","Cancelar"};
int eleccion = JOptionPane.showOptionDialog(rootPane,"En realidad desea"+ mensaje +"?","Mensaje de Confirmacion",
JOptionPane.YES_NO_OPTION,
JOptionPane.QUESTION_MESSAGE,null,opciones,"Aceptar");
if (eleccion == JOptionPane.YES_OPTION)
{
return true;
}return false;}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        id_pro = new javax.swing.JTextField();
        can_pro = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tip_pro = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        nc = new javax.swing.JTextField();
        mas = new javax.swing.JButton();
        p_ven_pro = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        pven = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        util = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        n_pro = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        guar = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setTitle("Ingreso de Productos");

        jLabel1.setFont(new java.awt.Font("Vladimir Script", 3, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 0, 255));
        jLabel1.setText("Ingresar Productos");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 2, 14)); // NOI18N
        jLabel2.setText("Nombre:");

        id_pro.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        can_pro.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        can_pro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                can_proKeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 2, 14)); // NOI18N
        jLabel4.setText("Cantidad:");

        jLabel5.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 2, 14)); // NOI18N
        jLabel5.setText("Tipo:");

        tip_pro.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 2, 14)); // NOI18N
        jLabel6.setText("Precio-Venta:");

        nc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ncKeyTyped(evt);
            }
        });

        mas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/add.png"))); // NOI18N
        mas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                masActionPerformed(evt);
            }
        });

        p_ven_pro.setEditable(false);
        p_ven_pro.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        p_ven_pro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                p_ven_proKeyTyped(evt);
            }
        });

        jLabel8.setText("Precio de Compra");

        jLabel7.setText("% Utilidad");

        util.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                utilFocusLost(evt);
            }
        });
        util.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                utilKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel4)))
                        .addGap(47, 47, 47)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tip_pro)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(can_pro, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(47, 47, 47)
                                .addComponent(mas, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                                .addComponent(nc, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(id_pro)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(27, 27, 27)
                        .addComponent(p_ven_pro))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(util)
                            .addComponent(pven))))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(id_pro, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(can_pro, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4))
                    .addComponent(mas)
                    .addComponent(nc, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tip_pro, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(pven, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(util, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(p_ven_pro, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addContainerGap())
        );

        jLabel3.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 2, 14)); // NOI18N
        jLabel3.setText("ID:");

        n_pro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                n_proKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                n_proKeyTyped(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ferreteria/imagenes/Search.png"))); // NOI18N
        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setSelected(true);
        jRadioButton1.setText("Por ID");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("Por Nombre");
        jRadioButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButton2MouseClicked(evt);
            }
        });
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(n_pro, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButton1)
                            .addComponent(jRadioButton2))))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(n_pro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jButton1)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jRadioButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButton2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        guar.setForeground(new java.awt.Color(240, 240, 240));
        guar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ferreteria/imagenes/7.png"))); // NOI18N
        guar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guarActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ferreteria/imagenes/mat.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ferreteria/imagenes/text_edit.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ferreteria/imagenes/Close.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(guar, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4)
                    .addComponent(jButton5)
                    .addComponent(jButton3)
                    .addComponent(guar, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/edit.png"))); // NOI18N
        jButton2.setText("ACCEPTAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jButton2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jButton2KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(186, 186, 186)
                        .addComponent(jButton2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(61, 61, 61))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
buscar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void masActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_masActionPerformed
setstock();   
    }//GEN-LAST:event_masActionPerformed

    private void guarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guarActionPerformed
 Registrar();
    }//GEN-LAST:event_guarActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
actualizar();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
         n_pro.setEnabled(true);
             p_ven_pro.setEnabled(true);
              tip_pro.setEnabled(true);
             can_pro.setEnabled(true);
            mas.setEnabled(false);
             nc.setEnabled(false);
             id_pro.setEnabled(false);
             guar.setEnabled(false);
            
    }//GEN-LAST:event_jButton5ActionPerformed

    private void n_proKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_n_proKeyTyped
        char c = evt.getKeyChar();
      if((c<'a' || c>'z')&&(c<'A' || c>'Z')&&(c<' '||c>' '))evt.consume();
    }//GEN-LAST:event_n_proKeyTyped

    private void can_proKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_can_proKeyTyped
      char c = evt.getKeyChar();
      if((c<'0' || c>'9')&&(c<'.'||c>'.'))evt.consume();
    }//GEN-LAST:event_can_proKeyTyped

    private void ncKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ncKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
      if((c<'0' || c>'9')&&(c<'.'||c>'.'))evt.consume();
    }//GEN-LAST:event_ncKeyTyped

    private void p_ven_proKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_p_ven_proKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
      if((c<'0' || c>'9')&&(c<'.'||c>'.'))evt.consume();
    }//GEN-LAST:event_p_ven_proKeyTyped

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
       cambiabusqueda();
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
      cambiabusqueda(); // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jRadioButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton2MouseClicked
       cambiabusqueda();        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton2MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void n_proKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_n_proKeyReleased
if(evt.getKeyCode()==KeyEvent.VK_ENTER ){        
 buscar();   
}// TODO add your handling code here:
    }//GEN-LAST:event_n_proKeyReleased

    private void jButton2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton2KeyReleased
     if(evt.getKeyCode()==KeyEvent.VK_ENTER){
         this.dispose();
     }
    }//GEN-LAST:event_jButton2KeyReleased

    private void utilKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_utilKeyReleased
 
    // TODO add your handling code here:
    }//GEN-LAST:event_utilKeyReleased

    private void utilFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_utilFocusLost

        Double suma= ((Double.parseDouble(util.getText()) /100 )  *Double.parseDouble(pven.getText())   )+Double.parseDouble(pven.getText()) ;
        p_ven_pro.setText(""+suma);
      // TODO add your handling code here:
    }//GEN-LAST:event_utilFocusLost

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    public javax.swing.JTextField can_pro;
    private javax.swing.JButton guar;
    private javax.swing.JTextField id_pro;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JButton mas;
    public javax.swing.JTextField n_pro;
    private javax.swing.JTextField nc;
    public javax.swing.JTextField p_ven_pro;
    private javax.swing.JTextField pven;
    public javax.swing.JTextField tip_pro;
    private javax.swing.JTextField util;
    // End of variables declaration//GEN-END:variables
}
