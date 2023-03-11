package com.example;
import java.io.FileWriter;
import java.io.IOException;
import java.security.*;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {
    private static final Logger LOG = Logger.getLogger(App.class.getName());

    public static void main(String[] args) throws NoSuchAlgorithmException {
        // Generating RSA Public and Private keys
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        // Saving RSA Public and Private keys
        saveKey(publicKey, "id_rsa.pub", "rsa public key");
        LOG.info(String.format("Public key format: %s", publicKey.getFormat()));

        saveKey(privateKey, "id_rsa.key", "rsa private key");
        LOG.info(String.format("Private key format: %s", privateKey.getFormat()));
    }

    private static void saveKey(Key key, String fileName, String header) {
        Base64.Encoder encoder = Base64.getEncoder();
        final String FORMAT = "----%s %s----";
        try (FileWriter out = new FileWriter(fileName)) {
            out.write(String.format(FORMAT, "BEGIN", header.toUpperCase()));
            out.write("\n");

            out.write(encoder.encodeToString(key.getEncoded()));
            out.write("\n");

            out.write(String.format(FORMAT, "END", header.toUpperCase()));
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

}
