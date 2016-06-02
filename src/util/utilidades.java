/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Developer
 */
public class utilidades {
    public static boolean checkEmail (String email) {

    // Establecer el patron
    Pattern p = Pattern.compile("[-\\w\\.]+@[\\.\\w]+\\.\\w+");

    // Asociar el string al patron
    Matcher m = p.matcher(email);

   // Comprobar si encaja
   return m.matches();

}
}
