/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebServiceTarea2.util;

/**
 *
 * @author Jose Pablo Bermudez
 */
public class CampoException {
    
    /**
     * Este método permite recibir por parámetros un Mensaje de una {@code SQLIntegrityConstraintViolationException} para obtener el campo que fue violado según el {@code Constrain} 
     * en base de datos.
     * 
     * Ejemplo: 
     *      String campo = getCampo("java.sql.SQLIntegrityConstraintViolationException: ORA-00001: unique constraint (UNA.ADN_CORREO) violated",
     *                              "UNA", "adn");
     *      "campo = {CORREO}"
     * 
     * @param textoSQLException Mensaje de la Excepcion (Excepcion ex; ex.getCause().getCause().getMessage(); "java.sql.SQLIntegrityConstraintViolationException: ORA-00001: unique constraint (UNA.ADN_CORREO) violated")
     * @param schemaName Esquema en la que están generadas las entidades en base de datos. Ejemplo = "UNA"
     * @param estandarAtrivuto Estandar con el que inicia el atrivuto de la tabla. Ejemplo = el nombre del atrivuto de la entidad es adn_correo por ende; el estandar será "adn" 
     * @return correo
     */
    public static String getCampo(String textoSQLException, String schemaName, String estandarAtrivuto){
        String campo = "";
        for(Integer i = 0; i<textoSQLException.length(); i++){
            if(textoSQLException.charAt(i) == '('){
                for(Integer j = i+(schemaName.length() + estandarAtrivuto.length()+ 3); j < textoSQLException.length(); j++){
                    if (textoSQLException.charAt(j) == ')') break;
                    else campo += textoSQLException.charAt(j);
                }
                break;
            }
        }
        return campo;
    }
}
