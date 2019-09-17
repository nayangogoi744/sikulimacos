package pck;

import java.io.File;
import java.io.IOException;

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

	public static void main(String[] args) throws IOException, FindFailed, InterruptedException {
		ProcessBuilder pb = new ProcessBuilder("C:\\Program Files\\Java\\jdk1.8.0_181\\bin\\java", "-jar", "C:\\Essentials\\SoftwareAGInstaller.jar");
		pb.directory(new File("C:\\Essentials\\"));
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
		
	}

}
