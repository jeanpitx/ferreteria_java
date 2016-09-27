
package ferreteria;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;


/**
 *
 * @author Usuario
 */
public class interfazRealizaVenta extends javax.swing.JInternalFrame {
DefaultTableModel mod;
imprimir i=new imprimir();   
Calendar fecha = new GregorianCalendar();
    public interfazRealizaVenta() {
        initComponents();
        this.setTitle("Pantalla de Ventas -METALPAR- ");
        pago.setVisible(false);
        panelfactura.setVisible(false);
        cmdCerrar2.setVisible(false);
tabla.transferFocus();
tabla.changeSelection ( tabla.getRowCount () - 1, 0, false, false );
delete.enable(false);jButton9.enable(false); 
jLabel16.setText("Portoviejo - "+fecha.get(Calendar.DAY_OF_MONTH) +"/"+Integer.parseInt(""+(fecha.get(Calendar.MONTH)+1))+"/"+fecha.get(Calendar.YEAR));
llenauser();
 jButton10.setEnabled(false);
    }
    
    
    public void buscarcliente(){
         interfazIngresarCliente a= new interfazIngresarCliente();
    if(estacerrado(a))
    {delete.enable(true);jButton9.enable(true);cmdCerrar2.setVisible(true);principal.panel.add(a);
    a.toFront();
    a.setVisible(true);
    a.setbusqued("");
    //empieza codigo de llamadas
    a.cedula.setText("");
    a.cedula.requestFocusInWindow();
    }else{JOptionPane.showMessageDialog(this,"La ventana ya esta abierta...");}
    }

    public void busquedaproducto(){
    // marcamos el ultimo valor de la tabla para su verificacion
    String v=(String)tabla.getValueAt(tabla.getRowCount()-1 ,0);
   //pregunta si es enter para abrir la interfaz producto    
    if(v==null||v.equals("")){JOptionPane.showMessageDialog(this,"Debe Ingresar el producto o cantidad deseada...");}
    else{      
   //abre la interfaz producto*****************************************************
    ActualizarProducto a= new ActualizarProducto();
    if(estacerrado(a))
    {delete.enable(true);jButton9.enable(true);cmdCerrar2.setVisible(true);principal.panel.add(a);
    a.toFront();
    a.setVisible(true);
    a.setbusqued();
    //empieza codigo de llamadas
    a.n_pro.setText(v);
    a.n_pro.requestFocusInWindow();
    }else{JOptionPane.showMessageDialog(this,"La ventana ya esta abierta...");}}  
    tabla.requestFocus ();
    tabla.changeSelection ( tabla.getRowCount () - 1, 0, false, false );
    
// TODO add your handling code here:
}
    public void generarreporte(){
        if(mensajeconfirmacion("Imprimir la venta")){
 i.clienc.setText(clienc.getText());
 i.clien.setText(clien.getText());
 i.txtiva.setText(txtiva.getText());
 i.txtsubt.setText(txtsubt.getText());
 i.txtdesc.setText(txtdesc.getText());
 i.txttotal.setText(txttotal.getText());
  i.txtpagar.setText(txtpagar.getText());
  i.jLabel16.setText(jLabel16.getText());
    DefaultTableModel modelo = (DefaultTableModel)imprimir.tabla.getModel();
    
    String []r=new String[6];
    for(int e=0;e<tabla.getRowCount();e++){//cuenta cuentas filas tiene el jtable         
           r[0]=tabla.getValueAt(e, 0).toString();
    r[1]=tabla.getValueAt(e, 1).toString();
    r[2]=tabla.getValueAt(e, 2).toString();
    r[3]=tabla.getValueAt(e, 3).toString();
    r[4]=tabla.getValueAt(e, 4).toString();
    r[5]=tabla.getValueAt(e, 5).toString();
    modelo.addRow(r);
     }
    principal.panel.add(i);
    i.toFront();
    i.setVisible(true);
    
   
} 
    }
    public void buscarcreditocliente(){
          pagos a= new pagos();
    if(estacerrado(a))
    {delete.enable(true);jButton9.enable(true);cmdCerrar2.setVisible(true);principal.panel.add(a);
    a.toFront();
    a.setVisible(true);
    a.setbusqued();
    //empieza codigo de llamadas
    a.pag.setText(txttotal.getText());
    a.cedula.requestFocusInWindow();
    }else{JOptionPane.showMessageDialog(this,"La ventana ya esta abierta...");} 
    }   
    public void registrarventa(int q){
        if(q==1)
        {
         
        Conexion cc=new Conexion();
        Connection cn= cc.getConnection();
      String cedula=clienc.getText();
      String iva=txtiva.getText();
       String total=txttotal.getText();
       String subtotal=txtsubt.getText();
       String descuento=txtdesc.getText();
       String pagar=txtpagar.getText();
        String fac=txtvuelto6.getText();
        //String fech=jLabel16.getText();        

        
        String strSQL="insert into factura (valor_pagar, iva, descuento,id_usuario,id_producto) values (?,?,?,?,?)";
        String strSQL2="insert into detalle_factura (catidad,subtotal,total) values (?,?,?)";
       
        for(int i=0;i<tabla.getRowCount();i++){//cuenta cuentas filas tiene el jtable         
            String id_art=tabla.getValueAt(i,0).toString();
              String cantidad=tabla.getValueAt(i,1).toString();   
        try {
            PreparedStatement pst = cn.prepareStatement(strSQL);
           // pst.setInt(1, cod);
            pst.setString(1,pagar );
            pst.setString(2, iva);
             pst.setString(3, descuento);
            pst.setString(4, cedula);
             pst.setString(5, id_art);
           int a=pst.executeUpdate();
         if(a>0){
                //JOptionPane.showMessageDialog(this, "Datos registrado con exito..!!");
                
            }
        } catch (SQLException ex) {
        Logger.getLogger(interfazIngresarCliente.class.getName()).log(Level.SEVERE, null, ex);
        }    
           
                  try {
            PreparedStatement pst = cn.prepareStatement(strSQL2);
           // pst.setString(1, fac);
            pst.setString(1, cantidad);
            pst.setString(2, subtotal);
            pst.setString(3, total);
         
           
            int a=pst.executeUpdate();
            if(a>0){
              // JOptionPane.showMessageDialog(this, "Datos registrado con exito..!!");
             

            }
        } catch (SQLException ex) {
            Logger.getLogger(interfazIngresarCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        
        
       
        }JOptionPane.showMessageDialog(this, "Datos registrado con exito..!!");   
        }else if(q==11){
                  Double valor= Double.parseDouble(txtpagar.getText());
Double total= Double.parseDouble(txttotal.getText());
if(valor>0&&valor>=total){
    if(valor==total){JOptionPane.showMessageDialog(rootPane, "Transaccion Finalizada");jButton10.setEnabled(true);generarreporte();
    for(int x=0;x<=tabla.getRowCount()-1;x++){actualizastock((String)tabla.getValueAt(x ,0),(String)tabla.getValueAt(x ,1));}
   
    }
    else{txtvuelto.setText("" +(Math.rint((valor-total)*100)/100));JOptionPane.showMessageDialog(rootPane, "Transaccion Finalizada");jButton10.setEnabled(true);generarreporte();
    for(int x=0;x<=tabla.getRowCount()-1;x++){actualizastock((String)tabla.getValueAt(x ,0),(String)tabla.getValueAt(x ,1));}}    

}else{//en caso de pagar por partes
Object [] opciones ={"Aceptar","Cancelar"};
int eleccion = JOptionPane.showOptionDialog(rootPane,"El valor que ingreso es menor al total: Desea Pagar una parte?","Mensaje de Confirmacion",
JOptionPane.YES_NO_OPTION,
JOptionPane.QUESTION_MESSAGE,null,opciones,"Aceptar");
if (eleccion == JOptionPane.YES_OPTION)
{
total=total-valor;
txttotal.setText(""+total);
}      


}

        // TODO add your handling code here: 
        }else if(q==2){
            
                 
        
        Conexion cc=new Conexion();
        Connection cn= cc.getConnection();
      String cedula=clienc.getText();
      String iva=txtiva.getText();
       String total=txttotal.getText();
       String subtotal=txtsubt.getText();
       String descuento=txtdesc.getText();
       String pagar=txtpagar2.getText();
       String pro=txtpagar2.getText();
        String fac=txtvuelto6.getText();
        //String fech=jLabel16.getText();        

        
        String strSQL="insert into factura (valor_pagar, iva, descuento, id_usuario,id_producto) values (?,?,?,?,?)";
        String strSQL2="insert into detalle_factura (catidad,subtotal,total) values (?,?,?)";
       
        for(int i=0;i<tabla.getRowCount();i++){//cuenta cuentas filas tiene el jtable         
            String id_art=tabla.getValueAt(i,0).toString();
              String cantidad=tabla.getValueAt(i,1).toString();   
        try {
            PreparedStatement pst = cn.prepareStatement(strSQL);
           // pst.setInt(1, cod);
            pst.setString(1,pagar );
            pst.setString(2, iva);
             pst.setString(3, descuento);
             pst.setString(4, cedula);
            pst.setString(5, id_art);
             //pst.setString(6, id_art);
           int a=pst.executeUpdate();
         if(a>0){
                //JOptionPane.showMessageDialog(this, "Datos registrado con exito..!!");
                
            }
        } catch (SQLException ex) {
        Logger.getLogger(interfazIngresarCliente.class.getName()).log(Level.SEVERE, null, ex);
        }    
           
                  try {
            PreparedStatement pst = cn.prepareStatement(strSQL2);
           // pst.setString(1, fac);
            pst.setString(1, cantidad);
            pst.setString(2, subtotal);
            pst.setString(3, total);
         
           
            int a=pst.executeUpdate();
            if(a>0){
               JOptionPane.showMessageDialog(this, "Datos registrado con exito..!!");
             

            }
        } catch (SQLException ex) {
            Logger.getLogger(interfazIngresarCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        
        
       
        }
        
        
            
        }else if(q==22){
                  Double valor= Double.parseDouble(txtpagar2.getText());
    Double total= Double.parseDouble(txttotal.getText());
    if(valor>0&&valor>=total){
    JOptionPane.showMessageDialog(rootPane, "Transaccion Finalizada"); generarreporte();jButton10.setEnabled(true);
    for(int x=0;x<=tabla.getRowCount()-1;x++){actualizastock((String)tabla.getValueAt(x ,0),(String)tabla.getValueAt(x ,1));}
    }else{//en caso de pagar por partes
    Object [] opciones ={"Aceptar","Cancelar"};
    int eleccion = JOptionPane.showOptionDialog(rootPane,"El valor que ingreso es menor al total: Desea Pagar una parte?","Mensaje de Confirmacion",
    JOptionPane.YES_NO_OPTION,
    JOptionPane.QUESTION_MESSAGE,null,opciones,"Aceptar");
    if (eleccion == JOptionPane.YES_OPTION)
    {
    total=total-valor;
    txttotal.setText(""+total);
    }      
    }  
        }         
    }
 
    
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        menutablas = new javax.swing.JPopupMenu();
        delete = new javax.swing.JMenuItem();
        add = new javax.swing.JMenuItem();
        buscar = new javax.swing.JMenuItem();
        jPanel3 = new javax.swing.JPanel();
        clienc = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        clien = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        panelfactura = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        txtvuelto6 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jTextField2 = new javax.swing.JTextField();
        txtnombre1 = new javax.swing.JTextField();
        txtcantidad1 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jButton9 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txttotal = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtsubt = new javax.swing.JTextField();
        txtdesc = new javax.swing.JTextField();
        txtiva = new javax.swing.JTextField();
        cmdCerrar2 = new javax.swing.JButton();
        pago = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        txtvuelto = new javax.swing.JTextField();
        txtpagar = new javax.swing.JTextField();
        jPanel13 = new javax.swing.JPanel();
        jTextField3 = new javax.swing.JTextField();
        txtnombre3 = new javax.swing.JTextField();
        txtcantidad3 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        txtvuelto4 = new javax.swing.JTextField();
        txtpagar1 = new javax.swing.JTextField();
        jPanel15 = new javax.swing.JPanel();
        jTextField5 = new javax.swing.JTextField();
        txtnombre5 = new javax.swing.JTextField();
        txtcantidad5 = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        txtpagar2 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jTextField6 = new javax.swing.JTextField();
        txtnombre6 = new javax.swing.JTextField();
        txtcantidad6 = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        credii = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jLabel33 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        txtvuelto1 = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        jTextField4 = new javax.swing.JTextField();
        txtnombre4 = new javax.swing.JTextField();
        txtcantidad4 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jTextField1 = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jButton8 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();

        jMenu1.setText("jMenu1");

        jMenuItem1.setText("jMenuItem1");

        delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/del.png"))); // NOI18N
        delete.setText("Delete (D)");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });
        menutablas.add(delete);

        add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/add.png"))); // NOI18N
        add.setText("Agregar (A)");
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });
        menutablas.add(add);

        buscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/find.png"))); // NOI18N
        buscar.setText("Buscar");
        buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarActionPerformed(evt);
            }
        });
        menutablas.add(buscar);

        setNextFocusableComponent(tabla);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setOpaque(false);
        jPanel3.setLayout(null);

        clienc.setText("1000000000");
        jPanel3.add(clienc);
        clienc.setBounds(120, 60, 110, 20);

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(51, 51, 255));
        jLabel16.setText("Portoviejo 19/02/1994 17:39");
        jPanel3.add(jLabel16);
        jLabel16.setBounds(120, 10, 180, 14);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/find.png"))); // NOI18N
        jButton1.setText("(B)");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1);
        jButton1.setBounds(320, 40, 80, 20);

        jButton2.setText("Nuevo Cliente (N)");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton2);
        jButton2.setBounds(270, 60, 130, 23);

        clien.setEditable(false);
        clien.setText("Consumidor Final");
        clien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clienMouseClicked(evt);
            }
        });
        clien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clienActionPerformed(evt);
            }
        });
        jPanel3.add(clien);
        clien.setBounds(120, 40, 200, 20);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setText("Cliente Recientes:");
        jPanel3.add(jLabel13);
        jLabel13.setBounds(10, 40, 110, 20);

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel23.setText("C.I.:");
        jPanel3.add(jLabel23);
        jLabel23.setBounds(90, 60, 30, 20);

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel24.setText("Fecha de Venta:");
        jPanel3.add(jLabel24);
        jLabel24.setBounds(10, 10, 100, 14);

        panelfactura.setBorder(javax.swing.BorderFactory.createTitledBorder("factura"));

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel31.setText("Numero de Factura");

        txtvuelto6.setEditable(false);
        txtvuelto6.setText("002-002-202554");
        txtvuelto6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtvuelto6MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelfacturaLayout = new javax.swing.GroupLayout(panelfactura);
        panelfactura.setLayout(panelfacturaLayout);
        panelfacturaLayout.setHorizontalGroup(
            panelfacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelfacturaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtvuelto6, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(65, Short.MAX_VALUE))
        );
        panelfacturaLayout.setVerticalGroup(
            panelfacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelfacturaLayout.createSequentialGroup()
                .addGroup(panelfacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel31)
                    .addComponent(txtvuelto6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jPanel3.add(panelfactura);
        panelfactura.setBounds(420, 10, 360, 70);

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.setOpaque(false);
        jPanel4.setLayout(null);

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel5.setOpaque(false);
        jPanel5.setLayout(null);

        jTextField2.setText("$0.00");
        jPanel5.add(jTextField2);
        jTextField2.setBounds(270, 10, 100, 20);

        txtnombre1.setText("name");
        txtnombre1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnombre1ActionPerformed(evt);
            }
        });
        jPanel5.add(txtnombre1);
        txtnombre1.setBounds(20, 10, 140, 20);

        txtcantidad1.setText("1");
        txtcantidad1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcantidad1ActionPerformed(evt);
            }
        });
        jPanel5.add(txtcantidad1);
        txtcantidad1.setBounds(170, 10, 90, 20);

        jPanel4.add(jPanel5);
        jPanel5.setBounds(0, 0, 0, 0);

        jScrollPane2.setComponentPopupMenu(menutablas);

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null}
            },
            new String [] {
                "Articulo", "Cantidad", "Descripcion", "Precio Base", "Descuento", "Precio Venta"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla.setColumnSelectionAllowed(true);
        tabla.setComponentPopupMenu(menutablas);
        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tablaKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tabla);
        tabla.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tabla.getColumnModel().getColumn(0).setResizable(false);
        tabla.getColumnModel().getColumn(0).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(1).setResizable(false);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(1);
        tabla.getColumnModel().getColumn(2).setResizable(false);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(3).setResizable(false);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(5);
        tabla.getColumnModel().getColumn(4).setResizable(false);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(5);
        tabla.getColumnModel().getColumn(5).setResizable(false);
        tabla.getColumnModel().getColumn(5).setPreferredWidth(5);

        jPanel4.add(jScrollPane2);
        jScrollPane2.setBounds(0, 0, 770, 190);

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/del.png"))); // NOI18N
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton9);
        jButton9.setBounds(770, 30, 24, 22);

        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/find.png"))); // NOI18N
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton12);
        jButton12.setBounds(770, 60, 24, 22);

        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/add.png"))); // NOI18N
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton14);
        jButton14.setBounds(770, 0, 24, 22);

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel6.setOpaque(false);
        jPanel6.setLayout(null);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Total");
        jPanel6.add(jLabel3);
        jLabel3.setBounds(620, 100, 40, 20);

        txttotal.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txttotal.setText("0");
        txttotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttotalActionPerformed(evt);
            }
        });
        jPanel6.add(txttotal);
        txttotal.setBounds(680, 100, 90, 20);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Subt");
        jPanel6.add(jLabel8);
        jLabel8.setBounds(620, 10, 40, 20);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Desc");
        jPanel6.add(jLabel9);
        jLabel9.setBounds(620, 40, 40, 20);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Iva");
        jPanel6.add(jLabel10);
        jLabel10.setBounds(620, 70, 40, 20);

        txtsubt.setText("0");
        txtsubt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsubtActionPerformed(evt);
            }
        });
        jPanel6.add(txtsubt);
        txtsubt.setBounds(680, 10, 90, 20);

        txtdesc.setText("0");
        txtdesc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtdescMouseClicked(evt);
            }
        });
        txtdesc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdescActionPerformed(evt);
            }
        });
        txtdesc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtdescKeyReleased(evt);
            }
        });
        jPanel6.add(txtdesc);
        txtdesc.setBounds(680, 40, 90, 20);

        txtiva.setText("12");
        txtiva.setEnabled(false);
        txtiva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtivaActionPerformed(evt);
            }
        });
        jPanel6.add(txtiva);
        txtiva.setBounds(680, 70, 90, 20);

        cmdCerrar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/mat.png"))); // NOI18N
        cmdCerrar2.setText("PAGAR");
        cmdCerrar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCerrar2ActionPerformed(evt);
            }
        });
        jPanel6.add(cmdCerrar2);
        cmdCerrar2.setBounds(10, 33, 160, 80);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 255));
        jLabel14.setText("Respuesta de Venta");

        jPanel12.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel12.setOpaque(false);
        jPanel12.setLayout(null);

        txtvuelto.setEditable(false);
        txtvuelto.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtvuelto.setForeground(new java.awt.Color(255, 0, 51));
        txtvuelto.setText("0");
        jPanel12.add(txtvuelto);
        txtvuelto.setBounds(120, 30, 100, 20);

        txtpagar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtpagarMouseClicked(evt);
            }
        });
        txtpagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpagarActionPerformed(evt);
            }
        });
        jPanel12.add(txtpagar);
        txtpagar.setBounds(10, 30, 100, 20);

        jPanel13.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel13.setOpaque(false);
        jPanel13.setLayout(null);

        jTextField3.setText("$0.00");
        jPanel13.add(jTextField3);
        jTextField3.setBounds(270, 10, 100, 20);

        txtnombre3.setText("name");
        txtnombre3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnombre3ActionPerformed(evt);
            }
        });
        jPanel13.add(txtnombre3);
        txtnombre3.setBounds(20, 10, 140, 20);

        txtcantidad3.setText("1");
        txtcantidad3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcantidad3ActionPerformed(evt);
            }
        });
        jPanel13.add(txtcantidad3);
        txtcantidad3.setBounds(170, 10, 90, 20);

        jPanel12.add(jPanel13);
        jPanel13.setBounds(0, 0, 0, 0);

        jLabel1.setText("Su Cambio");
        jPanel12.add(jLabel1);
        jLabel1.setBounds(120, 10, 100, 14);

        jLabel25.setText("Ingrese Valor:");
        jPanel12.add(jLabel25);
        jLabel25.setBounds(10, 10, 100, 14);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/edit.png"))); // NOI18N
        jButton3.setText("Finalizar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/guardar.jpg"))); // NOI18N
        jButton15.setText("Guardar");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pago.addTab("Efectivo", jPanel2);

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(0, 0, 255));
        jLabel26.setText("Respuesta de Venta");

        jPanel14.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel14.setOpaque(false);
        jPanel14.setLayout(null);

        txtvuelto4.setEditable(false);
        txtvuelto4.setBackground(new java.awt.Color(255, 255, 255));
        txtvuelto4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtvuelto4.setForeground(new java.awt.Color(255, 0, 51));
        txtvuelto4.setText("0");
        jPanel14.add(txtvuelto4);
        txtvuelto4.setBounds(130, 60, 100, 20);

        txtpagar1.setText("XXX-XX-X");
        txtpagar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtpagar1MouseClicked(evt);
            }
        });
        txtpagar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpagar1ActionPerformed(evt);
            }
        });
        jPanel14.add(txtpagar1);
        txtpagar1.setBounds(120, 10, 100, 20);

        jPanel15.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel15.setOpaque(false);
        jPanel15.setLayout(null);

        jTextField5.setText("$0.00");
        jPanel15.add(jTextField5);
        jTextField5.setBounds(270, 10, 100, 20);

        txtnombre5.setText("name");
        txtnombre5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnombre5ActionPerformed(evt);
            }
        });
        jPanel15.add(txtnombre5);
        txtnombre5.setBounds(20, 10, 140, 20);

        txtcantidad5.setText("1");
        txtcantidad5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcantidad5ActionPerformed(evt);
            }
        });
        jPanel15.add(txtcantidad5);
        txtcantidad5.setBounds(170, 10, 90, 20);

        jPanel14.add(jPanel15);
        jPanel15.setBounds(0, 0, 0, 0);

        jLabel27.setText("Valor a Devolver");
        jPanel14.add(jLabel27);
        jLabel27.setBounds(130, 40, 100, 14);

        jLabel28.setText("Transaciion N°:");
        jPanel14.add(jLabel28);
        jLabel28.setBounds(10, 10, 100, 14);

        jLabel29.setText("Valor con Tarjeta:");
        jPanel14.add(jLabel29);
        jLabel29.setBounds(10, 40, 100, 14);

        txtpagar2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtpagar2MouseClicked(evt);
            }
        });
        txtpagar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpagar2ActionPerformed(evt);
            }
        });
        jPanel14.add(txtpagar2);
        txtpagar2.setBounds(10, 60, 100, 20);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/edit.png"))); // NOI18N
        jButton4.setText("Finalizar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/guardar.jpg"))); // NOI18N
        jButton16.setText("Guardar");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton16)
                            .addComponent(jButton4))))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel26)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addComponent(jButton16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton4)
                        .addContainerGap())
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pago.addTab("Tarjeta de Credito", jPanel7);

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(0, 0, 255));
        jLabel30.setText("Respuesta de Venta");

        jPanel16.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel16.setOpaque(false);
        jPanel16.setLayout(null);

        jPanel17.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel17.setOpaque(false);
        jPanel17.setLayout(null);

        jTextField6.setText("$0.00");
        jPanel17.add(jTextField6);
        jTextField6.setBounds(270, 10, 100, 20);

        txtnombre6.setText("name");
        txtnombre6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnombre6ActionPerformed(evt);
            }
        });
        jPanel17.add(txtnombre6);
        txtnombre6.setBounds(20, 10, 140, 20);

        txtcantidad6.setText("1");
        txtcantidad6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcantidad6ActionPerformed(evt);
            }
        });
        jPanel17.add(txtcantidad6);
        txtcantidad6.setBounds(170, 10, 90, 20);

        jPanel16.add(jPanel17);
        jPanel17.setBounds(0, 0, 0, 0);

        jLabel32.setText("Id Crediticio:");
        jPanel16.add(jLabel32);
        jLabel32.setBounds(20, 10, 70, 14);

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/find.png"))); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel16.add(jButton6);
        jButton6.setBounds(260, 10, 49, 20);

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/new.png"))); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel16.add(jButton7);
        jButton7.setBounds(310, 10, 49, 20);

        credii.setEditable(false);
        credii.setText("000000001");
        credii.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                crediiMouseClicked(evt);
            }
        });
        jPanel16.add(credii);
        credii.setBounds(90, 10, 140, 20);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/edit.png"))); // NOI18N
        jButton5.setText("Guardar y Finalizar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel33.setText("Cliente con Credito?");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel30)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel33)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(0, 16, Short.MAX_VALUE)
                        .addComponent(jButton5)))
                .addContainerGap())
        );

        pago.addTab("Credito propio", jPanel8);

        jPanel6.add(pago);
        pago.setBounds(180, 0, 430, 140);

        jLabel35.setForeground(new java.awt.Color(255, 0, 51));
        jLabel35.setText("LLENE LOS DATOS...!");
        jPanel6.add(jLabel35);
        jLabel35.setBounds(20, 10, 150, 20);

        jLabel4.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel4.setText("REGISTRO DE VENTAS");

        jLabel5.setText("FERRETERIA METALPAR");

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/LOGOS.jpg"))); // NOI18N

        jLabel12.setText("RUC: 1309999999");

        jLabel15.setText("Direccion: Parque del niño");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setText("Datos de Venta");

        jPanel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel10.setOpaque(false);
        jPanel10.setLayout(null);

        txtvuelto1.setText("0");
        txtvuelto1.setEnabled(false);
        jPanel10.add(txtvuelto1);
        txtvuelto1.setBounds(130, 40, 100, 20);

        jPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel11.setOpaque(false);
        jPanel11.setLayout(null);

        jTextField4.setText("$0.00");
        jPanel11.add(jTextField4);
        jTextField4.setBounds(270, 10, 100, 20);

        txtnombre4.setText("name");
        txtnombre4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnombre4ActionPerformed(evt);
            }
        });
        jPanel11.add(txtnombre4);
        txtnombre4.setBounds(20, 10, 140, 20);

        txtcantidad4.setText("1");
        txtcantidad4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcantidad4ActionPerformed(evt);
            }
        });
        jPanel11.add(txtcantidad4);
        txtcantidad4.setBounds(170, 10, 90, 20);

        jPanel10.add(jPanel11);
        jPanel11.setBounds(0, 0, 0, 0);

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setText("Id Venta");
        jPanel10.add(jLabel18);
        jLabel18.setBounds(10, 40, 120, 14);

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Pepito Patea Trasero (cajero)");
        jPanel10.add(jLabel19);
        jLabel19.setBounds(290, 10, 250, 14);

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel20.setText("Tipo de Documento");
        jPanel10.add(jLabel20);
        jLabel20.setBounds(260, 40, 120, 14);

        jLabel6.setText("->>");
        jPanel10.add(jLabel6);
        jLabel6.setBounds(230, 10, 34, 14);

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel21.setText("Id Vendedor");
        jPanel10.add(jLabel21);
        jLabel21.setBounds(10, 10, 120, 14);

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel22.setText("Id Vendedor");
        jPanel10.add(jLabel22);
        jLabel22.setBounds(10, 10, 120, 14);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nota de Venta", "Factura" }));
        jComboBox1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBox1MouseClicked(evt);
            }
        });
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jPanel10.add(jComboBox1);
        jComboBox1.setBounds(390, 40, 190, 20);

        jTextField1.setEditable(false);
        jTextField1.setForeground(new java.awt.Color(0, 0, 102));
        jTextField1.setText("0");
        jTextField1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField1MouseClicked(evt);
            }
        });
        jPanel10.add(jTextField1);
        jTextField1.setBounds(130, 10, 100, 20);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel5)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(440, Short.MAX_VALUE))
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel12)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel15)))))
        );

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/new.png"))); // NOI18N
        jButton8.setText("Nuevo");
        jButton8.setBorderPainted(false);
        jButton8.setContentAreaFilled(false);
        jButton8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton8.setIconTextGap(1);
        jButton8.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/press.png"))); // NOI18N
        jButton8.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jButton8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/print.png"))); // NOI18N
        jButton10.setText("(H) Imprimir");
        jButton10.setBorderPainted(false);
        jButton10.setContentAreaFilled(false);
        jButton10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton10.setIconTextGap(1);
        jButton10.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/press.png"))); // NOI18N
        jButton10.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jButton10.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/ayuda.png"))); // NOI18N
        jButton11.setText("Ayuda");
        jButton11.setBorderPainted(false);
        jButton11.setContentAreaFilled(false);
        jButton11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton11.setIconTextGap(1);
        jButton11.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/press.png"))); // NOI18N
        jButton11.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jButton11.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/del.png"))); // NOI18N
        jButton13.setText("Cerrar");
        jButton13.setBorderPainted(false);
        jButton13.setContentAreaFilled(false);
        jButton13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton13.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton13.setIconTextGap(1);
        jButton13.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/press.png"))); // NOI18N
        jButton13.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jButton13.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jButton10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
            .addComponent(jButton11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 228, Short.MAX_VALUE)
                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7))
        );

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Datos de Generales");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(102, 684, Short.MAX_VALUE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(1, 1, 1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtivaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtivaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtivaActionPerformed

    private void txtdescActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdescActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdescActionPerformed

    private void txtsubtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsubtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsubtActionPerformed

    private void txttotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttotalActionPerformed

    private void txtcantidad1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcantidad1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcantidad1ActionPerformed

    private void txtnombre1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnombre1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnombre1ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
if(mensajeconfirmacion("Crear Nuevo registro de Ventas")){
    interfazRealizaVenta a= new interfazRealizaVenta();
    principal.panel.add(a);
    a.setVisible(true);
    dispose();}
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
generarreporte();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void txtnombre4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnombre4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnombre4ActionPerformed

    private void txtcantidad4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcantidad4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcantidad4ActionPerformed

    private void clienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clienMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_clienMouseClicked

    private void jComboBox1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox1MouseClicked
   
// TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1MouseClicked

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton13ActionPerformed

    private void cmdCerrar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCerrar2ActionPerformed
if(tabla.getRowCount()>=1)
{pago.setVisible(true);}
else{JOptionPane.showMessageDialog(rootPane, "Debe Ingresar productos y cliente");}
    }//GEN-LAST:event_cmdCerrar2ActionPerformed

    private void txtpagarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtpagarMouseClicked
        txtpagar.setText("");
    }//GEN-LAST:event_txtpagarMouseClicked

    private void txtpagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpagarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpagarActionPerformed

    private void txtnombre3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnombre3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnombre3ActionPerformed

    private void txtcantidad3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcantidad3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcantidad3ActionPerformed

    private void txtpagar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtpagar1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpagar1MouseClicked

    private void txtpagar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpagar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpagar1ActionPerformed

    private void txtnombre5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnombre5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnombre5ActionPerformed

    private void txtcantidad5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcantidad5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcantidad5ActionPerformed

    private void txtpagar2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtpagar2MouseClicked
txtpagar2.setText(txttotal.getText());
// TODO add your handling code here:
    }//GEN-LAST:event_txtpagar2MouseClicked

    private void txtpagar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpagar2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpagar2ActionPerformed

    private void txtnombre6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnombre6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnombre6ActionPerformed

    private void txtcantidad6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcantidad6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcantidad6ActionPerformed

    private void crediiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_crediiMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_crediiMouseClicked

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void clienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_clienActionPerformed

    private void txtvuelto6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtvuelto6MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtvuelto6MouseClicked

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        if(jComboBox1.getSelectedItem().equals("Factura"))
        {jLabel23.setText("RUC");
            panelfactura.setVisible(true);
        }else
        {jLabel23.setText("C.I.:");
            panelfactura.setVisible(false);}         // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
      setdeltabla();  // TODO add your handling code here:
    }//GEN-LAST:event_deleteActionPerformed

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
     setaddtabla();  
    }//GEN-LAST:event_addActionPerformed

    
    private void tablaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaKeyReleased
    if(evt.getKeyCode() == KeyEvent.VK_ENTER){ 
busquedaproducto();
}
    }//GEN-LAST:event_tablaKeyReleased

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
 busquedaproducto();         // TODO add your handling code here:
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        setdeltabla();// TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        setaddtabla();       // TODO add your handling code here:
    }//GEN-LAST:event_jButton14ActionPerformed

    private void buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarActionPerformed
busquedaproducto();        // TODO add your handling code here:
    }//GEN-LAST:event_buscarActionPerformed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed

    }//GEN-LAST:event_formKeyPressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
   //abre la interfaz producto*****************************************************
    interfazIngresarCliente a= new interfazIngresarCliente();
    if(estacerrado(a))
    {delete.enable(true);jButton9.enable(true);cmdCerrar2.setVisible(true);principal.panel.add(a);
    a.toFront();
    a.setVisible(true);
    //empieza codigo de llamadas
    a.cedula.setText("");
    a.cedula.requestFocusInWindow();
    }else{JOptionPane.showMessageDialog(this,"La ventana ya esta abierta...");}  
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField1MouseClicked
if(evt.getClickCount()==2)
{
}        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1MouseClicked

    private void txtdescKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdescKeyReleased
if(evt.getKeyCode()==KeyEvent.VK_ENTER){//aplicamos descuento con un enter de 0 a 100
    Double desc1= Double.parseDouble(txtdesc.getText());
    if(desc1<=70)
    {Double desc= (Double.parseDouble(txtdesc.getText())/100);
    Double total=Math.rint(( (Double.parseDouble(txttotal.getText())-(   (Double.parseDouble(txtsubt.getText())+Double.parseDouble(txtiva.getText()))   *desc))  )*100)/100;
    txttotal.setText(""+total);}
    else{JOptionPane.showMessageDialog(rootPane, "lo siento descuento no puede ser mayor a 70");}
}// TODO add your handling code here:
    }//GEN-LAST:event_txtdescKeyReleased

    private void txtdescMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtdescMouseClicked
txtdesc.setText("");        // TODO add your handling code here:
    }//GEN-LAST:event_txtdescMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
 registrarventa(11);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
registrarventa(22);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
 //abre la interfaz producto*****************************************************
  buscarcreditocliente();       // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
   //abre la interfaz producto*****************************************************
   buscarcliente();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
 //abre la interfaz producto*****************************************************
    CreditoCliente a= new CreditoCliente();
    if(estacerrado(a))
    {delete.enable(true);jButton9.enable(true);cmdCerrar2.setVisible(true);principal.panel.add(a);
    a.toFront();
    a.setVisible(true);
    //empieza codigo de llamadas
    a.cedula.setText("");
    a.cedula.requestFocusInWindow();
    }else{JOptionPane.showMessageDialog(this,"La ventana ya esta abierta...");}         // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
 registrarventa(1);
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
registrarventa(2);
      
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
jButton16ActionPerformed(evt);
jButton4ActionPerformed(evt);// TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed
    public boolean estacerrado(JInternalFrame obj){
    JInternalFrame[] activos=principal.panel.getAllFrames();
    boolean cerrado=true;
    int i=0;
    while (i<activos.length && cerrado){                  
	if(activos[i].getTitle()==obj.getTitle()){                      
		cerrado=false;
	}
	i++;
    }
    return cerrado;}
    public boolean mensajeconfirmacion(String mensaje){
Object [] opciones ={"Aceptar","Cancelar"};
int eleccion = JOptionPane.showOptionDialog(rootPane,"En realidad desea "+ mensaje +"?","Mensaje de Confirmacion",
JOptionPane.YES_NO_OPTION,
JOptionPane.QUESTION_MESSAGE,null,opciones,"Aceptar");
if (eleccion == JOptionPane.YES_OPTION)
{
return true;
}return false;}
    public static void subt(Double var){
        Double subt= Math.rint((Double.parseDouble(txtsubt.getText())   +var )*100)/100;
        Double iva=Math.rint(( subt*0.12 )*100)/100;
        Double total=Math.rint((subt+iva)*100)/100;
      txtsubt.setText(""+subt);
      txtiva.setText(""+iva);
      txttotal.setText(""+total);
    }  
    public void llenauser()
    {String cadenaAnalizar=principal.usuario.getText();
     Character caracter = null; 
     String a="",b="",sii="si";
    for (int i = 0; i< cadenaAnalizar.length(); i++) {        
        caracter = cadenaAnalizar.charAt(i);
        if(caracter.toString().equals("-")){caracter=cadenaAnalizar.charAt(i-1);sii="no";}
        if(sii.equals("si")){a=a+cadenaAnalizar.charAt(i);}
        if(sii.equals("no")){b=b+caracter;}
    }
jLabel19.setText(a);
jTextField1.setText(b);
    } 
    public void actualizastock(String id,String cant){
        String cpp="";
        Conexion cc=new Conexion();
        Connection cn= cc.getConnection();
        Statement st=null;
        ResultSet rs;
        try {
            st=(Statement) cn.createStatement();
            rs=st.executeQuery("select * from producto where ID_PRODUCTO='"+id+"'");
            if(rs.next())
            {  
                cpp=rs.getString("producto.CANTIDAD");

            } else {
                JOptionPane.showMessageDialog(this, "El cliente no esta registrado, REGISTRELO");
         
            }
        } catch (SQLException ex) {
            Logger.getLogger(interfazIngresarCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        cpp=""+(Double.parseDouble(cpp)-Double.parseDouble(cant));
                       
              try{
            PreparedStatement pstm = cn.prepareStatement("UPDATE PRODUCTO SET CANTIDAD='"+cpp
             +"' WHERE ID_PRODUCTO='"+id+"'");
            
            pstm.execute();
            pstm.close();
            JOptionPane.showMessageDialog(this, "Cantidad modificada");
           } catch(SQLException e)
           {
            JOptionPane.showMessageDialog(this, "Cantidad no modificada");
           }
}
        public void setaddtabla(){
        if(tabla.getRowCount()==0){DefaultTableModel dtm = (DefaultTableModel) tabla.getModel();
        String datos[]={"","","","","",""};
        dtm.addRow(datos);}else{
        String a=(String)tabla.getValueAt(tabla.getRowCount()-1 ,0);
        String b=(String)tabla.getValueAt(tabla.getRowCount()-1 ,1);
        if(a==null||b==null)
        {JOptionPane.showMessageDialog(this,"Debe Ingresar el producto y cantidad deseada...");
        }else{DefaultTableModel dtm = (DefaultTableModel) tabla.getModel();
        String datos[]={"","","","","",""};
        dtm.addRow(datos); delete.enable(true);jButton9.enable(true);}  }
    }
    public void setdeltabla(){
        String a=(String)tabla.getValueAt(tabla.getSelectedRow(),tabla.getSelectedColumn());
      if(mensajeconfirmacion(("Eliminar el producto "+a))){
      DefaultTableModel dtm = (DefaultTableModel) tabla.getModel(); //TableProducto es el nombre de mi tabla ;) 
      dtm.removeRow(tabla.getSelectedRow());
      tabla.transferFocus();}
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem add;
    private javax.swing.JMenuItem buscar;
    public static javax.swing.JTextField clien;
    public static javax.swing.JLabel clienc;
    private javax.swing.JButton cmdCerrar2;
    public static javax.swing.JTextField credii;
    private javax.swing.JMenuItem delete;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    public javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JPopupMenu menutablas;
    private javax.swing.JTabbedPane pago;
    private javax.swing.JPanel panelfactura;
    public static javax.swing.JTable tabla;
    private javax.swing.JTextField txtcantidad1;
    private javax.swing.JTextField txtcantidad3;
    private javax.swing.JTextField txtcantidad4;
    private javax.swing.JTextField txtcantidad5;
    private javax.swing.JTextField txtcantidad6;
    private javax.swing.JTextField txtdesc;
    public static javax.swing.JTextField txtiva;
    private javax.swing.JTextField txtnombre1;
    private javax.swing.JTextField txtnombre3;
    private javax.swing.JTextField txtnombre4;
    private javax.swing.JTextField txtnombre5;
    private javax.swing.JTextField txtnombre6;
    private javax.swing.JTextField txtpagar;
    private javax.swing.JTextField txtpagar1;
    private javax.swing.JTextField txtpagar2;
    public static javax.swing.JTextField txtsubt;
    public static javax.swing.JTextField txttotal;
    private javax.swing.JTextField txtvuelto;
    private javax.swing.JTextField txtvuelto1;
    private javax.swing.JTextField txtvuelto4;
    private javax.swing.JTextField txtvuelto6;
    // End of variables declaration//GEN-END:variables
public static void capturar (String nombre){
nombre= clien.getText();
    
}
}
