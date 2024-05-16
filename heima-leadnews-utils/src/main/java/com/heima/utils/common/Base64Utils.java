package com.heima.utils.common;

//import sun.misc.BASE64Decoder;
//import sun.misc.BASE64Encoder;
import org.apache.commons.codec.binary.Base64;

public class Base64Utils {

    /**
     * 解码
     * @param base64
     * @return
     */
    public static byte[] decode(String base64){
        //BASE64Decoder decoder = new BASE64Decoder();

        try {
            // Base64解码
            //byte[] b = decoder.decodeBuffer(base64);
            byte[] b = Base64.decodeBase64(base64);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }
            return b;
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 编码
     * @param data
     * @return
     * @throws Exception
     */
    public static String encode(byte[] data) {
        //BASE64Encoder encoder = new BASE64Encoder();
        //return encoder.encode(data);
        return Base64.encodeBase64String(data);
    }
}