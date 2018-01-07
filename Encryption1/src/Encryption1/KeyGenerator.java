/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Encryption1;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.*;

/**
 *
 * @author AntonTran
 */
public class KeyGenerator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            SecureRandom secureRandom = new SecureRandom();
            keyPairGenerator.initialize(1024, secureRandom);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            writePrivateKey(keyPair.getPrivate());
            writePublicKey(keyPair.getPublic());

        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
    }

    public static void writePrivateKey(PrivateKey privateKey) {
        try {
            FileOutputStream fis = new FileOutputStream("Private.txt");
            ObjectOutputStream outPrivate = new ObjectOutputStream(fis);
            outPrivate.writeObject(privateKey);
            outPrivate.close();
            fis.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void writePublicKey(PublicKey publicKey) {
        try {
            FileOutputStream fis = new FileOutputStream("Public.txt");
            ObjectOutputStream outPublic = new ObjectOutputStream(fis);
            outPublic.writeObject(publicKey);
            outPublic.close();
            fis.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
