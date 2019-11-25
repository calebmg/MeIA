/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Component;
import java.io.File;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
/**
 *
 * @author Boris Moran
 */
public class ListaRender extends JLabel implements ListCellRenderer{
    
    
    public String pathBasura = "C:\\MEIA\\Fotografia\\Basura.jpg";
    final public ImageIcon imagenB = new ImageIcon(pathBasura);
    
    public String pathBotellas = "C:\\MEIA\\Fotografia\\Botellas.jpg";
    final public ImageIcon imagenBo = new ImageIcon(pathBotellas);
    
    public String pathIron = "C:\\MEIA\\Fotografia\\Metal.jpg";
    final public ImageIcon imagenIron = new ImageIcon(pathIron);
    
    public String pathPapel = "C:\\MEIA\\Fotografia\\Papel.jpg";
    final public ImageIcon imagenP = new ImageIcon(pathPapel);
    
    public String pathRopa = "C:\\MEIA\\Fotografia\\Ropa.jpg";
    final public ImageIcon imagenR = new ImageIcon(pathRopa);
    
    public String pathVidrio = "C:\\MEIA\\Fotografia\\Ventana.jpg";
    final public ImageIcon imagenV = new ImageIcon(pathVidrio);
    
    
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        String valor = value.toString();
        setText(valor);
        if (valor.equals("Basura")) {
            setIcon(imagenB);
        }
        else if (valor.equals("Botellas")) {
            setIcon(imagenBo);
        }
        else if (valor.equals("Metal")) {
            setIcon(imagenIron);
        }
        else if (valor.equals("Papel")) {
            setIcon(imagenP);
        }
        else if (valor.equals("Ropa")) {
            setIcon(imagenR);
        }
        else if (valor.equals("Ventana")) {
            setIcon(imagenV);
        }
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        }
        else
        {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
        setEnabled(list.isEnabled());
        setFont(list.getFont());
        setOpaque(true);
        return this;
    }
    
}



