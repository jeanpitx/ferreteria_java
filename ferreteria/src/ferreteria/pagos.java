/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ferreteria;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
/**
 *
 * @author alberto
 */
public class pagos extends javax.swing.JInternalFrame {
public String strSQL="";
private char  n;
    /**
     * Creates new form pagos
     */
    public pagos() {
        initComponents();
        jButton3.setVisible(false);
    }
    
    public void buscar(){
      Conexion cc=new Conexion();
        Connection cn= cc.getConnection();
        Statement st=null;
        ResultSet rs;
        try {
            st=(Statement) cn.createStatement();
            rs=st.executeQuery("SELECT * FROM `credito` WHERE ID_USUARIO = '" + cedula.getText()+ "' AND ID_CREDITO = (SELECT MAX( ID_CREDITO ) FROM CREDITO WHERE ID_USUARIO='"+ cedula.getText() +"' ) ");
            if(rs.next())
            {  
                M_I.setText(rs.getString("credito.valor_deber"));

            } else {
                JOptionPane.showMessageDialog(this, "El cliente no esta registrado, REGISTRELO");
         
            }
        } catch (SQLException ex) {
            Logger.getLogger(interfazIngresarCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void actualizar(){
           
        Double m = Double.parseDouble(M_I.getText());
        Double pago= Double.parseDouble(pag.getText());
        String pagos= pag.getText();
        String ced=cedula.getText();
         Double res;
         
        if(uno.isSelected()){
      
        res=m-pago;
         String resp=String.valueOf(res);
        Conexion cc=new Conexion();
        Connection cn= cc.getConnection();
          String strSQL="insert into credito (valor_deber, valor_ultimo_pago, id_usuario) values (?,?,?)";
      try
        {
           
            PreparedStatement pst = cn.prepareStatement(strSQL);
           // pst.setInt(1, cod);
            pst.setString(1,resp );
            pst.setString(2, pagos);
             pst.setString(3, ced);
           int n=pst.executeUpdate();
            
          
           
            JOptionPane.showMessageDialog(this, "Cantidad modificada");
                  
        }
        
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(this, "Cantidad no modificada");
           
          
        
       }
        
        }else{if ((pago+m)>100)
         {JOptionPane.showMessageDialog(rootPane, "El Valor supera los 100 dolares");
         }else{
          res=m+pago;
         String resp=String.valueOf(res);
        Conexion cc=new Conexion();
        Connection cn= cc.getConnection();
          String strSQL="insert into credito (valor_deber, valor_ultimo_pago, id_usuario) values (?,?,?)";
      try
        {
           
            PreparedStatement pst = cn.prepareStatement(strSQL);
           // pst.setInt(1, cod);
            pst.setString(1,resp );
            pst.setString(2, pagos);
             pst.setString(3, ced);
           int n=pst.executeUpdate();
           
            JOptionPane.showMessageDialog(this, "Cantidad modificada");
                  
        }
        
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(this, "Cantidad no modificada");
           
          
        
       }
        }}
    }
    
    
public void setbusqued(){
    jButton3.setVisible(true);
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupo2 = new javax.swing.ButtonGroup();
        jLabel2 = new javax.swing.JLabel();
        cedula = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        M_I = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        pag = new javax.swing.JTextField();
        uno = new javax.swing.JRadioButton();
        dos = new javax.swing.JRadioButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        lb_cedula = new javax.swing.JLabel();

        jLabel2.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 2, 14)); // NOI18N
        jLabel2.setText("ID Cliente");

        cedula.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cedulaActionPerformed(evt);
            }
        });
        cedula.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cedulaFocusLost(evt);
            }
        });
        cedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cedulaKeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cedulaKeyReleased(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ferreteria/imagenes/Search.png"))); // NOI18N
        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Vladimir Script", 3, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 0, 255));
        jLabel1.setText("Credito al Cliente");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Nuevo Pago/ Aumento de valor"));

        jLabel7.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 2, 14)); // NOI18N
        jLabel7.setText("Monto :");

        M_I.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        M_I.setForeground(new java.awt.Color(255, 0, 0));

        jLabel8.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 2, 14)); // NOI18N
        jLabel8.setText("pago/Incremento:");

        grupo2.add(uno);
        uno.setText("Pagar");
        uno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unoActionPerformed(evt);
            }
        });

        grupo2.add(dos);
        dos.setSelected(true);
        dos.setText("Aumentar credito");
        dos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dosActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ferreteria/imagenes/Refresh.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(uno)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(M_I, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pag, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(dos)))
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(M_I, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(uno)
                            .addComponent(dos))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(pag, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(56, 56, 56))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ferreteria/imagenes/Close.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/edit.png"))); // NOI18N
        jButton3.setText("LLevar datos a Formulario");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        lb_cedula.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        lb_cedula.setForeground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jLabel2)
                        .addGap(28, 28, 28)
                        .addComponent(cedula, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(45, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 252, Short.MAX_VALUE)
                    .addComponent(lb_cedula)
                    .addGap(0, 253, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cedula, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButton4))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jButton3)))
                .addContainerGap(74, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 235, Short.MAX_VALUE)
                    .addComponent(lb_cedula)
                    .addGap(0, 236, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cedulaActionPerformed

    }//GEN-LAST:event_cedulaActionPerformed

    private void cedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cedulaKeyTyped

        char c = evt.getKeyChar();
        if(c<'0' || c>'9')evt.consume();
        
         n=evt.getKeyChar();
     validacion.keyType(evt,n , 1);
     String Caracteres = cedula.getText();
        if(Caracteres.length()>=10){
            evt.consume();
           }
    }//GEN-LAST:event_cedulaKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      buscar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void unoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unoActionPerformed
        // TODO add your handling code here:
        
       
    }//GEN-LAST:event_unoActionPerformed

    private void dosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dosActionPerformed
        // TODO add your handling code here:
  
    }//GEN-LAST:event_dosActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here: if(uno.isSelected()){
    actualizar();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String cpp="";
        Conexion cc=new Conexion();
        Connection cn= cc.getConnection();
        Statement st=null;
        ResultSet rs;
        try {
            st=(Statement) cn.createStatement();
            rs=st.executeQuery("select * from credito where ID_USUARIO='"+cedula.getText()+"'");
            if(rs.next())
            {  
                cpp=rs.getString("credito.id_credito");

            } else {
                JOptionPane.showMessageDialog(this, "El cliente no esta registrado, REGISTRELO");
         
            }
        } catch (SQLException ex) {
            Logger.getLogger(interfazIngresarCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        JTextField a=(JTextField)interfazRealizaVenta.credii;
        a.setText("000000000"+ cpp);
        this.dispose();// TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void cedulaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cedulaKeyReleased
 boolean ced=validacion.cedula(lb_cedula, cedula);
        if(ced==true) {
            cedula.setBackground(Color.white);
            cedula.requestFocus();
        } else {
            
            
            cedula.setEnabled(true);
            cedula.setBackground(Color.red);
            cedula.requestFocus();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_cedulaKeyReleased

    private void cedulaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cedulaFocusLost
validacion.cedula(lb_cedula, cedula);        // TODO add your handling code here:
    }//GEN-LAST:event_cedulaFocusLost

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel M_I;
    public static javax.swing.JTextField cedula;
    private javax.swing.JRadioButton dos;
    private javax.swing.ButtonGroup grupo2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lb_cedula;
    public javax.swing.JTextField pag;
    private javax.swing.JRadioButton uno;
    // End of variables declaration//GEN-END:variables
}
