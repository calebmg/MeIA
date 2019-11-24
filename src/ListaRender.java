/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Component;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
/**
 *
 * @author Boris Moran
 */
public class ListaRender extends JLabel implements ListCellRenderer{
    public String path = "C:\\Users\\Boris Moran\\Desktop\\Planton.jpg";
    final public ImageIcon imagen = new ImageIcon(path);
    
    
    
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        String valor = value.toString();
        setText(valor);
        if (!valor.equals("")) {
            setIcon(imagen);
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


