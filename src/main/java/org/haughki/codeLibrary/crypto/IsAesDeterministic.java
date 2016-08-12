package org.haughki.codeLibrary.crypto;

import java.security.InvalidKeyException;
import javax.crypto.*;

public class IsAesDeterministic {

    public static void main(String[] args) throws Exception {
        KeyGenerator KeyGen = KeyGenerator.getInstance("AES");
        KeyGen.init(128);

        SecretKey SecKey = KeyGen.generateKey();

        Cipher AesCipher1 = Cipher.getInstance("AES");
        Cipher AesCipher2 = Cipher.getInstance("AES");


        byte[] byteText = "Your Plain Text Here".getBytes();

        // encrypting the same plain text with the same secret is deterministic with AES
        String firstEncrypt = aesEncrypt(SecKey, AesCipher1, byteText);
        String secondEncrypt = aesEncrypt(SecKey, AesCipher2, byteText);

        System.out.println(firstEncrypt);
        System.out.println(secondEncrypt);
        assert(firstEncrypt == secondEncrypt);
    }

    private static String aesEncrypt(SecretKey secKey, Cipher aesCipher, byte[] byteText) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        aesCipher.init(Cipher.ENCRYPT_MODE, secKey);
        byte[] byteCipherText = aesCipher.doFinal(byteText);

        StringBuilder cipherTextAsHex = new StringBuilder();
        for(byte b : byteCipherText) {
            cipherTextAsHex.append(getHexForByte(b));
        }
        return cipherTextAsHex.toString();
    }

    private static String getHexForByte(int i) {
        String hex = String.format("%2s", Integer.toHexString(i)).replace(' ', '0');
        if (hex.length() > 2) {
            hex = hex.substring(hex.length() - 2);
        }
        return String.format("%s", hex);
    }    
}