package com.roachf.survey.utils.cryptology;

import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

public class RSA {
	
	/** base64字符集 0..63 */
    private static char[] alphabet ="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".toCharArray();
    /**
     * 初始化base64字符集表
     */
    private static byte[] codes = new byte[256];

    static {
        for (int i = 0; i < 256; i++) codes[i] = -1;
        for (int i = 'A'; i <= 'Z'; i++) codes[i] = (byte) (i - 'A');
        for (int i = 'a'; i <= 'z'; i++) codes[i] = (byte) (26 + i - 'a');
        for (int i = '0'; i <= '9'; i++) codes[i] = (byte) (52 + i - '0');
        codes['+'] = 62;
        codes['/'] = 63;
    }
	
	private static RSA rsa = null;
	
	private RSAPublicKey publicKey;
	
	private RSAPrivateKey privateKey;
	
	private static String KEY_ALGORITHM = "RSA";
	
    private static final String SIGNATURE_ALGORITHM = "MD5withRSA";
	
	private RSA() {
	}
	
	public static RSA getInstance(){
		if(rsa == null){
			rsa = new RSA();
		}
		try {
			KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
			keyPairGen.initialize(2048);
			KeyPair keyPair = keyPairGen.generateKeyPair();
			rsa.publicKey = (RSAPublicKey) keyPair.getPublic();
			rsa.privateKey = (RSAPrivateKey) keyPair.getPrivate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rsa;
	}
	
	
	/**
     * 验证签名
     * @param data 要验证的密文
     * @param publicKey 公钥
     * @param sign 签名信息
     * @return 返回验证成功状态
     */
    public boolean verify(String decryptData, String publicKey, String sign) {
        try {
            // 解密由base64编码的公钥
            byte[] keyBytes = base64Decode(publicKey);
            // 构造X509EncodedKeySpec对象
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
            // KEY_ALGORITHM 指定的加密算法
            Signature signature;
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            // 取公钥匙对象
            PublicKey pubKey = keyFactory.generatePublic(keySpec);
            signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initVerify(pubKey);
            signature.update(base64Decode(decryptData));
            // 验证签名是否正常
            return signature.verify(base64Decode(sign));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

	
	 /**
     * 数字签名
     * @param data 要签名的密文
     * @param privateKey 私钥
     * @return 返回签名信息
     * 
     */
    public String sign(String data, String privateKey) {
        try {
            // 解密由base64编码的私钥
            byte[] keyBytes = base64Decode(privateKey);
            // 构造PKCS8EncodedKeySpec对象
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
            // KEY_ALGORITHM 指定的加密算法
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            // 取私钥匙对象
            PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);
            // 用私钥对信息生成数字签名
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initSign(priKey);
            signature.update(base64Decode(data));
            return encode(signature.sign());
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
	
	/**
     * 私钥解密
     * @param data 要解密的字符串
     * @param key 私钥
     * @return 返回解密后的字符串
     */
    public String decryptByPrivateKey(String data, String privateKey) {
        try {
            // 对密钥解密
            byte[] keyBytes = base64Decode(privateKey);
            // 取得私钥
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            Key key = keyFactory.generatePrivate(pkcs8KeySpec);
            // 对数据解密
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.DECRYPT_MODE, key);
            return new String(cipher.doFinal(base64Decode(data)));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

	/**
     * 公钥加密
     * @param data 要加密的数据
     * @param key 公钥
     * @return 返回加密的数据
     */
    public String encryptByPublicKey(String encryptData, String publicKey) {
        try {
            // 对公钥解密
            byte[] keyBytes = base64Decode(publicKey);
            // 取得公钥
            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            Key key = keyFactory.generatePublic(x509KeySpec);
            // 对数据加密
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return encode(cipher.doFinal(encryptData.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 解码base64加码过的字符串
     * @return
     */
   /* private String decode(String data){
    	char[] chars = new char[data.length()];
        data.getChars(0, data.length(), chars, 0);
        return new String(base64Decode(chars));
    }*/
    
    /**
     * 将 base64 加码过的 String 转换成 byte[]
     * @param base64Str base64码
     * @return 返回转换后的字节数组
     */
    private byte[] base64Decode(String decodeData) {
        char[] dataArr = new char[decodeData.length()];
        decodeData.getChars(0, decodeData.length(), dataArr, 0);
        return base64Decode(dataArr);
    }
    
    /**
     * 将一个 char[] 解码成一个 byte[]
     * @param data base64字符数组
     * @return 返回解码以后的字节数组
     */
    private byte[] base64Decode(char[] data) {
        int len = ((data.length + 3) / 4) * 3;
        if (data.length > 0 && data[data.length - 1] == '=') --len;
        if (data.length > 1 && data[data.length - 2] == '=') --len;
        byte[] out = new byte[len];
        int shift = 0;
        int accum = 0;
        int index = 0;
        for (int ix = 0; ix < data.length; ix++) {
            int value = codes[data[ix] & 0xFF];
            if (value >= 0) {
                accum <<= 6;
                shift += 6;
                accum |= value;
                if (shift >= 8) {
                    shift -= 8;
                    out[index++] = (byte) ((accum >> shift) & 0xff);
                }
            }
        }
        if (index != out.length)
            throw new Error("miscalculated data length!");
        return out;
    }

	/**
	 * 将 byte[] 通过base64转码成 String
	 * @param bytes
	 * @return
	 */
	private String encode(byte[] bytes){
		return new String(base64Encode(bytes));
	}
	
	/**
     * 将 byte[] 转换成base64的字符数组
     * @param data 字节数组
     * @return base64字符数组
     */
    private char[] base64Encode(byte[] data) {
        char[] out = new char[((data.length + 2) / 3) * 4];
        for (int i = 0, index = 0; i < data.length; i += 3, index += 4) {
            boolean quad = false;
            boolean trip = false;
            int val = (0xFF & (int) data[i]);
            val <<= 8;
            if ((i + 1) < data.length) {
                val |= (0xFF & (int) data[i + 1]);
                trip = true;
            }
            val <<= 8;
            if ((i + 2) < data.length) {
                val |= (0xFF & (int) data[i + 2]);
                quad = true;
            }
            out[index + 3] = alphabet[(quad ? (val & 0x3F) : 64)];
            val >>= 6;
            out[index + 2] = alphabet[(trip ? (val & 0x3F) : 64)];
            val >>= 6;
            out[index + 1] = alphabet[val & 0x3F];
            val >>= 6;
            out[index + 0] = alphabet[val & 0x3F];
        }
        return out;
    }
	
	
	
	public RSAPublicKey getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(RSAPublicKey publicKey) {
		this.publicKey = publicKey;
	}

	public RSAPrivateKey getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(RSAPrivateKey privateKey) {
		this.privateKey = privateKey;
	}
	
	
	
	public static void main(String[] args) {
		RSA rsa = RSA.getInstance();
		
		/* 获取公钥私钥 */
		String publicKey = rsa.encode(rsa.getPublicKey().getEncoded());
		String privateKey = rsa.encode(rsa.getPrivateKey().getEncoded());
		System.out.println("privateKey==" + privateKey);
		System.out.println("publicKey==" + publicKey);
		
		/* 公钥加密 */
		String data = rsa.encryptByPublicKey("123", publicKey);
		System.out.println(data);
		/* 私钥解密 */
		String result = rsa.decryptByPrivateKey(data, privateKey);
		System.out.println(result);
		
		// 签名, 验签
		String sign = rsa.sign(data, privateKey);
		boolean flag = rsa.verify(data, publicKey, sign);
		System.out.println(flag);
	}
	
}
