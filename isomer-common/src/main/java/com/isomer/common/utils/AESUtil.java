package com.isomer.common.utils;

import com.isomer.common.constant.EncryptionConstant;
import com.isomer.common.exception.AESException;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

/**
 * Description:
 *
 * @author likain
 * @since 2023/8/7 20:30
 */
public class AESUtil {

    public static String encrypt(String data, String key) throws AESException {
        return doAES(data, key, Cipher.ENCRYPT_MODE);
    }

    public static String decrypt(String data, String key) throws AESException {
        return doAES(data, key, Cipher.DECRYPT_MODE);
    }

    private static String doAES(String data, String key, int mode) throws AESException {
        if (StringUtil.isNullOrEmpty(data) || StringUtil.isNullOrEmpty(key)) {
            throw new AESException("Data and key should not be null or empty");
        }

        try {
            boolean encrypt = mode == Cipher.ENCRYPT_MODE;
            byte[] content;

            if (encrypt) {
                content = data.getBytes();
            } else {
                content = Base64.decodeBase64(data);
            }

            KeyGenerator kGen = KeyGenerator.getInstance(EncryptionConstant.ALGORITHM);
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(key.getBytes());
            kGen.init(128, random);

            SecretKey secretKey = kGen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec keySpec = new SecretKeySpec(enCodeFormat, EncryptionConstant.ALGORITHM);
            Cipher cipher = Cipher.getInstance(EncryptionConstant.ALGORITHM);
            cipher.init(mode, keySpec);
            byte[] result = cipher.doFinal(content);

            if (encrypt) {
                return new String(Base64.encodeBase64(result));
            } else {
                return new String(result);
            }
        } catch (Throwable t) {
            String opsName = mode == Cipher.ENCRYPT_MODE ? "encryption" : "decryption";
            throw new AESException("Error occurred when " + opsName, t);
        }
    }
}
