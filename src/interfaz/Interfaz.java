
package interfaz;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
 

/**
 *
 * @author Administrador
 */
public class Interfaz {

    /**
     * @param args the command line arguments
     */
    static ArrayList<String> app;
     public static void main(String[] args) {
      frmLogin fr=new frmLogin();
      fr.show(); 
            
    }	 
}
