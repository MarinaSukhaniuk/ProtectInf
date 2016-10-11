package com.sukhaniuk.databases.configuration;


import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.*;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.*;
import java.util.Properties;


public class DatabaseConnection {
    private static final Logger log = Logger.getLogger(DatabaseConnection.class.getName());
    //private key in md5
    private static final String encryptionCode = md5("SukhaniukMarina");

    private Connection con;
    public Statement st;
    public ResultSet rs;

    /**
     * Open mysql connection and create statement
     */
    public void openConnection() {
        ClassLoader classLoader = getClass().getClassLoader();
        Properties props = new Properties();
        try (InputStream in = classLoader.getResourceAsStream("database.properties")) {
            //file input stream to bytes
            byte[] bytes = IOUtils.toByteArray(in);
            //get string by bytes
            String databasePropertiesText = new String(bytes, StandardCharsets.UTF_8);
            String decodeText = null;
            try {
                //decrypt database properties file
                decodeText = decryptText(new BASE64Decoder().decodeBuffer(databasePropertiesText));
            } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException | BadPaddingException | InvalidAlgorithmParameterException | IllegalBlockSizeException e) {
                e.printStackTrace();
            }
            // decode text to input stream
            InputStream stream = new ByteArrayInputStream(decodeText.getBytes());
            props.load(stream);
        } catch (IOException ex) {
            log.error("cannot be read properties file" + ex);
        }
        try {
            Class.forName(props.getProperty("jdbc.drivers"));
            con = DriverManager.getConnection(props.getProperty("jdbc.url"), props.getProperty("jdbc.username"), props.getProperty("jdbc.password"));
            st = con.createStatement();
        } catch (ClassNotFoundException | SQLException ex) {
            log.error("open connection error" + ex);
        }
    }

    /**
     * Get connection without statement
     *
     * @return connection
     */
    public Connection getConnection() {
        ClassLoader classLoader = getClass().getClassLoader();
        Properties props = new Properties();
        try (InputStream in = classLoader.getResourceAsStream("database.properties")) {
            //file input stream to bytes
            byte[] bytes = IOUtils.toByteArray(in);
            //get string by bytes
            String databasePropertiesText = new String(bytes, StandardCharsets.UTF_8);
            String decodeText = null;
            try {
                //decrypt database properties file
                decodeText = decryptText(new BASE64Decoder().decodeBuffer(databasePropertiesText));
            } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException | BadPaddingException | InvalidAlgorithmParameterException | IllegalBlockSizeException e) {
                e.printStackTrace();
            }
            // decode text to input stream
            InputStream stream = new ByteArrayInputStream(decodeText.getBytes());
            props.load(stream);
        } catch (IOException ex) {
            log.error("cannot be read properties file" + ex);
        }
        try {
            Class.forName(props.getProperty("jdbc.drivers"));
            con = DriverManager.getConnection(props.getProperty("jdbc.url"), props.getProperty("jdbc.username"), props.getProperty("jdbc.password"));
        } catch (ClassNotFoundException | SQLException ex) {
            log.error("open connection error" + ex);
        }
        return con;
    }

    public void closeConnection() {
        try {
            con.close();
        } catch (SQLException ex) {
            log.error("close database connection error" + ex);
        }
    }

    /**
     * Algorithm name "DES - private key,AES" / Mode / Padding - PKCS5Padding
     */
    private static final String algorithm = "DESede/ECB/PKCS5Padding";
    private static final String cipher = "DESede";

    /**
     * Crypt string
     */
    public static byte[] cryptString(String incoming) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException {
        //transform md5 hash string to bytes
        byte[] arrayBytes = encryptionCode.getBytes();
        KeySpec ks = new DESedeKeySpec(arrayBytes);
        SecretKeyFactory skf = SecretKeyFactory.getInstance(cipher);
        SecretKey secretKey = skf.generateSecret(ks);

        Cipher desCipher = Cipher.getInstance(algorithm);
        desCipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] byteDataToEncrypt = incoming.getBytes();
        return desCipher.doFinal(byteDataToEncrypt);
    }

    /**
     * Decrypt bytes
     */
    public static String decryptText(byte[] cryptedString) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException {
        byte[] arrayBytes = encryptionCode.getBytes();
        KeySpec ks = new DESedeKeySpec(arrayBytes);
        SecretKeyFactory skf = SecretKeyFactory.getInstance(cipher);
        SecretKey secretKey = skf.generateSecret(ks);
        Cipher desCipher = Cipher.getInstance(algorithm);
        desCipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] byteDecryptedText = desCipher.doFinal(cryptedString);
        String strDecryptedText = new String(byteDecryptedText);
        System.out.println(" Decrypted Text message is " + strDecryptedText);
        return strDecryptedText;
    }

    /**
     * String to md5 hash
     *
     * @param st input string
     * @return hashed string
     */
    public static String md5(String st) {
        MessageDigest messageDigest;
        byte[] digest = new byte[0];
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(st.getBytes());
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        BigInteger bigInt = new BigInteger(1, digest);
        String md5Hex = bigInt.toString(16);

        while (md5Hex.length() < 32) {
            md5Hex = "0" + md5Hex;
        }

        return md5Hex;
    }
}