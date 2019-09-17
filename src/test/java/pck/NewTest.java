package pck;

import java.io.File;
import java.io.IOException;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;
import org.testng.annotations.Test;

public class NewTest extends Helper{
  @Test
  public void createImageForMAC() throws FindFailed, IOException, InterruptedException {
	  java.lang.System.setProperty("java.awt.headless", "false");
	  downloadFile("http://aquarius-dae.eur.ad.sag/PDShare/WWW/dataserveSuiteTest/data/SoftwareAGInstaller.jar", "SoftwareAGInstaller.jar");
		//ProcessBuilder pb = new ProcessBuilder("C:\\Program Files\\Java\\jdk1.8.0_181\\bin\\java", "-jar", "SoftwareAGInstaller.jar");
		ProcessBuilder pb = new ProcessBuilder("/WM/opt/jdk1.8.0_latest/bin/java", "-jar", "SoftwareAGInstaller.jar");
		Thread.sleep(10000);
		pb.directory(new File("/WM/TESTSRV/testenv/"));
		//pb.directory(new File("c:\\Essentials\\"));
		Process p = pb.start();
		s=new Screen();
		s.click("images\\advance.png");
		s.click("images\\images-tab.png");
		s.click("images\\image-checkbox.png");
		s.click("images\\create-image.png");
		s.type("images\\image-name.png", "imageAll.zip");
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
