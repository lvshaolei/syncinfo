package syncinfo.utils.security;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


public class Base64 {
	public static String encode(byte[] inValue) {
	    String outStr = null;
	    BASE64Encoder base64Encoder = new BASE64Encoder();
	    try {
	      outStr = base64Encoder.encode(inValue);
	      outStr = outStr.replace("\r", "").replace("\n", "");
	    }
	    catch (Exception e) {
	      return null;
	    }
	    return outStr;
	}
	
	public static String encode_splite(byte[] inValue) {
	    String outStr = null;
	    BASE64Encoder base64Encoder = new BASE64Encoder();
	    try {
	      outStr = base64Encoder.encode(inValue);
	    }
	    catch (Exception e) {
	      return null;
	    }
	    return outStr;
	}
	
	public static byte[] decode(String inValue) {
		byte[] outValue = null;
		BASE64Decoder base64Decode = new BASE64Decoder();
		try {
			outValue = base64Decode.decodeBuffer(inValue);
		} catch (Exception e) {
			return null;
		}
		return outValue;
	}

}
