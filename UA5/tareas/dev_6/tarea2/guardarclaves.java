import java.io.FileOutputStream;
import java.io.IOException;
import java.security.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class guardarclaves {
    private static final Logger LOG = Logger.getLogger(Sample2.class.getName());

    public static void main(String[] args) throws NoSuchAlgorithmException {
        // Generating RSA Public and Private keys
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        // Saving RSA Public and Private keys
        saveKey(publicKey, "id_bin_rsa.pub");
        LOG.info(String.format("Public key format: %s", publicKey.getFormat()));

        saveKey(privateKey, "id_bin_rsa.key");
        LOG.info(String.format("Private key format: %s", privateKey.getFormat()));
    }

    private static void saveKey(Key key, String fileName) {
        try (FileOutputStream out = new FileOutputStream(fileName)) {
            out.write(key.getEncoded());
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}
