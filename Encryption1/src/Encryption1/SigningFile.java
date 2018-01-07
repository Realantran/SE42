/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Encryption1;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import static java.nio.charset.StandardCharsets.UTF_8;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;

/**
 *
 * @author AntonTran
 */
public class SigningFile {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            String input = readInputFile();
            PrivateKey privateKey = readPrivateKey();
            Signature signature = Signature.getInstance("SHA1withRSA");
            signature.initSign(privateKey);
            byte[] bytesSignature = signature.sign();

            File file = new File("INPUT(SignedByAnton)");
            file.createNewFile();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));

            objectOutputStream.writeInt(bytesSignature.length);
            objectOutputStream.writeObject(bytesSignature);
            objectOutputStream.writeObject(input);
            objectOutputStream.close();

        } catch (IOException | InvalidKeyException | NoSuchAlgorithmException | SignatureException ex) {
            ex.printStackTrace();
        }
    }

    public static String readInputFile() {
        String input = "";
        try {
            input = new String(Files.readAllBytes(Paths.get("input.txt")), UTF_8);
            System.out.println(input);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return input;
    }

    public static PrivateKey readPrivateKey() {
        PrivateKey privateKey = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Private.txt"));
            privateKey = (PrivateKey) ois.readObject();
            ois.close();
        } catch (FileNotFoundException | ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return privateKey;
    }
}
