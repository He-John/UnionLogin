package com.ky.vmk;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.nio.file.Files;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class SecurityUtils {
    private Cipher cipher;

    public SecurityUtils() throws NoSuchAlgorithmException, NoSuchPaddingException {
        this.cipher = Cipher.getInstance("RSA");
    }

    public PrivateKey getPrivateKey(String textBase64) throws Exception {
        byte[] keyBytes = Base64.decodeBase64(textBase64);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(spec);
    }

    public PublicKey getPublicKey(String textBase64) throws Exception {
        byte[] keyBytes = Base64.decodeBase64(textBase64);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(spec);
    }


    public String encryptText(String msg, PrivateKey key) throws UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        this.cipher.init(Cipher.ENCRYPT_MODE, key);
        return Base64.encodeBase64String(cipher.doFinal(msg.getBytes("UTF-8")));
    }


    public String decryptText(String msg, PublicKey key) throws InvalidKeyException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
        this.cipher.init(Cipher.DECRYPT_MODE, key);
        return new String(cipher.doFinal(Base64.decodeBase64(msg)), "UTF-8");
    }

}
