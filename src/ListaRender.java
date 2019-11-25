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
    public Materiales fMat = new Materiales();
    public ArrayList<Materiales.NodoMat> Listado = fMat.ListadoNodos("C:\\MEIA\\Materiales.txt","");
    
    
    
    
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        String valor = value.toString();
        setText(valor);
        for (Materiales.NodoMat item : Listado) {
            if (valor.equals(item.Nombre)) {
                ImageIcon ima = new ImageIcon(item.RFoto);
                setIcon(ima);
            }
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



