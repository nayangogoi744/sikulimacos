package pck;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;

public class Helper{
	public static Screen s;
	public static boolean isImagePresent(String image)
    {
        boolean status = false;
        s = new Screen();
        try {
            s.find(image);
            status = true;
        } catch (FindFailed e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return status;
    }   

    public static void waitForImage(String image, int time) throws InterruptedException{
        for(int i=0; i<time; i++){
            if(isImagePresent(image)){
                break;
            }
            else{
                Thread.sleep(1000);
            }
        }
    }

    public static void downloadFile(String fromUrl, String localFileName) throws IOException {
        File localFile = new File(localFileName);
        if (localFile.exists()) {
            localFile.delete();
        }
        localFile.createNewFile();
        URL url = new URL(fromUrl);
        
        OutputStream out = new BufferedOutputStream(new FileOutputStream(localFileName));
        URLConnection conn = url.openConnection();
        String encoded = Base64.getEncoder().encodeToString(("username"+":"+"password").getBytes(StandardCharsets.UTF_8));  //Java 8
        conn.setRequestProperty("Authorization", "Basic "+ encoded);
        InputStream in = conn.getInputStream();
        byte[] buffer = new byte[1024];

        int numRead;
        while ((numRead = in.read(buffer)) != -1) {
            out.write(buffer, 0, numRead);
        }
        if (in != null) {
            in.close();
        }
        if (out != null) {
            out.close();
        }
    }
	
}
