package com.example.password.Componment;

public class StringUtils extends com.sun.tools.javac.util.StringUtils {
    /**
     * 重命名，UUIU
     *
     * @param oleFileName
     * @return
     */
    public static String reloadFile(String oleFileName) {
        oleFileName = getFileName(oleFileName);
        if (oleFileName.isEmpty()) {
            return oleFileName;
        }
        //得到后缀
        if (oleFileName.indexOf(".") == -1) {
            //对于没有后缀的文件，直接返回重命名
            return UUIDUtils.getUUID();
        }
        String[] arr = oleFileName.split("\\.");
        // 根据uuid重命名图片
        String fileName = UUIDUtils.getUUID() + "." + arr[arr.length - 1];

        return fileName;
    }

    /**
     * 把带路径的文件地址解析为真实文件名 /25h/upload/hc/1448089199416_06cc07bf-7606-4a81-9844-87d847f8740f.mp4 解析为 1448089199416_06cc07bf-7606-4a81-9844-87d847f8740f.mp4
     *
     * @param url
     */
    public static String getFileName(final String url) {
        if (url.isEmpty()) {
            return url;
        }
        String newUrl = url;
        newUrl = newUrl.split("[?]")[0];
        String[] bb = newUrl.split("/");
        String fileName = bb[bb.length - 1];
        return fileName;
    }

}
