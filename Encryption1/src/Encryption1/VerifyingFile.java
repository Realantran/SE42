/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Encryption1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.PublicKey;
import java.security.Signature;

/**
 *
 * @author AntonTran
 */
public class VerifyingFile {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("INPUT(SignedByAnton)"));
            int signatureLenth = ois.readInt();
            byte[] bytesSignature = (byte[]) ois.readObject();
            String text = (String) ois.readObject();
            Signature signature = Signature.getInstance("SHA1withRSA");
            signature.initVerify(readPublicKey());
            if(signature.verify(bytesSignature)){
                System.out.println("Signature is valid!");
            }
            else{
                System.out.println("Signature is invalid!");
            }

        } catch (Exception ex) {

        }
    }
    public static PublicKey readPublicKey() {
        PublicKey privateKey = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Public.txt"));
            privateKey = (PublicKey) ois.readObject();
            ois.close();
        } catch (FileNotFoundException | ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return privateKey;
    }
}
