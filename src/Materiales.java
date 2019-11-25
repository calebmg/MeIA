/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.Key;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.nio.file.*;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import java.io.File;
import java.io.IOException;
import java.io.*;

import java.net.URL;
import java.time.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Marcelo Rosales
 */
public class Materiales extends javax.swing.JFrame {

    private Object Text;

    /**
     * Creates new form Materiales
     */
    DefaultListModel x = new DefaultListModel();
    public Materiales() {
        File Archivo = new File("C:\\MEIA\\Materiales.txt");
        
       
        initComponents();
         
        jList1.setModel(x);
        
        if(Archivo.exists()==true)
        {
        ArrayList<NodoMat> LNodos = ListadoNodos("C:\\MEIA\\Materiales.txt","");
        
        for(NodoMat item : LNodos)
        {
            x.addElement(item.Nombre);
        }
        }
    }
 public int Cantidad_Max(String strPath,String strError)
    {
        int cant_max = 0;
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
                    String[] split;
                    
                    
                    while(Linea != null)
                    {
                        if(!"".equals(Linea))
                        {
                            split=Linea.split(":");
                            if(split[0].equals("Maximo_Registros"))
                            {
                                cant_max = Integer.parseInt(split[1]);
                                
                            }
                        }
                        Linea=LeerArchivo.readLine();
                    }
                    //Aqui ya se puede hacer comparaciones

                    LecturaArchivo.close();
                    LeerArchivo.close();
                    
                    strError="a";
                    return cant_max;
                    
                } catch (IOException ex) {
                    strError= ex.getMessage();
                    return cant_max;
                }
            } catch (FileNotFoundException ex) {
                strError= ex.getMessage();
                return cant_max;
            }            
        }
        else
        {
            
            return cant_max;
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
                            user.nombre = Split[0];
                            user.tipo = Split[1];
                            user.path_foto = Split[2];
                            user.degradarse = Split[3];
                            user.usuario = Split[4];
                            user.Fecha_Creacion = Split[5];
                           user.status= Split[6];
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
    
    public class NodoMat{
        public int Posicion;
        public int PIzq;
        public int PDer;
        public NodoMat Izquierdo;
        public NodoMat Derecho;
        public String Nombre;
        public String Tipo;
        public String RFoto;
        public String Tiempo;
        public String Usuario;
        public String Fecha;
        public String Status;
        
        
        public boolean EsHoja(){
            if((Izquierdo == null) && (Derecho == null)){return true;}
            else {return false;}
        }
        
        public void LlenarNodoMat(String Props){
            String[] Separados = Props.split(",");
            this.Posicion = Integer.parseInt(Separados[0]);
            this.PIzq = Integer.parseInt(Separados[1]);
            this.PDer = Integer.parseInt(Separados[2]);
            this.Nombre = Separados[3];
            this.Tipo = Separados[4];
            this.RFoto = Separados[5];
            this.Tiempo = Separados[6];
            this.Usuario = Separados[7];
            this.Fecha = Separados[8];
            this.Status = Separados[9];
        }
    }
    
    
    public ArrayList<NodoMat> ListadoNodos(String strPath,String strError)
    {
        ArrayList<NodoMat> Listado = new ArrayList<NodoMat>();
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
                            NodoMat Material = new NodoMat();
                            Material.LlenarNodoMat(Linea);
                            Listado.add(Material);
                        }
                        Linea=LeerArchivo.readLine();
                    }
                    //Aqui ya se puede hacer comparaciones

                    LecturaArchivo.close();
                    LeerArchivo.close();
                    
                    strError="";
                    return Listado;
                    
                } catch (IOException ex) {
                    strError= ex.getMessage();
                    return Listado;
                }
            } catch (FileNotFoundException ex) {
                strError= ex.getMessage();
                return Listado;
                
            }            
        }
        else
        {
            
            return Listado;
        }
    }
    public List<String> BuscarSecuencial() throws IOException
    {
        List<String> Respuesta = new ArrayList<>();
        try{
                FileReader fr = new FileReader("C:\\MEIA\\BitacoraMateriales.txt");
                BufferedReader br = new BufferedReader(fr);
                String linea;
                while ((linea = br.readLine()) != null) {
                    String[] Campos = linea.split(",");
                    Respuesta.add(Campos[0]);
                }
                FileReader fR = new FileReader("C:\\MEIA\\Materiales.txt");
                BufferedReader bR = new BufferedReader(fR);
                String lineas;
                while ((lineas = bR.readLine()) != null) {
                    String[] Campos = lineas.split(",");
                    Respuesta.add(Campos[0]);
                }
                br.close();
                fr.close();
                
                bR.close();
                fR.close();
            }catch(IOException ex){}
        if(Respuesta.size() == 0)
        {
            Respuesta.add("404");
        }
        return Respuesta;
    }
    
    public boolean Insertar()
    {
        ArrayList<Usuario> Index = Obtener_Datos_("C:\\MEIA\\BitacoraMateriales.txt","");
        int max = Cantidad_Max("C:\\MEIA\\Desc_BitacoraMaterials.txt","");
        if(Index.size()<max)
        {
            return true;
        }
        else 
        {
            return false;
        }
        
    }
    public boolean Busqueda(String user)
    {
        File archivo = new File("C:\\MEIA\\BitacoraMateriales.txt","");
        File archivo2 = new File("C:\\MEIA\\Materiales.txt");
        
        if(archivo.exists() && !archivo2.exists())
        {
             ArrayList<Usuario> Index = Obtener_Datos_("C:\\MEIA\\BitacoraMateriales.txt","");
      
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
              ArrayList<Usuario> Archivo = Obtener_Datos_("C:\\MEIA\\Materiales.txt","");
               ArrayList<Usuario> Index = Obtener_Datos_("C:\\MEIA\\BitacoraMateriales.txt","");
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
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TextoUsuario = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        DireccionFotograficaLabel = new javax.swing.JLabel();
        ComboDia = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        ComboMes = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        Comboaño = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        TextStatus = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        BotonInsertar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        TextMaterial = new javax.swing.JTextField();
        TextTipo = new javax.swing.JTextField();
        TextDeg = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jButton3 = new javax.swing.JButton();
        BuscarTF = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        BuscadoTF = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        TextoUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextoUsuarioActionPerformed(evt);
            }
        });

        jLabel13.setText("Material");

        DireccionFotograficaLabel.setText("\"");

        ComboDia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        ComboDia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboDiaActionPerformed(evt);
            }
        });

        jLabel6.setText("Mes");

        ComboMes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        ComboMes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboMesActionPerformed(evt);
            }
        });

        jLabel7.setText("Fecha de creacion:");

        Comboaño.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980", "1979", "1978", "1977", "1976", "1975", "1974", "1973", "1972", "1971", "1970", "1969", "1968", "1967", "1966", "1965", "1964", "1963", "1962", "1961", "1960", "1959", "1958", "1957", "1956", "1955", "1954", "1953", "1952", "1951", "1950", "1949", "1948", "1947", "1946", "1945", "1944", "1943", "1942", "1941", "1940", "1939", "1938", "1937", "1936", "1935", "1934", "1933", "1932", "1931", "1930", " " }));

        jLabel8.setText("Año");

        jLabel9.setText("0 No usado, 1 En uso");

        TextStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextStatusActionPerformed(evt);
            }
        });

        jLabel2.setText("Tipo");

        jLabel3.setText("Tiempo");

        jLabel4.setText("Usuario de creacion");

        jLabel5.setText("Dia");

        BotonInsertar.setText("Insertar");
        BotonInsertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonInsertarActionPerformed(evt);
            }
        });

        jButton1.setText("Foto");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel14.setText("Direccion Fotografia:");

        TextMaterial.setToolTipText("");

        jButton2.setText("Modificar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList1ValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        jButton3.setText("Buscar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel1.setText("Buscar por nombre");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(TextTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(TextMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(55, 55, 55)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(TextDeg, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(14, 14, 14))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(128, 128, 128)
                                .addComponent(BotonInsertar)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(TextoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DireccionFotograficaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ComboDia, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ComboMes, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Comboaño, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 642, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jButton2)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(TextStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(BuscarTF, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(jButton3)
                                .addGap(36, 36, 36)
                                .addComponent(BuscadoTF, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(180, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jButton1)
                            .addComponent(TextMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TextTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TextDeg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(TextoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ComboDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ComboMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Comboaño, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TextStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BotonInsertar)
                            .addComponent(jButton2))
                        .addGap(11, 11, 11))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(DireccionFotograficaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(43, 43, 43)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)))
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BuscarTF, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3)
                    .addComponent(BuscadoTF))
                .addGap(113, 113, 113))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TextoUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextoUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextoUsuarioActionPerformed

    private void ComboDiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboDiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboDiaActionPerformed

    private void ComboMesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboMesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboMesActionPerformed

    public boolean moveFile(String fromFile, String toFile) {
        File origin = new File(fromFile);
        File destination = new File(toFile);
        if (origin.exists()) {
            try {
                InputStream in = new FileInputStream(origin);
                OutputStream out = new FileOutputStream(destination);
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                in.close();
                out.close();
                return origin.delete();
            } catch (IOException ioe) {
                ioe.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }
    
    private void BotonInsertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonInsertarActionPerformed
        Usuario user= new Usuario();
        user.nombre = TextMaterial.getText();
     
        Date fecha = new Date();
        File file = new File("C:\\MEIA\\Materiales.txt");
        File fdesc = new File("C:\\MEIA\\Desc_Materiales.txt");
        FileWriter escritor;
        PrintWriter imprimir;
        String origenPath = DireccionFotograficaLabel.getText();
        Path origen = Paths.get(origenPath);
        Path destino = Paths.get("C:\\MEIA\\Fotografia");
        try {
            Files.move(origen, destino.resolve(origen.getFileName()));
        } catch (IOException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Foto
        String pathFoto = "C:\\MEIA\\Fotografia\\" + origen.getFileName();
        NodoMat NMat = new NodoMat();
        NMat.Nombre = TextMaterial.getText();  NMat.Tipo = TextTipo.getText();    NMat.RFoto = pathFoto;
        NMat.Tiempo = TextDeg.getText();    NMat.Usuario = TextoUsuario.getText();
        NMat.Fecha = ComboDia.getSelectedItem()+" "+ComboMes.getSelectedItem()+" "+ Comboaño.getSelectedItem();
        NMat.Status = TextStatus.getText(); NMat.PIzq = -1; NMat.PDer = -1;
        
        //SI MATERIALES.TXT NO EXISTE  
        if(!file.exists())
        {
            try{
                //Para insertar en Materiales.txt
                file.createNewFile();
                escritor = new FileWriter(file,true);
                imprimir = new PrintWriter(escritor,true);
                imprimir.println("0,-1,-1," + TextMaterial.getText()+","+TextTipo.getText()+","+pathFoto+"," +TextDeg.getText()+","+TextoUsuario.getText()+","+
                        ComboDia.getSelectedItem()+" "+ComboMes.getSelectedItem()+" "+
                        Comboaño.getSelectedItem()+","+TextStatus.getText());
                
                //Creacion de descriptor
                File fileDesc = new File("C:\\MEIA\\Desc_Materiales.txt");
                FileWriter escritor2 = new FileWriter(fileDesc,true);
                PrintWriter linea = new PrintWriter(escritor2);
                
                linea.println("Nombre:Materiales.txt");
                linea.println("Fecha_Modificacion:"+fecha);
                linea.println("Creador:"+TextoUsuario.getText());
                linea.println("Total_Registros:1");
                linea.println("Registros_Activos:1");
                linea.println("Registros_Inactivos:0");
                
                imprimir.close();
                escritor2.close();
                linea.close();
                escritor.close();
                
            }catch(IOException ex){ex.printStackTrace();}
            
        }//MATERIALES.TXT SI EXISTE
        else{
            ArrayList<NodoMat> ListadoViejo = ListadoNodos("C:\\MEIA\\Materiales.txt","");
            int PNuevo = ListadoViejo.size();
            if(!ListadoViejo.contains(NMat)){//Si no contiene nuevo
                int Posicion = 0;
                int Ultima = 0;
                while(Posicion >= 0){
                    if( NMat.Nombre.compareTo(ListadoViejo.get(Posicion).Nombre) < 0 ){ //NMat es menor a actual
                        Ultima = Posicion;
                        Posicion = ListadoViejo.get(Posicion).PIzq;
                    }else if (NMat.Nombre.compareTo(ListadoViejo.get(Posicion).Nombre) > 0){
                        Ultima = Posicion;
                        Posicion = ListadoViejo.get(Posicion).PDer;
                    }
                }
                if(NMat.Nombre.compareTo(ListadoViejo.get(Ultima).Nombre) < 0) //Nuevo es menor a ultima
                { ListadoViejo.get(Ultima).PIzq = PNuevo; }
                else if(NMat.Nombre.compareTo(ListadoViejo.get(Ultima).Nombre) > 0)
                { ListadoViejo.get(Ultima).PDer = PNuevo; }
                NMat.Posicion = PNuevo;
                ListadoViejo.add(NMat);
             try
             {
                file.delete(); file.createNewFile();
                escritor = new FileWriter(file,true); imprimir = new PrintWriter(escritor, true);
                for(NodoMat i : ListadoViejo)
                {
                    String Props = Integer.toString(i.Posicion) + "," + Integer.toString(i.PIzq) + "," + Integer.toString(i.PDer)+ "," +
                            i.Nombre + "," + i.Tipo + "," + i.RFoto + "," + i.Tiempo + "," + i.Usuario + "," + i.Fecha + "," + i.Status;
                    imprimir.println(Props);
                }
                escritor.close(); imprimir.close();
                fdesc.delete(); fdesc.createNewFile();
                FileWriter escritor2 = new FileWriter(fdesc,true);
                PrintWriter linea = new PrintWriter(escritor2);
                linea.println("Nombre:Materiales.txt");
                linea.println("Fecha_Modificacion:"+fecha);
                linea.println("Creador:"+TextoUsuario.getText());
                linea.println("Total_Registros:" + Integer.toString(ListadoViejo.size()));
                
                escritor2.close(); linea.close();
             }catch(IOException ex){ex.printStackTrace();}
            }
        }
        TextMaterial.setText(null);
        TextoUsuario.setText(null);
        TextTipo.setText(null);
        TextDeg.setText(null);
        TextoUsuario.setText(null);
        TextStatus.setText(null);

        Admin cerrar = new Admin();
        cerrar.dispose(); //0o0
        // TODO add your handling code here:
    }//GEN-LAST:event_BotonInsertarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        JFileChooser dialogo = new JFileChooser();
        dialogo.setCurrentDirectory(new java.io.File("."));
        dialogo.setDialogTitle("choosertitle");
        dialogo.setFileSelectionMode(JFileChooser.FILES_ONLY);
        //dialogo.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        dialogo.setAcceptAllFileFilterUsed(false);
        if (dialogo.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            DireccionFotograficaLabel.setText(dialogo.getSelectedFile().getPath());
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList1ValueChanged
        // TODO add your handling code here:
        int index = jList1.getSelectedIndex();
        Usuario obj = (Usuario)x.getElementAt(index);
        TextMaterial.setText(obj.nombre);
        TextTipo.setText(obj.tipo);
        TextStatus.setText(obj.status);
        DireccionFotograficaLabel.setText(obj.path_foto);
        TextDeg.setText(obj.degradarse);
        TextoUsuario.setText(obj.usuario);
        
    }//GEN-LAST:event_jList1ValueChanged
private void ActualizarLinea(String Actualizar, String cambio, String path) throws FileNotFoundException, IOException{

    BufferedReader file = new BufferedReader(new FileReader(path));
    String Line;
    String input = "";
    while((Line = file.readLine())!=null)
    {
        input += Line +"\n";
        input = input.replace(Actualizar, cambio);
    }

    FileOutputStream os = new FileOutputStream(path);
    os.write(input.getBytes());
    file.close();
    os.close();
}
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        File Archivo = new File("C:\\MEIA\\Materiales.txt");
        NodoMat MatMod = new NodoMat();
        MatMod.Nombre = TextMaterial.getText();
       
//        initComponents();
//         
//        jList1.setModel(x);
        if(Archivo.exists()==true)
        {
        ArrayList<NodoMat> Listado = ListadoNodos("C:\\MEIA\\Materiales.txt","");
        
        for(NodoMat item :Listado)
        {
            if(item.Nombre.equals(MatMod.Nombre))
            {
                try {
                    String Viejo = Integer.toString(item.Posicion) + "," + Integer.toString(item.PIzq) + "," + Integer.toString(item.PDer) + "," + 
                            item.Nombre+","+item.Tipo+","+item.RFoto +","+item.Tiempo +"," + item.Usuario +","+item.Fecha+","+item.Status;
                    String Nuevo = Integer.toString(item.Posicion) + "," + Integer.toString(item.PIzq) + "," + Integer.toString(item.PDer) + "," +
                            TextMaterial.getText()+","+TextTipo.getText()+","+DireccionFotograficaLabel.getText()+"," +TextDeg.getText()+","+TextoUsuario.getText()+","+
                                    ComboDia.getSelectedItem()+" "+ComboMes.getSelectedItem()+" "+
                                    Comboaño.getSelectedItem()+","+TextStatus.getText();
                    ActualizarLinea(Viejo, Nuevo,"C:\\MEIA\\Materiales.txt");
                } catch (IOException ex) {
                    Logger.getLogger(Materiales.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        }
        
          TextMaterial.setText(null);
        TextoUsuario.setText(null);
        TextTipo.setText(null);
        TextDeg.setText(null);
        TextoUsuario.setText(null);
        TextStatus.setText(null);
        DireccionFotograficaLabel.setText(null);
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void TextStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextStatusActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        NodoMat Buscado = new NodoMat();
        Buscado.Nombre = BuscarTF.getText();
        ArrayList<NodoMat> Listado = ListadoNodos("C:\\MEIA\\Materiales.txt","");
        int Pencontrado = 0;
        boolean Encontrado = false;
        for(NodoMat item : Listado)
        {
            if(item.Nombre.equals(Buscado.Nombre))
            { Encontrado = true; Pencontrado = item.Posicion;}
        }
        if(Encontrado == true)
        {
            String Props = "Nombre: " +Listado.get(Pencontrado).Nombre + "\n" + Listado.get(Pencontrado).Tipo + "," +
                    Listado.get(Pencontrado).RFoto + "," + Listado.get(Pencontrado).Tiempo + "\n" + 
                    Listado.get(Pencontrado).Fecha + "," + Listado.get(Pencontrado).Status; 
            BuscadoTF.setText(Props);
        }else{
            BuscadoTF.setText("El material no esta en el listado.");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(Materiales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Materiales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Materiales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Materiales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Materiales().setVisible(true);
            }
        });
    }
public class Usuario {
    String nombre;
    String usuario;
    String tipo;
    String Fecha_Creacion;
    String path_foto;
    String status;
    String degradarse;
    @Override
    public String toString(){
        return nombre;
    }
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonInsertar;
    private javax.swing.JTextField BuscadoTF;
    private javax.swing.JTextField BuscarTF;
    public javax.swing.JComboBox<String> ComboDia;
    public javax.swing.JComboBox<String> ComboMes;
    public javax.swing.JComboBox<String> Comboaño;
    private javax.swing.JLabel DireccionFotograficaLabel;
    public javax.swing.JTextField TextDeg;
    public javax.swing.JTextField TextMaterial;
    public javax.swing.JTextField TextStatus;
    public javax.swing.JTextField TextTipo;
    public javax.swing.JTextField TextoUsuario;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    public javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
