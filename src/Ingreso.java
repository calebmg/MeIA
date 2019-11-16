
import java.io.File;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.security.Key;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;//
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import java.io.File;
import java.io.IOException;
import java.io.*;
import java.time.*;
import java.util.ArrayList;
import  java.util.*;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Ingreso extends javax.swing.JFrame {

    public Ingreso() {
        initComponents();
         jLabel3.setIcon(new ImageIcon("C:\\MEIA\\Fotografia\\green file.jpg"));
        this.setLocationRelativeTo(null);
    }
    public static String Usuario ="";
    public static String Nombre = "";
    public static String Apellido ="";
    public static String Contraseña = "";
    public static String Dia ="";
    public static String Mes = "";
    public static String Año ="";
    public static String Correo = "";
    public static String Telefono ="";
    public static String Descripcion = "";
    public static String Ruta ="";
    
    private static final String ALGO = "AES";
    @SuppressWarnings("unchecked")
    private byte[] keyValue;
    
    public void AESLlave(String llave)
    {
        keyValue = llave.getBytes();
    }
    
    private Key generarLlave() throws Exception
    {
        Key key = new SecretKeySpec(keyValue, ALGO);
        return key;
    }
    
    public String Codificar(String contraseña) throws Exception
    {
        Key key = generarLlave();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(contraseña.getBytes());
        String contraseñaEncriptada = new BASE64Encoder().encode(encVal);
        return contraseñaEncriptada;
    }
    
    public String Decodificar(String contraseñaEncriptada) throws Exception
    {
        Key key = generarLlave();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decordedValue = new BASE64Decoder().decodeBuffer(contraseñaEncriptada);
        byte[] decValue = c.doFinal(decordedValue);
        String ContraseñaDescifrada = new String(decValue);
        return ContraseñaDescifrada;
    }
    
    public void Inicializar() throws IOException
    {
        //Crear las carpetas para el proyecto
        String carpeta = "C:\\MEIA\\Fotografia";
        File crear = new File(carpeta);
        
       
        crear.mkdirs();
        String carpetaSeguridad = "C:\\MEIA\\ArchivosSeguridad";
        File crear2 = new File(carpetaSeguridad);
        crear2.mkdirs();
        //Crear los archivos necesarios para el login si no estan creados ya
        
        String pathPuntuacion = "C:\\MeIA\\ArchivosSeguridad\\puntuacion.txt";
        File archivoPuntuacion = new File(pathPuntuacion);
        BufferedWriter writer3;
        if(archivoPuntuacion.exists()){}
        else
        {
            writer3 = new BufferedWriter(new FileWriter(archivoPuntuacion));
            writer3.write("6");
            writer3.write("\n3");
            writer3.write("\n2");
            writer3.write("\n1");
            writer3.write("\n2");
            writer3.write("\n4");
            writer3.write("\n6");
            writer3.write("\n3");
            writer3.close();
        }
        String pathResultado = "C:\\MeIA\\ArchivosSeguridad\\resultado.txt";
        File archivoResultado = new File(pathResultado);
        BufferedWriter writer4;
        if(archivoResultado.exists()){}
        else
        {
            writer4 = new BufferedWriter(new FileWriter(archivoResultado));
            writer4.write("0,20");
            writer4.write("\n21,30");
            writer4.write("\n31,45");
            writer4.write("\n46,100");
            writer4.close();
        }
    }
    public Usuario Obtener_Usuario(String strPath,String strError,String user)
    {
        Usuario Usuarios = new Usuario();
        File Archivo = new File(strPath);
        
        if(Archivo.exists()==true)
        {
            FileReader LecturaArchivo;
            
            try {
                LecturaArchivo = new FileReader(Archivo);
                
                BufferedReader LeerArchivo = new BufferedReader(LecturaArchivo);
                
                String Linea="";
                try {
                    Linea=LeerArchivo.readLine();
                    String[] Split;
                    
                    
                    while(Linea != null)
                    {
                        if(!"".equals(Linea))
                        {
                            Split=Linea.split(",");
                            Usuario User = new Usuario();
                            User.usuario = Split[0];
                            User.nombre = Split[1];
                            User.apellido = Split[2];
                            User.password = Split[3];
                            User.rol = Split[4];
                            User.Fecha_Nacimiento = Split[5];
                            User.correo_electronico = Split[6];
                            User.telefono = Split[7];
                            User.path_foto = Split[8];
                            User.Descripcion = Split[9];
                            User.status =  Split[10];                           
                            if(User.usuario.equals(user))
                            {
                                return User;
                            }
                        }
                        Linea=LeerArchivo.readLine();
                    }
                    //Aqui ya se puede hacer comparaciones

                    LecturaArchivo.close();
                    LeerArchivo.close();
                    
                    strError="";
                    return null;
                    
                } catch (IOException ex) {
                    strError= ex.getMessage();
                    return null;
                }
            } catch (FileNotFoundException ex) {
                strError= ex.getMessage();
                return null;
            }            
        }
        else
        {
            
            return null;
        }
    }
    public String Obtener_Linea(String strPath,String strError,String user)
    {
        Usuario Usuarios = new Usuario();
        File Archivo = new File(strPath);
        
        if(Archivo.exists()==true)
        {
            FileReader LecturaArchivo;
            
            try {
                LecturaArchivo = new FileReader(Archivo);
                
                BufferedReader LeerArchivo = new BufferedReader(LecturaArchivo);
                
                String Linea="";
                try {
                    Linea=LeerArchivo.readLine();
                    String[] Split;
                    
                    
                    while(Linea != null)
                    {
                        if(!"".equals(Linea))
                        {
                            Split=Linea.split(",");
                            Usuario User = new Usuario();
                            User.usuario = Split[0];
                            User.nombre = Split[1];
                            User.apellido = Split[2];
                            User.password = Split[3];
                            User.rol = Split[4];
                            User.Fecha_Nacimiento = Split[5];
                            User.correo_electronico = Split[6];
                            User.telefono = Split[7];
                            User.path_foto = Split[8];
                            User.Descripcion = Split[9];
                            User.status =  Split[10];                           
                            if(User.usuario.equals(user))
                            {
                                return Linea;
                            }
                        }
                        Linea=LeerArchivo.readLine();
                    }
                    //Aqui ya se puede hacer comparaciones

                    LecturaArchivo.close();
                    LeerArchivo.close();
                    
                    strError="";
                    return "";
                    
                } catch (IOException ex) {
                    strError= ex.getMessage();
                    return "";
                }
            } catch (FileNotFoundException ex) {
                strError= ex.getMessage();
                return "";
            }            
        }
        else
        {
            
            return "";
        }
    }
     public ArrayList<Usuario> Obtener_Datos_(String strPath,String strError)
    {
        ArrayList<Usuario> Usuarios = new ArrayList<Usuario>();
        File Archivo = new File(strPath);
        
        if(Archivo.exists()==true)
        {
            FileReader LecturaArchivo;
            
            try {
                LecturaArchivo = new FileReader(Archivo);
                
                BufferedReader LeerArchivo = new BufferedReader(LecturaArchivo);
                
                String Linea="";
                try {
                    Linea=LeerArchivo.readLine();
                    String[] Split;
                    
                    
                    while(Linea != null)
                    {
                        if(!"".equals(Linea))
                        {
                            Split=Linea.split(",");
                            Usuario user = new Usuario();
                            user.usuario = Split[0];
                            user.nombre = Split[1];
                            user.apellido = Split[2];
                            user.password = Split[3];
                            user.rol = Split[4];
                            user.Fecha_Nacimiento = Split[5];
                            user.correo_electronico = Split[6];
                            user.telefono = Split[7];
                            user.path_foto = Split[8];
                            user.Descripcion = Split[9];
                            user.status =  Split[10];                           
                            Usuarios.add(user);
                        }
                        Linea=LeerArchivo.readLine();
                    }
                    //Aqui ya se puede hacer comparaciones

                    LecturaArchivo.close();
                    LeerArchivo.close();
                    
                    strError="";
                    return Usuarios;
                    
                } catch (IOException ex) {
                    strError= ex.getMessage();
                    return Usuarios;
                }
            } catch (FileNotFoundException ex) {
                strError= ex.getMessage();
                return Usuarios;
            }            
        }
        else
        {
            
            return Usuarios;
        }
    }
     public boolean BusquedaUsuario(String user)
    {
        File archivo = new File("C:\\MEIA\\Bitacora.txt","");
        File archivo2 = new File("C:\\MEIA\\Usuario.txt");
        
        if(archivo.exists() && !archivo2.exists())
        {
             ArrayList<Usuario> Index = Obtener_Datos_("C:\\MEIA\\Bitacora.txt","");
      
        if(!Index.isEmpty())
        {
            
            for (Usuario item : Index) 
                 {
                     if(item.usuario.equals(user))
                     {
                         return true;
                         
                     }
                 }
          
        }
        }else if(archivo.exists() && archivo2.exists())
        {
              ArrayList<Usuario> Archivo = Obtener_Datos_("C:\\MEIA\\Usuario.txt","");
               ArrayList<Usuario> Index = Obtener_Datos_("C:\\MEIA\\Bitacora.txt","");
        if(!Index.isEmpty()||!Archivo.isEmpty())
        {
            
            for (Usuario item : Index) 
                 {
                     if(item.usuario.equals(user))
                     {
                         return true;
                         
                     }
                 }
             for (Usuario item : Archivo) 
                 {
                     if(item.usuario.equals(user))
                     {
                         return true;
                         
                     }
                 }
          
        }
          
        }
        return false;
    }
      public boolean BusquedaContraseña(String Contraseña) 
    {
        File archivo = new File("C:\\MEIA\\Bitacora.txt","");
        File archivo2 = new File("C:\\MEIA\\Usuario.txt");
        String des = "";
        try {
            //Llave debe de ser 16 caracteres
            AESLlave("1234567891234567");
            
            
            
        
        
        if(archivo.exists() && !archivo2.exists())
        {
             ArrayList<Usuario> Index = Obtener_Datos_("C:\\MEIA\\Bitacora.txt","");
      
        if(!Index.isEmpty())
        {
            
            for (Usuario item : Index) 
                 {
                     des = Decodificar(item.password);
                     if(des.equals(Contraseña))
                     {
                         return true;
                         
                     }
                 }
          
        }
        }else if(archivo.exists() && archivo2.exists())
        {
              ArrayList<Usuario> Archivo = Obtener_Datos_("C:\\MEIA\\Usuario.txt","");
               ArrayList<Usuario> Index = Obtener_Datos_("C:\\MEIA\\Bitacora.txt","");
        if(!Index.isEmpty()||!Archivo.isEmpty())
        {
            
            for (Usuario item : Index) 
                 { 
                     
                     des = Decodificar(item.password);
                     if(des.equals(Contraseña))
                     {
                         return true;
                         
                     }
                 }
             for (Usuario item : Archivo) 
                 {
                     if(item.password.equals(Contraseña))
                     {
                         return true;
                         
                     }
                 }
          
        }
          
        }
        return false;
    }
        
      catch (Exception ex) {
            Logger.getLogger(Ingreso.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
  
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jLabel1 = new javax.swing.JLabel();
        TextoContraseña = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        TextoUsuario = new javax.swing.JTextField();
        BTNIngreso = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Usuario");

        jLabel2.setText("Contraseña");

        BTNIngreso.setText("Ingresar");
        BTNIngreso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNIngresoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BTNIngreso))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TextoContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TextoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)))
                .addGap(61, 61, 61))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(150, 150, 150))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TextoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TextoContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BTNIngreso)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BTNIngresoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNIngresoActionPerformed
        File file = new File("C:\\MEIA\\Bitacora.txt");
        File file2 = new File("C:\\MEIA\\Usuario.txt");
        try {
            Inicializar();
            try{
                ArchivoSecuencialIndexado h = new ArchivoSecuencialIndexado();
                h.Inicializar("Guita");
            }catch(IOException ex){}
        } catch (IOException ex) {
            Logger.getLogger(Ingreso.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        if(!file.exists())
        {
            Insertar verFormulario = new Insertar();
            verFormulario.setVisible(true);
           
                    
        }
        else
        {
            
            if(BusquedaContraseña(TextoContraseña.getText()) && BusquedaUsuario(TextoUsuario.getText()))
            {
             
                Usuario Index = Obtener_Usuario("C:\\MEIA\\Bitacora.txt","",TextoUsuario.getText());
                Usuario Index2 = Obtener_Usuario("C:\\MEIA\\Usuario.txt","",TextoUsuario.getText());
                Usuario Envio = new Usuario();
                String Direccion ="";
                if(Index2!=null)
                {
                    Envio = Index2;
                    Direccion ="C:\\MEIA\\Usuario.txt";
                }
                else
                {
                    Envio =Index;
                     Direccion ="C:\\MEIA\\Bitacora.txt";
                }
                 String x = Obtener_Linea(Direccion,"",TextoUsuario.getText());
                    String[] div;
                    div = x.split(",");
                   
                    Usuario = TextoUsuario.getText();
                    Nombre = div[1];
                    Apellido = div[2];
                     AESLlave("1234567891234567");
             String des = "";
            try {
               des = Decodificar(div[3]);
        } catch (Exception ex) {
               Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
                    Contraseña = des;
                    String[] dividir = div[5].split(" ");
                    Dia = dividir[0];
                    Mes = dividir[1];
                    Año = dividir[2];
                    Correo = div[8];
                    Telefono = div[6];
                    Ruta = div[7];
                    Descripcion = div[9];
                
                if(Envio.rol.equals("Administrador"))
                {
                    Admin H = new Admin();
                   
                    
                   H.setVisible(true);
                    this.setVisible(false);
                   
                }
                else
                {   
                     User H = new User();
                    
                    
                    H.setVisible(true);
                    this.setVisible(false);
                   
                }    
                
             
          
            }
            else{
             JOptionPane.showMessageDialog(null, "Contraseña o usuario incorrecto");   
            }
            
        }
    }//GEN-LAST:event_BTNIngresoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Ingreso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ingreso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ingreso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ingreso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ingreso().setVisible(true);
            }
        });
    }
    public class Usuario {
    String nombre;
    String usuario;
    String apellido;
    String password;
    String Fecha_Nacimiento;
    String rol;
    String correo_electronico;
    String telefono;
    String path_foto;
    String Descripcion;
    String status;
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTNIngreso;
    private javax.swing.JTextField TextoContraseña;
    private javax.swing.JTextField TextoUsuario;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}
