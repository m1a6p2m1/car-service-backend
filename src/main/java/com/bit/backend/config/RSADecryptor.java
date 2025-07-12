package com.bit.backend.config;

import org.bouncycastle.util.io.pem.PemReader;
import javax.crypto.Cipher;
import java.io.StringReader;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class RSADecryptor {

    private static final String PRIVATE_KEY_PEM = "-----BEGIN PRIVATE KEY-----\n" +
            "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDltj+QICZr0OwH\n" +
            "ae3BMMZ+LYaU3jdcaekVrj/Dy6xUqmMtzxY9qjuOIWveY3SBfAgjWX4ZfGBIwbzW\n" +
            "6kGlRMVmUQwmxmR35vcBETa89r4jBkehzgBbb7lmD3nSL3Ffe/+BWzHtJEAJbgKq\n" +
            "SOCL/LqxYoh7m30sifkSWhhOWVET9wVJCU/u6bfQb5RBGdAGfR//3NCYkxbGlAFc\n" +
            "mWeadgQUQME1l5lwyRT1HTH8UXtFVS0IP3stDsDRtezpEjxK97jUXLhgkFY4GTL8\n" +
            "mtWW/d09pZMjFbbgcvVHkM2u9SIAQklaXZ1r9akbLe8dF42aQlenAIkjpfFJc1Bi\n" +
            "lxcyYXZLAgMBAAECggEAAxdqz5O/MKBFLOxeCMWRRk1yjDpZYGMEgggwT4BEN3d7\n" +
            "kjhBQl8yIf2NKBcKkFuM+HzR16H24cPJuNK6Q3EjfvcERNlSrNJs7JTAudZQRMRm\n" +
            "3mSZWKGuSxNWPl/pFLHkhGcnyts8CWRzoLsOWpPyJ0T+NTuoeZr5wSd5QESvdKdN\n" +
            "38LrX2IMJB42kTH58DaEbuCfILjTpLW6aZeVDCpcenosvvODNN7DKj8vY7035Wge\n" +
            "EX6uv7USoXhdeL9CUN+SeNawpGE/R2YL94BcHCBBGW7LXUwHsE1YHSeiXF+rnirW\n" +
            "iNX9MwRpAxsbgvodjIS6oKtxm6bUvbf1uVTCswLPEQKBgQD/K3FrgIjHX7oTzpcn\n" +
            "nb4nQzIliyG+8HK1uOyHRrvfEQk2aqXkI7jKHQNuC5p4Vco9nJQRIdzvq8RKPmDo\n" +
            "yCsmkKVNbOkRyb9C8uj5JeuAY2b09N7Y0CYdk166aCAT7hFyNQzVqCTwyFLjA3EY\n" +
            "UlEWj3IoSD5ifiFfZya2mnmH1wKBgQDmdZlN/oK/DMKngy/l4xlXiLI2kBF0alb0\n" +
            "wwYWYv3gRTRI3psrB/5JY+Y03or/2g9w+/7vcf6ijPvPn3N8G4C3TBYwl2LeE2ii\n" +
            "YdX3AO4EIIQL3kf/DkJF2pTmk3Lz/ubp0P3CP5MtuZG5pRQFD42oA8xpPx92QPU5\n" +
            "K3rRRsdmrQKBgHX2Rmu1MwZAacxoUBZ/ZP3PoaobhNE5ZT4/LBFpkMaRoJmoeeJa\n" +
            "5T2CZiiEcre4qVMMRKMa7YFa/dZ4ueJDTzd33QNLGwAfLS0tEKjFb/adfWY2oaLH\n" +
            "ryczDrLMGjA8eZI7liBbqCAr662I1Mqym0v7qoGWzEhKGPF6xTnbdd1nAoGBAOMm\n" +
            "fliVx6Evnp0lfQyF7vCMV7vX/JxWRCc0IsfmAn4POB2q7B5Z3pXP1ZO+1hqggB/J\n" +
            "AdpY7baiZ4CZlWZ6ZUBFyolSbayeTL/g1tiDHhcLRZu9GBGZqFDZxXIPW3qLgGZu\n" +
            "C/BuYU7sXWox/62NSv6T3+LMyOHx3MLgzEmCDgBxAoGBAIufpFcJf2vCE27UqSPY\n" +
            "3FIbVaJmoVTWdS1p4Q7pYRUNnW4yltKv4DiyN5ZKz5R9xRs5YGyUPpeKYWVo7FRi\n" +
            "/cD2vW3dYXzFTzsmJD36Q7ZhyjBFOijK9LMu3xDy/bLCiPUc2sSrTkWJHL8kq4cd\n" +
            "EpSYR9bjjoM+SkoLDPqN00eN\n" +
            "-----END PRIVATE KEY-----";

    public static String decrypt(String encryptedBase64) throws Exception {
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedBase64);
        PrivateKey privateKey = getPrivateKeyFromPem(PRIVATE_KEY_PEM);
        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-1AndMGF1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes);
    }

    private static PrivateKey getPrivateKeyFromPem(String pem) throws Exception {
        PemReader pemReader = new PemReader(new StringReader(pem));
        byte[] content = pemReader.readPemObject().getContent();
        pemReader.close();
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(content);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(keySpec);
    }
}
