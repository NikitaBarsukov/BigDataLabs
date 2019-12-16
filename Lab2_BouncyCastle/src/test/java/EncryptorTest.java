import org.bouncycastle.cms.CMSException;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Security;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

public class EncryptorTest {

   private static PrivateKey      privateKey;
   private static X509Certificate certificate;

   @BeforeClass
   public static void setup() throws Exception {
      Security.setProperty("crypto.policy", "unlimited");
      int maxKeySize = javax.crypto.Cipher.getMaxAllowedKeyLength("AES");
      System.out.println("Max Key Size for AES : " + maxKeySize);

      Security.addProvider(new BouncyCastleProvider());
      CertificateFactory certFactory = CertificateFactory
              .getInstance("X.509", "BC");
      certificate = (X509Certificate) certFactory
              .generateCertificate(new FileInputStream("C:\\Users\\Nikita\\Documents\\work\\BouncyCastle\\src\\main\\resources\\public.cer"));
      char[] keystorePassword = "password".toCharArray();
      char[] keyPassword = "password".toCharArray();
      KeyStore keystore = KeyStore.getInstance("PKCS12");
      keystore.load(new FileInputStream("C:\\Users\\Nikita\\Documents\\work\\BouncyCastle\\src\\main\\resources\\private.p12"), keystorePassword);
      privateKey = (PrivateKey) keystore.getKey("baeldung",
              keyPassword);
   }

   @Test
   public void encrypt() throws CMSException, IOException, CertificateEncodingException {
      Encryptor encryptor = new Encryptor();
      String secretMessage = "My password is 123456Seven";
      System.out.println("Original Message : " + secretMessage);
      byte[] stringToEncrypt = secretMessage.getBytes();
      byte[] encryptedData = encryptor.encryptData(stringToEncrypt, certificate);
      System.out.println("Encrypted Message : ");
      System.out.println(new String(encryptedData));
      byte[] rawData = encryptor.decryptData(encryptedData, privateKey);
      String decryptedMessage = new String(rawData);
      System.out.println("Decrypted Message : " + decryptedMessage);
   }

   @Test
   public void sign() throws Exception {
      Encryptor encryptor = new Encryptor();
      byte[] rawData = "My password is 123456Seven".getBytes();
      byte[] signedData = encryptor.signData(rawData, certificate, privateKey);
      Boolean check = Encryptor.verifySignedData(signedData);
      System.out.println(check);
   }
}