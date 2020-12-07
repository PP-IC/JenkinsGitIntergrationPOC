package testCases;
import static testCases.AutomationRunner.browser;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class JavaModule {
	 WebDriver driver;
	 
  @Test(groups = { "Test","Java","Smoke"})
  public void Test_Case_1() {
	  System.out.println("Executing JAVA_Test_Case_1 from Java Group");
	  driver.findElement(By.linkText("Core Java")).click();     
  }

  @Test(groups = { "Test","Java"})
  public void Test_Case_2() {
	  System.out.println("Executing JAVA_Test_Case_2 from Java Group");
	  
  }
  
  @Test(groups = { "Test","Java"})
  public void Test_Case_3() {
	  System.out.println("Executing JAVA_Test_Case_3 from Java Group");
	  
  }
  
  
  @BeforeClass(alwaysRun = true)
  public void beforeClass() {
	  System.out.println("Before Class Execution");
	  switch (browser) {
	case "ie":
	case "IE":
	case "Ie":
	case "iE":
		System.setProperty("webdriver.ie.driver", "src/test/resources/drivers/IEDriverServer.exe");  
	    driver= new EdgeDriver();
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("ignoreZoomSetting", true);
		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		capabilities.setCapability("ignoreZoomLevel", true);
		capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		capabilities.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, false);
		capabilities.setCapability(InternetExplorerDriver.UNEXPECTED_ALERT_BEHAVIOR, true);
		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		capabilities.setJavascriptEnabled(true);
		capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
		driver= new InternetExplorerDriver(capabilities);
		 
		break;
	
	case "Chrome":
	case "CHROME":
	case "chrome":
		System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");  
	    driver=new ChromeDriver();  
	   
		break;

	case "Edge":
	case "EDGE":
	case "edge":
		System.setProperty("webdriver.edge.driver", "src/test/resources/drivers/msedgedriver.exe");  
	    driver= new EdgeDriver();
		break;

	default:
		break;
	}
	  
	  //Maximize the browser  
	  driver.manage().window().maximize();  
	  driver.navigate().to("http://www.javatpoint.com/"); 
  }

  @AfterClass(alwaysRun = true)
  public void afterClass() {
	  driver.quit();
  }

  @BeforeMethod(alwaysRun = true)
  public void beforeMethod() {
	// Launch Website  
	//  driver.navigate().to("http://www.javatpoint.com/");  
  } 
  
  @AfterMethod(alwaysRun = true)
  public void afterMethod() {
	try {
		Thread.sleep(2000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
  } 
  
}
