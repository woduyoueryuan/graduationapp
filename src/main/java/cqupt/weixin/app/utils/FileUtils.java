package cqupt.weixin.app.utils;

public class FileUtils {

    public static String getFileType(String filename) {
        if (filename.endsWith(".jpg") || filename.endsWith(".jepg")) {
            return ".jpg";
        } else if (filename.endsWith(".png") || filename.endsWith(".PNG")) {
            return ".png";
        } else {
            return "application/octet-stream";
        }

    }
}
