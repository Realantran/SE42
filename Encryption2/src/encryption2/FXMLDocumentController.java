/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package encryption2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.security.SecureRandom;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.swing.JOptionPane;

/**
 *
 * @author AntonTran
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label label;

    @FXML
    private Button encrypt;

    @FXML
    private Button decrypt;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextArea txtArea;

    private PBEParameterSpec pbeParamSpec;
    private int iterationCount = 1000;
    private byte[] salt;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void encrypt(ActionEvent event) {
        if (!txtArea.getText().isEmpty() && !txtPassword.getText().isEmpty()) {
            try {
                String content = txtArea.getText();
                char[] password = txtPassword.getText().toCharArray();

                SecretKey key = createKey(password);
                emptyPassword(password);

                byte[] cipherBytes = cipherContent(key, content);

                try {
                    FileOutputStream fos = new FileOutputStream("Encryption2");
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(salt);
                    oos.writeObject(cipherBytes);
                    oos.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                JOptionPane.showMessageDialog(null, "Encryptie gelukt!", null, JOptionPane.INFORMATION_MESSAGE);
                txtArea.clear();
                txtPassword.clear();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vul een password en text in!", null, JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @FXML
    private void decrypt(ActionEvent event) {
        
        if (!txtPassword.getText().isEmpty()) {

            char[] password = txtPassword.getText().toCharArray();

             
            byte[] eCipherbytes = null;
            SecretKey secret;
            try {
                FileInputStream ip = new FileInputStream("Encryption2");
              
                ObjectInputStream ois = new ObjectInputStream(ip);
               byte[] eSalt = (byte[]) ois.readObject();
                eCipherbytes = (byte[]) ois.readObject();
             
                if (eSalt != null) {
                    secret = getKey(password, eSalt);
                     String content = decipherContent(secret,eCipherbytes);
                    
                     if(content != null){
                         txtArea.setText(content);
                         txtPassword.clear();
                         emptyPassword(password);
                     }
                }
               

            } catch (Exception e) {
               e.printStackTrace();
            }

        }

    }

    private SecretKey createKey(char[] password) {
        SecretKey secretKey = null;
        try {
            SecureRandom secureRandom = new SecureRandom();
            salt = new byte[8];
            secureRandom.nextBytes(salt);
            pbeParamSpec = new PBEParameterSpec(salt, iterationCount);
            PBEKeySpec pbeKeySpec = new PBEKeySpec(password);
            SecretKeyFactory keyFac = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
            secretKey = keyFac.generateSecret(pbeKeySpec);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return secretKey;
    }

    private SecretKey getKey(char[] password, byte[] salt) {
        SecretKey secretKey = null;
        try {

            pbeParamSpec = new PBEParameterSpec(salt, iterationCount);
            PBEKeySpec pbeKeySpec = new PBEKeySpec(password);
            SecretKeyFactory keyFac = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
            secretKey = keyFac.generateSecret(pbeKeySpec);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return secretKey;
    }

    private byte[] cipherContent(SecretKey secretKey, String content) {
        byte[] cipherBytes = null;
        try {
            Cipher cipher = Cipher.getInstance("PBEWithMD5AndDES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, pbeParamSpec);

            cipherBytes = cipher.doFinal(content.getBytes());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return cipherBytes;
    }

    private String decipherContent(SecretKey secretKey, byte[] cipherBytes) {
        byte[] content;
        try {
            Cipher cipher = Cipher.getInstance("PBEWithMD5AndDES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, pbeParamSpec);

            content = cipher.doFinal(cipherBytes);
            return new String(content);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private void emptyPassword(char[] password) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < password.length; i++) {
            sb.append("0");
        }
        txtPassword.setText(sb.toString());
    }
}
