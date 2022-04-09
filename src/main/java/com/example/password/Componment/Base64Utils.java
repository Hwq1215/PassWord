package com.example.password.Componment;

import org.apache.tomcat.util.codec.binary.Base64;
import java.io.*;


public class Base64Utils {
    public static String decodeBase64String(String prefix, String base64String,String extFilePath) {
        try {
            if (prefix == null || base64String == null) {
                return null;
            }
            long beginTime = System.currentTimeMillis();

            // 把base64前缀截取掉
            // base64文件前缀
            String value = Base64FileTypeEnum.value(prefix.toLowerCase());
            if (value == null || "".equals(value)) {
                return null;
            }
            // 替换
            String tempBase64String = base64String.replace(value, "");

            // 把base64字符串转换成字节
            //Base64Encoder encoder = new Base64Encoder();
            byte[] bytes = Base64.decodeBase64(tempBase64String);

            // 转换成字节输入流
            ByteArrayInputStream in = new ByteArrayInputStream(bytes);

            // 把base64编码文件还原, 并存放到指定磁盘路径中
            File file = new File(extFilePath);
            if(!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            OutputStream out = new FileOutputStream(file);

            // 写文件
            byte[] buffer = new byte[4096];

            int len = 0;
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len); // 文件写操作
            }

            long endTime = System.currentTimeMillis();
            System.out.println("解析base64编码文件, 文件类型为" + prefix + "总耗时: " + (endTime - beginTime) + "ms");
            return extFilePath;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

