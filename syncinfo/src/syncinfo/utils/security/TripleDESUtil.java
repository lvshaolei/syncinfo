/**
 * 
 */
package syncinfo.utils.security;

import java.security.Key;  
  
import javax.crypto.Cipher;  
import javax.crypto.SecretKey;  
import javax.crypto.SecretKeyFactory;  
import javax.crypto.spec.DESedeKeySpec;  
import javax.crypto.spec.IvParameterSpec;

import syncinfo.utils.security.Base64;


  

  
/**
 *  
 * Triple-DES对称加密算法 
 * @author charles zhang
 * @company infosec Technology Co,. Ltd
 * 采用SUN JCE 没有在IBM JCE上进行测试

 */
public class TripleDESUtil {  
    public static final String KEY_ALGORITHM = "DESede";                                          //指定DESede算法也就是所谓的Triple-DES,俗称3DES 
    public static final String CIPHER_ALGORITHM = "DESede/CBC/PKCS5Padding";                      //指定算法/模式/补丁 
    private static final byte[] IV = new byte[] {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00}; //采用CBC Mode需指定默认初始向量IV
    private static final byte[] SEC_KEY = {(byte)0x6A,(byte)0x2A,(byte)0x19,(byte)0xF4,(byte)0x1E,(byte)0xCA,(byte)0x85,(byte)0x4B,(byte)0x03,(byte)0xE6,(byte)0x9F,(byte)0x5B,(byte)0xFA, (byte)0x58, (byte)0xEB, (byte)0x42 ,(byte)0xDD, (byte)0x17, (byte)0xE8, (byte)0xB8, (byte)0xB4, (byte)0x37, (byte)0xD2, (byte)0x32};    //主密钥  
  
    /** 
     * change byte key to secretKey
     */  
    private static Key toKey(byte[] key) throws Exception {  
    	DESedeKeySpec dks = new DESedeKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM);  
        SecretKey secretKey = keyFactory.generateSecret(dks);  
        return secretKey;  
    }  
      
    /** 
     * 加密数据  encryption data
     * @param data 待加密数据(To be encrypted data)
     * @param key  密钥     (key)
     * @return 加密后的数据   (Encrypted data)
     */  
    public static String encrypt(String data, byte[] key) throws Exception {  
        Key k = toKey(key);  
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);  
        IvParameterSpec iv2 = new IvParameterSpec(IV); 
        cipher.init(Cipher.ENCRYPT_MODE, k,iv2);  
        return Base64.encode(cipher.doFinal(data.getBytes()));  
    }
    
    public static String encrypt(String data) throws Exception {  
    	String enc=encrypt(data, SEC_KEY);
        return enc;  
    }
    
    public static String encrypt(String propertyName, String data) throws Exception {  
    	String enc=encrypt(data, byteMerger(propertyName.getBytes(), SEC_KEY));
    	return enc;  
    }
      
    /** 
     * 解密数据 Decryption data
     * @param data 待解密数据 (To be decryption data)
     * @param key  密钥      (key)
     * @return 解密后的数据    (Encrypted data)
     */  
    public static String decrypt(String data, byte[] key) throws Exception {  
        Key k = toKey(key);  
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);  
        IvParameterSpec iv2 = new IvParameterSpec(IV); 
        cipher.init(Cipher.DECRYPT_MODE, k,iv2);  
        return new String(cipher.doFinal(Base64.decode(data)));  
    } 
    
    public static String decrypt(String propertyName, String encdata) throws Exception { 
    	String plaintxt=decrypt(encdata, byteMerger(propertyName.getBytes(), SEC_KEY));
        return plaintxt;  
    }
    public static String decrypt(String encdata) throws Exception { 
    	String plaintxt=decrypt(encdata,SEC_KEY);  
    	return plaintxt;  
    }
     
    //java 合并两个byte数组  
    public static byte[] byteMerger(byte[] byte_1, byte[] byte_2){  
        byte[] byte_3 = new byte[byte_1.length+byte_2.length];  
        System.arraycopy(byte_1, 0, byte_3, 0, byte_1.length);  
        System.arraycopy(byte_2, 0, byte_3, byte_1.length, byte_2.length);  
        
        return byte_3;  
    }  
    /*
     * Call examples
     */
    public static void main(String[] args) throws Exception {  
    	
//    	if(args.length == 1){
//    		System.out.println("plain text:  "+args[0]);
//    		String encryptData = encrypt(args[0]);  
//    		System.out.println("Cipher text: " + encryptData);
//    	}
    	//set plain text
        String plaintxt = "111111werwUUYY_+@11";  
        String propertyName = "jdbc.username";
        String encryptData = encrypt(propertyName, plaintxt);  
        System.out.println("Cipher text: " + encryptData);  
        
        //Decrypt
        String decryptData = decrypt(propertyName, "4Li7MITOS0WOZGc4+B0MCzvbHIPHyeUX");  
        System.out.println("plain text: " + decryptData);  
    }  
    
} 
