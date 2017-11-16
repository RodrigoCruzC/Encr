package com.bolivia.crypto;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
/**
 * @web http://www.jc-mouse.net/
 * @author Mouse
 */
public class Encriptacion {

    private SecretKey key;   
    private String skey="";    
    private Cipher desCipher;

     /**
 * Constrcutor de clase
 */
    public Encriptacion(){}

    /**
 * Crea la Llave para encriptar/desencriptar
 * @param String value
 */
    public void addKey( String value ){
        try {
            skey = value;
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest( value.getBytes("utf-8") );  
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);  
            key = new SecretKeySpec(keyBytes, "DESede");
        } catch (NoSuchAlgorithmException ex) {
            System.err.println( ex.getMessage() );
        } catch (UnsupportedEncodingException ex) {
            System.err.println( ex.getMessage() );
        }
    }

    public String getsKey(){
        return skey;
    }

    /**
 * Metodo para encriptar un texto
 * @param String texto
 * @return String texto encriptado
 */
    public String encrypt( String texto ){
        String value="";
        try {
            desCipher = Cipher.getInstance("DESede");
             //inicia el Cipher para la encriptacion
            desCipher.init( Cipher.ENCRYPT_MODE, key );             
            byte[] byteDataToEncrypt = texto.getBytes();
            byte[] byteCipherText = desCipher.doFinal(byteDataToEncrypt);             
            value = new BASE64Encoder().encode( byteCipherText );
        } catch (NoSuchAlgorithmException ex) {
            System.err.println( ex.getMessage() );
        } catch (NoSuchPaddingException ex) {
            System.err.println( ex.getMessage() );
        } catch (InvalidKeyException ex) {
            System.err.println( ex.getMessage() );
        } catch (IllegalBlockSizeException ex) {
            System.err.println( ex.getMessage() );
        } catch (BadPaddingException ex) {
            System.err.println( ex.getMessage() );
        }
        return value;
    }

    /**
 * Metodo para desencriptar un texto
 * @param texto Texto encriptado
 * @return String texto desencriptado
 */
    public String decrypt( String texto ){
        String strDecryptedText="";
        byte[] value;
        try {
            value = new BASE64Decoder().decodeBuffer(texto);

            desCipher = Cipher.getInstance("DESede");
            desCipher.init( Cipher.DECRYPT_MODE, key, desCipher.getParameters() );                                  
            byte[] byteDecryptedText = desCipher.doFinal( value );
            strDecryptedText = new String(byteDecryptedText);                                  
        } catch (InvalidKeyException ex) {
            System.err.println( ex.getMessage() );
        }  catch (IllegalBlockSizeException ex) {
            System.err.println( ex.getMessage() );
        } catch (BadPaddingException ex) {
            System.err.println( ex.getMessage() );
            JOptionPane.showMessageDialog( null , "La contraseña es incorrecta." );
        }   catch (IOException ex) {
            System.err.println( ex.getMessage() );
        } catch (InvalidAlgorithmParameterException ex) {
            System.err.println( ex.getMessage() );
        } catch (NoSuchAlgorithmException ex) {
            System.err.println( ex.getMessage() );
        } catch (NoSuchPaddingException ex) {
            System.err.println( ex.getMessage() );
        }
        return strDecryptedText;
    }

}