/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package historias_clinicas;

import Vistas.PaginaPrincipal;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author matia
 */
public class Historias_Clinicas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
try {
        // OPCIÓN 1: Estilo clásico (Metal)
        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());

        // OPCIÓN 2: Estilo del sistema
        // UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

    } catch (Exception e) {
        e.printStackTrace();
    }

    SwingUtilities.invokeLater(() -> {
        PaginaPrincipal pp = new PaginaPrincipal();
        pp.setLocationRelativeTo(null);
        pp.pack();
        pp.setVisible(true);
    });
}
   
        
  
}
        
    
    

