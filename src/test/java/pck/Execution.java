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

public class Execution {
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

    public void downloadFile(String fromUrl, String localFileName) throws IOException {
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
    
	public static void main(String[] args) throws IOException, FindFailed, InterruptedException {
		Execution ec = new Execution();
		ec.downloadFile("http://aquarius-dae.eur.ad.sag/PDShare/WWW/dataserveSuiteTest/data/SoftwareAGInstaller.jar", "SoftwareAGInstaller.jar");
		//ProcessBuilder pb = new ProcessBuilder("C:\\Program Files\\Java\\jdk1.8.0_181\\bin\\java", "-jar", "SoftwareAGInstaller.jar");
		ProcessBuilder pb = new ProcessBuilder("/usr/bin/java", "-jar", "SoftwareAGInstaller.jar");
		pb.directory(new File("/WM/TESTSRV/testenv/"));
		Process p = pb.start();
		s=new Screen();
		s.click("images\\advance.png");
		s.click("images\\images-tab.png");
		s.click("images\\image-checkbox.png");
		s.click("images\\create-image.png");
		s.type("images\\image-name.png", "c:\\Essentials\\imageAll.zip");
		s.click("images\\image-type.png");
		s.wheel("images\\image-wheel.png",1,8);
		//s.type(Key.PAGE_DOWN);
		s.click("images\\image-type-name.png");
		s.click("images\\ok.png");
		s.exists("images\\advance.png");
		s.click("images\\advance.png");
		s.click("images\\server-tab.png");
		s.click("images\\specify-server.png");
		s.type("images\\server-path.png","aquarius-blr:SuiteTest");
		s.click("images\\ok.png");
		s.click("images\\next.png");
		s.exists("images\\verify-sandbox-connected.png");
		s.click("images\\next.png");
		s.type("images\\username.png","latest");
		s.type("images\\password.png","latest");
		s.click("images\\next-blue.png");
		waitForImage("images\\wait-for-installer-tree.png",10);
		s.exists("images\\wait-for-installer-tree.png");
		System.out.println("++Inside Installer tree++");
		s.click("images\\select-box.png");
		s.click("images\\next-blue.png");
		s.click("images\\no-button.png");
		s.click("images\\next.png");
		s.click("images\\i-have-read.png");
		s.click("images\\next-blue.png");
		s.exists("images\\following-products.png");
		s.click("images\\next.png");
		waitForImage("images\\following-products-added.png", 300);
		s.click("images\\close.png");
		
	}

}
