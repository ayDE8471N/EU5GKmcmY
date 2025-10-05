// 代码生成时间: 2025-10-06 03:42:20
import org.apache.struts2.convention.annotation.Action;
import com.opensymphony.xwork2.ActionSupport;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * DigitalSignatureTool class provides functionality to generate a digital signature.
 * It uses Java's built-in cryptographic APIs to generate key pairs and sign messages.
 */
public class DigitalSignatureTool extends ActionSupport {

    // Generates a key pair for digital signature
    private KeyPair keyPair;

    // Private constructor method to generate key pair
    public DigitalSignatureTool() {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(2048);
            this.keyPair = keyGen.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            // Log the exception and set an error message
            addActionError("Error generating key pair: " + e.getMessage());
        }
    }

    // Method to sign a message
    public String signMessage(String message) {
        try {
            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initSign(this.keyPair.getPrivate());
            signature.update(message.getBytes());

            byte[] signedBytes = signature.sign();
            return Base64.getEncoder().encodeToString(signedBytes);
        } catch (Exception e) {
            // Log the exception and set an error message
            addActionError("Error signing message: " + e.getMessage());
            return null;
        }
    }

    // Getters and setters for keyPair
    public KeyPair getKeyPair() {
        return keyPair;
    }

    public void setKeyPair(KeyPair keyPair) {
        this.keyPair = keyPair;
    }

    // Action method to handle the digital signature process
    @Action(value = "/digitalSignature")
    public String execute() {
        String message = "This is a test message for digital signature.";
        String signature = signMessage(message);
        addActionMessage("Signed message: " + signature);
        return SUCCESS;
    }
}
