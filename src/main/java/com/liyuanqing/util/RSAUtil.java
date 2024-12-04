package com.liyuanqing.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.liyuanqing.wecom.ExternalLoginDTO;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class RSAUtil {

    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;

    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;

    /**
     * 企微平台公钥私钥
     */
    private static final String publickey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC4MYCBS/j4kw7+goVY7WiAzkTLTNkkH5Dqa3PZuB8nofHBoSZ/xIFDy38l69odOYal9nh6DzQ6Xj+IGSMUn0i5KY7yCYbRJ6jU+T0IAC/8PO/ZcngsyjEnzp+Wn3j+q9ql6tZf1KqK1DHRjA24zoWxVXjY7I8Ht7Yaz9K2zZyOSwIDAQAB";
    private static final String privatekey = "MIICeQIBADANBgkqhkiG9w0BAQEFAASCAmMwggJfAgEAAoGBALgxgIFL+PiTDv6ChVjtaIDORMtM2SQfkOprc9m4Hyeh8cGhJn/EgUPLfyXr2h05hqX2eHoPNDpeP4gZIxSfSLkpjvIJhtEnqNT5PQgAL/w879lyeCzKMSfOn5afeP6r2qXq1l/UqorUMdGMDbjOhbFVeNjsjwe3thrP0rbNnI5LAgMBAAECgYEAgfP69D5DtC0dIRe5ORqW5nmhM8ZOVRwoRBv3qp4q7O1nAfPOk8pyYQCL/pc+VewmQaV5LrTXZm7kaANEKuKOjVHWC/Bx6pMqgidTeEtVXGyZWnhKctrP3AH59d7UiTsUBmv+9l1ZGa8w+uQP5xp5jjliXfXiI84Vf3iuFYz+KdECQQDoVXXURd9NssBYfkX89fcr3Q+Tk73MMMnr+3HThXDDU3rZ0V6jOtiKupN4xmS7uAESsDmZHgK/hsHl52TBQSi1AkEAyvSwYceT0sekwcGbEzIubNOh67W+K1S3p/ym99qpXFtxcn/1dwXdXCGliXgOC3gqsQCiSsr1C4iZbMXkWgw6/wJBANb9xhRROAspPw5kgxVK8peXb4OLspVSDHgfbLxkfe1xwXvwX5fCq4Dsl+yqAasn4W5Ovm3vNHHRHHpHfpfKWQUCQQCI2raVOJ7VYwoWHRcUuqHTgGGPL94sGdQ90oBEYY4SWRcGvoIm4C2EEqwtJmdSFJW1BVhqVrEmNdY/IrH4hobBAkEAmY+tuT1qa4URYCImbye33LpX8EKqz9RX7spaSUq10YxRbcTm7ocURxUwQ3bUzgarO8v5y4V5JPWUyBjKKRKvBQ==";

    public static final String LF = "\n";

    public static final String CR = "\r";

    public static final String SPACE = " ";

    public static final String EMPTY = "";

    public static final String UTF_8 = "UTF-8";

    private final static String RSA_CIPHER = "RSA";

    private static KeyFactory RSA_KF;

    static{
        try{
            Security.addProvider(new BouncyCastleProvider());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    /**
     * RSA pkcs1 2048bit 解密工具,
     * 获取私钥PrivateKey
     *
     * @param privKeyPEM 2048bit pkcs1格式,base64编码后的RSA字符串
     * @return PrivateKey, 用于解密 decryptRSA
     * @throws IOException 异常
     */
    public static PrivateKey getPrivateKeyPEM(String privKeyPEM) throws IOException {
        PrivateKey privateKey = null;
        Reader privateKeyReader = new StringReader(privKeyPEM);
        PEMParser privatePemParser = new PEMParser(privateKeyReader);
        Object privateObject = privatePemParser.readObject();
        if (privateObject instanceof PEMKeyPair) {
            PEMKeyPair pemKeyPair = (PEMKeyPair) privateObject;
            JcaPEMKeyConverter converter = new JcaPEMKeyConverter().setProvider("BC");
            privateKey = converter.getPrivateKey(pemKeyPair.getPrivateKeyInfo());
        }
        return privateKey;
    }

    /**
     * RSA pkcs1 2048bit 解密工具,
     *
     * @param str        被解密的字符串
     * @param privateKey 私钥对象 从 getPrivateKey 获取
     * @return 解密后数据
     * @throws NoSuchPaddingException    异常
     * @throws NoSuchAlgorithmException  异常
     * @throws InvalidKeyException       异常
     * @throws BadPaddingException       异常
     * @throws IllegalBlockSizeException 异常
     */
    public static String decryptRSA(String str, PrivateKey privateKey) throws NoSuchPaddingException,
            NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, NoSuchProviderException {
        Security.addProvider(new BouncyCastleProvider());
        Cipher rsa = Cipher.getInstance("RSA/ECB/PKCS1Padding", "BC");
        rsa.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] utf8 = rsa.doFinal(Base64.getDecoder().decode(str));
        return new String(utf8, StandardCharsets.UTF_8);
    }

    public static PrivateKey getPrivateKey(String privateKey) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] decodedKey = org.apache.commons.codec.binary.Base64.decodeBase64(privateKey.getBytes());
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decodedKey);
        return keyFactory.generatePrivate(keySpec);
    }
    /**
     * RSA加密
     * @param data 待加密数据
     */

    public static String encrypt(String data) throws Exception {
        if (null == data) {
            return null;
        }
        PublicKey publicKey1 = getPublicKey(publickey);
        return encrypt(data,publicKey1);
    }

    /**
     * RSA解密
     * @param data 待解密数据
     */
    public static String decrypt(String data) throws Exception {
        if (null == data) {
            return null;
        }
        PrivateKey privateKey = getPrivateKey(privatekey);
        return decrypt(data, privateKey);
    }

    /**
     * RSA加密
     *
     * @param data      待加密数据
     * @param publicKey 公钥
     * @return
     */
    public static String encrypt(String data, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        int inputLen = data.getBytes().length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offset = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offset > 0) {
            if (inputLen - offset > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data.getBytes(), offset, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data.getBytes(), offset, inputLen - offset);
            }
            out.write(cache, 0, cache.length);
            i++;
            offset = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        // 获取加密内容使用base64进行编码,并以UTF-8为标准转化成字符串
        // 加密后的字符串
        return new String(org.apache.commons.codec.binary.Base64.encodeBase64String(encryptedData));
    }

    public static String decrypt(String data, String privateString) throws Exception {
        PrivateKey privateKey = getPrivateKey(privateString);
        return decrypt(data, privateKey);
    }
    public static String encrypt(String data, String publicKey) throws Exception {
        PublicKey publicKey1 = getPublicKey(publicKey);
        return encrypt(data,publicKey1);
    }
    public static String decrypt(String data, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] dataBytes = org.apache.commons.codec.binary.Base64.decodeBase64(data);
        int inputLen = dataBytes.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offset = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offset > 0) {
            if (inputLen - offset > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(dataBytes, offset, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(dataBytes, offset, inputLen - offset);
            }
            out.write(cache, 0, cache.length);
            i++;
            offset = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        // 解密后的内容
        return new String(decryptedData, "UTF-8");
    }
    /**
     * 获取公钥
     *
     * @param publicKey 公钥字符串
     * @return
     */
    public static PublicKey getPublicKey(String publicKey) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] decodedKey = org.apache.commons.codec.binary.Base64.decodeBase64(publicKey.getBytes());
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decodedKey);
        return keyFactory.generatePublic(keySpec);
    }


    /**
     * 签名
     *
     * @param data       待签名数据
     * @param privateKey 私钥
     * @return 签名
     */
    public static String sign(String data, PrivateKey privateKey) throws Exception {
        byte[] keyBytes = privateKey.getEncoded();
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey key = keyFactory.generatePrivate(keySpec);
        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initSign(key);
        signature.update(data.getBytes(UTF_8));
        return new String(org.apache.commons.codec.binary.Base64.encodeBase64(signature.sign()));
    }

    public static String sign(String data) throws Exception {
        return sign(data, getPrivateKey(privatekey));
    }

    /**
     * 签名
     *
     * @param data 待签名数据
     * @return 签名
     */
    public static String sign(String data, String priKey) throws Exception {
        return sign(data, getPrivateKey(priKey));
    }

    /**
     *
     * @param data
     * @param RSAPrivateKey
     * @param signType
     * @return
     * @throws Exception
     */
    public static synchronized String sign(String data, RSAPrivateKey RSAPrivateKey, String signType) throws Exception {

        // 用私钥对信息生成数字签名
        Signature signature = Signature.getInstance(signType);
        signature.initSign(RSAPrivateKey);
        signature.update(data.getBytes(UTF_8));

        return new String(Base64.getEncoder().encode(signature.sign()), UTF_8);
    }

    /**
     * 验签
     *
     * @param srcData   原始字符串
     * @param publicKey 公钥
     * @param sign      签名
     * @return 是否验签通过
     */
    public static boolean verify(String srcData, PublicKey publicKey, String sign) throws Exception {
        byte[] keyBytes = publicKey.getEncoded();
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey key = keyFactory.generatePublic(keySpec);
        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initVerify(key);
        signature.update(srcData.getBytes(UTF_8));
        return signature.verify(org.apache.commons.codec.binary.Base64.decodeBase64(sign.getBytes()));
    }

    /**
     * 验签
     *
     * @param srcData   原始字符串
     * @param publicKey 公钥
     * @param sign      签名
     * @return 是否验签通过
     */
    public static boolean verify(String srcData, String publicKey, String sign) throws Exception {
        return verify(srcData, getPublicKey(publicKey), sign);
    }

    public static boolean verify(String srcData, String sign) throws Exception {
        PublicKey publicKey = getPublicKey(publickey);
        return verify(srcData, publicKey, sign);
    }

    public static RSAPrivateKey getPemRSAPrivateKey(String contentBase64) throws Exception {
        String privKeyPEM = contentBase64.replace("-----BEGIN PRIVATE KEY-----", EMPTY)
                .replace("-----END PRIVATE KEY-----", EMPTY)
                .replace(LF, EMPTY).replace(CR, EMPTY)
                .replace(SPACE, "+");

        byte[] decoded = Base64.getDecoder().decode(privKeyPEM.getBytes(UTF_8));
        return getRSAPrivateKey(decoded);
    }

    public static RSAPrivateKey getRSAPrivateKey(byte[] keyBytes) throws NoSuchAlgorithmException, InvalidKeySpecException {
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory kf = RSA_KF != null ? RSA_KF : KeyFactory.getInstance(RSA_CIPHER);

        return (RSAPrivateKey) kf.generatePrivate(spec);
    }


    public static void main(String[] args) throws Exception {
        //  第三方登陆
        ExternalLoginDTO dto = new ExternalLoginDTO();
        dto.setSource("LYQ");
        dto.setLoginType("userAPI");
        dto.setBankCode("6555");
        dto.setInstCode("QYWX99");
        dto.setEmployeeNo("5080");
        dto.setMobileNo("18266245080");
        dto.setNickname("李源青_LYQ");
        dto.setMicrofilm("50805080");
        dto.setSceneNum("1");
        dto.setRedirectUrl("redirectUrl");
        String param = JSON.toJSONString(dto);
        JSONObject json = new JSONObject();
        json.put("appId", "LYQ");
        json.put("sign", RSAUtil.sign(param));
        json.put("sign", RSAUtil.encrypt(param));
        URLEncoder.encode(json.toJSONString(), "utf-8");
    }
}
