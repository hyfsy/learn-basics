package basics.util;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author baB_hyf
 * @date 2021/07/14
 */
public class EncodeUtils {

    public static final String ALGORITHM_RSA         = "RSA";
    public static final String ALGORITHM_RS256       = "SHA256withRSA";
    public static final String ALGORITHM_SHA256      = "SHA-256";
    public static final String ALGORITHM_HMAC_SHA256 = "HmacSHA256";

    public static String sha256(String str) {
        try {
            MessageDigest messageDigest;
            messageDigest = MessageDigest.getInstance(ALGORITHM_SHA256);
            messageDigest.update(str.getBytes(StandardCharsets.UTF_8));
            return byte2Hex(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 encrypt fail", e);
        }
    }

    /**
     * 同时处理base64正常的，但在url上不能存在的特殊字符，如 =，+，/
     */
    public static String base64UrlEncode(String str) {
        byte[] bytes = Base64.encodeBase64URLSafe(str.getBytes(StandardCharsets.UTF_8));
        return new String(bytes, StandardCharsets.UTF_8);
    }

    public static String base64UrlDecode(String str) {
        byte[] bytes = Base64.decodeBase64(str.getBytes(StandardCharsets.UTF_8));
        return new String(bytes, StandardCharsets.UTF_8);
    }

    public static String rsa(String str, String privateKey) {
        try {
            byte[] bytes = Base64.decodeBase64(privateKey);
            PrivateKey priKey = KeyFactory.getInstance(ALGORITHM_RSA).generatePrivate(new PKCS8EncodedKeySpec(bytes));
            Cipher cipher = Cipher.getInstance(ALGORITHM_RSA);
            cipher.init(Cipher.ENCRYPT_MODE, priKey);
            return Base64.encodeBase64String(cipher.doFinal(str.getBytes(StandardCharsets.UTF_8)));
        } catch (InvalidKeySpecException | NoSuchAlgorithmException | NoSuchPaddingException
                | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            throw new RuntimeException("Rsa encrypt fail", e);
        }
    }

    public static String rsa_public(String str, String publicKey) {
        try {
            byte[] bytes = Base64.decodeBase64(publicKey);
            PublicKey pubKey = KeyFactory.getInstance(ALGORITHM_RSA).generatePublic(new X509EncodedKeySpec(bytes));
            Cipher cipher = Cipher.getInstance(ALGORITHM_RSA);
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);
            return Base64.encodeBase64String(cipher.doFinal(str.getBytes(StandardCharsets.UTF_8)));
        } catch (InvalidKeySpecException | NoSuchAlgorithmException | NoSuchPaddingException
                | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            throw new RuntimeException("Rsa encrypt fail", e);
        }
    }

    public static String rsaDecrypt(String str, String publicKey) {
        try {
            byte[] strBytes = Base64.decodeBase64(str.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = Base64.decodeBase64(publicKey);
            PublicKey pubKey = KeyFactory.getInstance(ALGORITHM_RSA).generatePublic(new X509EncodedKeySpec(bytes));
            Cipher cipher = Cipher.getInstance(ALGORITHM_RSA);
            cipher.init(Cipher.DECRYPT_MODE, pubKey);
            return new String(cipher.doFinal(strBytes));
        } catch (InvalidKeySpecException | NoSuchAlgorithmException | NoSuchPaddingException
                | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            throw new RuntimeException("Rsa decrypt fail", e);
        }
    }

    public static String rsaDecrypt_private(String str, String privateKey) {
        try {
            byte[] strBytes = Base64.decodeBase64(str.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = Base64.decodeBase64(privateKey);
            PrivateKey priKey = KeyFactory.getInstance(ALGORITHM_RSA).generatePrivate(new PKCS8EncodedKeySpec(bytes));
            Cipher cipher = Cipher.getInstance(ALGORITHM_RSA);
            cipher.init(Cipher.DECRYPT_MODE, priKey);
            return new String(cipher.doFinal(strBytes));
        } catch (InvalidKeySpecException | NoSuchAlgorithmException | NoSuchPaddingException
                | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            throw new RuntimeException("Rsa decrypt fail", e);
        }
    }

    public static Pair createRSAKeyPair() {
        try {
            int keyLength = 1024;
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM_RSA);
            keyPairGenerator.initialize(keyLength, new SecureRandom());
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            return new Pair(keyPair);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Create rsa key pair fail", e);
        }
    }

    public static String hs256(String str, String secret) {
        try {
            Mac sha256_HMAC = Mac.getInstance(ALGORITHM_HMAC_SHA256); // 简称HS256
            SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), ALGORITHM_HMAC_SHA256);
            sha256_HMAC.init(secret_key);
            byte[] array = sha256_HMAC.doFinal(str.getBytes(StandardCharsets.UTF_8));
            return byte2Hex(array);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException("HmacSHA256 encrypt fail", e);
        }
    }

    public static String rs256(String str, PrivateKey privateKey) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(ALGORITHM_SHA256);
            messageDigest.update(str.getBytes(StandardCharsets.UTF_8));
            byte[] digest = messageDigest.digest();
            Signature signature = Signature.getInstance(ALGORITHM_RS256);
            signature.initSign(privateKey);
            signature.update(digest);
            return Base64.encodeBase64String(signature.sign());
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            e.printStackTrace();
            throw new RuntimeException("Rs256 encrypt fail", e);
        }
    }

    public static boolean rs256Verify(String str, String signed, PublicKey publicKey) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(ALGORITHM_SHA256);
            messageDigest.update(str.getBytes(StandardCharsets.UTF_8));
            byte[] digest = messageDigest.digest();
            Signature verifySign = Signature.getInstance(ALGORITHM_RS256);
            verifySign.initVerify(publicKey);
            verifySign.update(digest);
            return verifySign.verify(Base64.decodeBase64(signed.getBytes(StandardCharsets.UTF_8)));
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            e.printStackTrace();
            return false;
        }
    }

    // byte转换成16进制
    public static String byte2Hex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(Integer.toHexString((b & 0xFF) | 0x100), 1, 3);
        }
        return sb.toString();
    }

    // 生成 2048 位（不是 256 位）的 RSA 密钥
    // openssl genrsa -out rsa-private-key.pem 2048
    // 通过密钥生成公钥
    // openssl rsa -in rsa-private-key.pem -pubout -out rsa-public-key.pem
    public static String readKey(String filePath) {
        try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            if (is == null) {
                throw new IllegalArgumentException("Cannot find file: " + filePath);
            }

            int len;
            byte[] bytes = new byte[1024];
            while ((len = is.read(bytes)) != -1) {
                baos.write(bytes, 0, len);
            }

            return baos.toString();
        } catch (IOException e) {
            throw new RuntimeException("Read file error");
        }
    }

    public static class Pair {

        private final PublicKey  originPublicKey;
        private final PrivateKey originPrivateKey;
        private final String     publicKey;
        private final String     privateKey;

        public Pair(KeyPair keyPair) {
            this.originPublicKey = keyPair.getPublic();
            this.originPrivateKey = keyPair.getPrivate();
            this.publicKey = Base64.encodeBase64String(originPublicKey.getEncoded());
            this.privateKey = Base64.encodeBase64String(originPrivateKey.getEncoded());
        }

        public String getPublicKey() {
            return publicKey;
        }

        public String getPrivateKey() {
            return privateKey;
        }

        public PublicKey getOriginPublicKey() {
            return originPublicKey;
        }

        public PrivateKey getOriginPrivateKey() {
            return originPrivateKey;
        }
    }
}
