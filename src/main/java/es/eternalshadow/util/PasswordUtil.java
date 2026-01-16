package es.eternalshadow.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordUtil {
    
    /**
     * Hashea una contraseña usando SHA-256.
     * @param password Contraseña a hashear.
     * @return Hash SHA-256 de la contraseña.
     */
    public String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            
            // Convertir bytes a hexadecimal
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al hashear la contraseña", e);
        }
    }
    
    /**
     * Verifica si una contraseña introducida coincide con la almacenada.
     * @param introducida Contraseña introducida por el usuario.
     * @param guardada Hash de la contraseña almacenada.
     * @return true si coinciden, false en caso contrario.
     */
    public boolean isPassword(String introducida, String guardada) {
        if (introducida == null || guardada == null) {
            return false;
        }
        String passwordHasheada = hashPassword(introducida);
        return passwordHasheada.equals(guardada);
    }
    
    /**
     * Genera un salt aleatorio para mayor seguridad.
     * @return Salt en Base64.
     */
    public String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }
    
    /**
     * Hashea una contraseña con salt.
     * @param password Contraseña.
     * @param salt Salt.
     * @return Hash con salt.
     */
    public String hashPasswordWithSalt(String password, String salt) {
        String saltedPassword = password + salt;
        return hashPassword(saltedPassword);
    }
    
    /**
     * Verifica una contraseña con salt.
     * @param password Contraseña a verificar.
     * @param storedHash Hash almacenado.
     * @param salt Salt almacenado.
     * @return true si coinciden.
     */
    public boolean verifyPasswordWithSalt(String password, String storedHash, String salt) {
        String hashToVerify = hashPasswordWithSalt(password, salt);
        return hashToVerify.equals(storedHash);
    }
    
    /**
     * Genera una contraseña aleatoria segura.
     * @param length Longitud de la contraseña.
     * @return Contraseña aleatoria.
     */
    public String generateRandomPassword(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(chars.length());
            sb.append(chars.charAt(index));
        }
        
        return sb.toString();
    }
}