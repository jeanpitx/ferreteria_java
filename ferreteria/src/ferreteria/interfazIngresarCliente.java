package ferreteria;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

public class interfazIngresarCliente extends javax.swing.JInternalFrame {
public String strSQL="";
private ResultSetMetaData mtd;
public String inter="";
    public interfazIngresarCliente() {
        initComponents();
        nombre.setEnabled(false);
        apellidos.setEnabled(false);
        dir.setEnabled(false);
        tel.setEnabled(false);
        jButton2.setVisible(false);
    }

    public void registrar(){
               Conexion cc=new Conexion();
        Connection cn= cc.getConnection();
        
        String nom= nombre.getText();
        String ape= apellidos.getText();
        String ce= cedula.getText();
        String tele = tel.getText();
        String direc= dir.getText();
        
        strSQL="insert into personas (nombre, apellidos, cedula, direccion, telefono) values (?,?,?,?,?)";            
       try {         
           PreparedStatement pst = cn.prepareStatement(strSQL);
            pst.setString(1, nom);
             pst.setString(2, ape);
              pst.setString(3, ce);
               pst.setString(4, direc);
                pst.setString(5, tele);
                int n=pst.executeUpdate();
                if(n>0){
                    JOptionPane.showMessageDialog(this, "Datos registrado con exito..!!");
                   nombre.setText("");
                   apellidos.setText("");
                   tel.setText("");
                   dir.setText("");
                   cedula.setText("");
                
                }
        } catch (SQLException ex) {
            Logger.getLogger(interfazIngresarCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void consultar(){
    Conexion cc=new Conexion();
        Connection cn= cc.getConnection();
        Statement st=null;
        ResultSet rs;
        try {
            st=(Statement) cn.createStatement();
            rs=st.executeQuery("select * from personas where cedula = '"+cedula.getText()+"'");
            if(rs.next())
            {
              JOptionPane.showMessageDialog(this, "El cliente ya esta registrado");
             nombre.setText(rs.getString("nombre"));
             apellidos.setText(rs.getString("apellidos"));
             dir.setText(rs.getString("direccion"));
             tel.setText(rs.getString("telefono"));
            } else {
            JOptionPane.showMessageDialog(this, "El cliente no esta registrado, REGISTRELO");
             nombre.setEnabled(true);
             apellidos.setEnabled(true);
             dir.setEnabled(true);
             tel.setEnabled(true);
              nombre.setText("");
                   apellidos.setText("");
                   tel.setText("");
                   dir.setText("");
                   
            }
        } catch (SQLException ex) {
            Logger.getLogger(interfazIngresarCliente.class.getName()).log(Level.SEVERE, null, ex);
        }    
        
        
       
           
    }
    
    public void actualizar(){
        Conexion cc=new Conexion();
        Connection cn= cc.getConnection();
         String nom= nombre.getText();
        String ape= apellidos.getText();
        String ce= cedula.getText();
        String tele = tel.getText();
        String direc= dir.getText();
      try
        {
            PreparedStatement pstm = cn.prepareStatement("UPDATE personas SET nombre='"+nom+"', apellidos= '"+ape
                    +"', direccion='"+direc
                    +"', telefono='"+tele
                    +"' WHERE cedula='"+ce+"'");
            
            pstm.execute();
            pstm.close();
            JOptionPane.showMessageDialog(this, "Datos modificados con Exito");
            nombre.setEnabled(false);
        apellidos.setEnabled(false);
        dir.setEnabled(false);
        tel.setEnabled(false);
        guar.setEnabled(true);        
nombre.setText("");
        apellidos.setText("");
        dir.setText("");
        tel.setText("");

        
            
        }
        
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(this, "Datos no modificados");
           
          
        
       }
    }
    
    public void setbusqued(String var){
        jButton2.setVisible(true);
        this.inter=var;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        guar = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        cedula = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        nombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        apellidos = new javax.swing.JTextField();
        tel = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        dir = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Vladimir Script", 3, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 255));
        jLabel1.setText("Registro Cliente");

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

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ferreteria/imagenes/Close.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ferreteria/imagenes/text_edit.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        cedula.setText("Cedula");
        cedula.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cedulaMouseClicked(evt);
            }
        });
        cedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cedulaActionPerformed(evt);
            }
        });
        cedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cedulaKeyTyped(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 2, 14)); // NOI18N
        jLabel2.setText("ID Cliente");

        jButton1.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ferreteria/imagenes/Search.png"))); // NOI18N
        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLayeredPane1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreActionPerformed(evt);
            }
        });
        nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nombreKeyTyped(evt);
            }
        });
        jLayeredPane1.add(nombre);
        nombre.setBounds(100, 10, 240, 20);

        jLabel3.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 2, 14)); // NOI18N
        jLabel3.setText("Nombre:");
        jLayeredPane1.add(jLabel3);
        jLabel3.setBounds(20, 10, 50, 16);

        jLabel4.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 2, 14)); // NOI18N
        jLabel4.setText("Apellidos:");
        jLayeredPane1.add(jLabel4);
        jLabel4.setBounds(20, 50, 51, 16);

        apellidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apellidosActionPerformed(evt);
            }
        });
        apellidos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                apellidosKeyTyped(evt);
            }
        });
        jLayeredPane1.add(apellidos);
        apellidos.setBounds(100, 50, 240, 20);

        tel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                telActionPerformed(evt);
            }
        });
        tel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                telKeyTyped(evt);
            }
        });
        jLayeredPane1.add(tel);
        tel.setBounds(100, 90, 240, 20);

        jLabel5.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 2, 14)); // NOI18N
        jLabel5.setText("Teléfono");
        jLayeredPane1.add(jLabel5);
        jLabel5.setBounds(20, 90, 44, 16);

        jLabel6.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 2, 14)); // NOI18N
        jLabel6.setText("Dirección:");
        jLayeredPane1.add(jLabel6);
        jLabel6.setBounds(20, 130, 51, 16);

        dir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dirActionPerformed(evt);
            }
        });
        dir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                dirKeyTyped(evt);
            }
        });
        jLayeredPane1.add(dir);
        dir.setBounds(100, 130, 240, 20);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cedula, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cedula, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/edit.png"))); // NOI18N
        jButton2.setText("LLevar datos a Formulario");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(guar, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(76, 76, 76))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(124, 124, 124)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(guar)
                    .addComponent(jButton3)
                    .addComponent(jButton5)
                    .addComponent(jButton4))
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cedulaActionPerformed

    }//GEN-LAST:event_cedulaActionPerformed

    private void telActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_telActionPerformed
tel.transferFocus();
    }//GEN-LAST:event_telActionPerformed

    private void nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreActionPerformed
 nombre.transferFocus();
    }//GEN-LAST:event_nombreActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
 this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void guarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guarActionPerformed
 registrar();
    }//GEN-LAST:event_guarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
consultar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void apellidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apellidosActionPerformed
        // TODO add your handling code here:
        apellidos.transferFocus();
    }//GEN-LAST:event_apellidosActionPerformed

    private void dirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dirActionPerformed
        // TODO add your handling code here:
        dir.transferFocus();
    }//GEN-LAST:event_dirActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
              actualizar();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        nombre.setEnabled(true);
        apellidos.setEnabled(true);
        dir.setEnabled(true);
        tel.setEnabled(true);
        guar.setEnabled(false);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void telKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_telKeyTyped
      char c = evt.getKeyChar();
      if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_telKeyTyped

    private void nombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombreKeyTyped
        char c = evt.getKeyChar();
      if((c<'a' || c>'z')&&(c<'A' || c>'Z')&&(c<' '||c>' '))evt.consume();
    }//GEN-LAST:event_nombreKeyTyped

    private void apellidosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_apellidosKeyTyped
   char c = evt.getKeyChar();
      if((c<'a' || c>'z')&&(c<'A' || c>'Z')&&(c<' '||c>' '))evt.consume();
    }//GEN-LAST:event_apellidosKeyTyped

    private void dirKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dirKeyTyped
  
    }//GEN-LAST:event_dirKeyTyped

    private void cedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cedulaKeyTyped
     char c = evt.getKeyChar();
      if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_cedulaKeyTyped

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
//este es el boton que aparece cuando no se pide credito
        if(this.inter.equals("credito"))
{this.dispose();}
else{
 JTextField a=(JTextField)interfazRealizaVenta.clien;
a.setText(nombre.getText()+" "+ apellidos.getText());

JLabel b=(JLabel)interfazRealizaVenta.clienc;
b.setText(cedula.getText());   
}
this.dispose();// TODO add your handling code here:}
    }//GEN-LAST:event_jButton2ActionPerformed

    private void cedulaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cedulaMouseClicked
cedula.setText("");        // TODO add your handling code here:
    }//GEN-LAST:event_cedulaMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField apellidos;
    public static javax.swing.JTextField cedula;
    private javax.swing.JTextField dir;
    private javax.swing.JButton guar;
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
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField nombre;
    private javax.swing.JTextField tel;
    // End of variables declaration//GEN-END:variables
}
