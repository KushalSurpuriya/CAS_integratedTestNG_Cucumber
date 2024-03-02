package StepDefination;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.apache.logging.log4j.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class UserVerification  {
	
	public WebDriver driver=new ChromeDriver();
	String myName = "Surpuriya, Kushal (Cognizant)";
	String myId = "2304035@cognizant.com";
	String userName;
	String userId;
	int i;			
	ActionClass ac ;
	public static Logger Log;
	static List<WebElement> newsOrg = new ArrayList<WebElement>();
	List<String> newsInfoo=new ArrayList<String>();
	String insideHeading;
	String insideHeadingTip;
	
	@Given("The User is on the OneCognizant page")
	public void the_user_is_on_the_one_cognizant_page() throws InterruptedException {
		Log = LogManager.getLogger("UserVerification");
	    driver.get("https://cognizantonline.sharepoint.com/sites/Be.Cognizant/SitePages/Home.aspx");
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    driver.manage().window().maximize();
	    Thread.sleep(5000);
	    Log.info("The webpage launched....");
	}

	@When("User click on Account Manager")
	public void user_click_on_account_manager() throws InterruptedException {
		Thread.sleep(9000);
		ac = new ActionClass(driver);
		ac.accountManager.click();
	    //driver.findElement(By.xpath("//div[@id='O365_HeaderRightRegion']")).click();
	    Thread.sleep(3000);
	    ac.accountManager.click();
	    //driver.findElement(By.xpath("//div[@id='O365_HeaderRightRegion']")).click();
	    Log.info("The user clicked the account manager....");
	}

	@When("Get the User Name and ID")
	public void get_the_user_name_and_id() {
	    userName = ac.getUserName();//driver.findElement(By.xpath("//div[@id='mectrl_currentAccount_primary']")).getText();
	    userId = ac.getUserId();//driver.findElement(By.xpath("//div[@id='mectrl_currentAccount_secondary']")).getText();
	    System.out.println(userName);
	    System.out.println(userId);
	    Log.info("Got the username and userId....");
	}

	@When("Verify the user information")
	public void verify_the_user_information() {
	    if (myName.equalsIgnoreCase(userName) && myId.equalsIgnoreCase(userId)) {System.out.println("User name and Id Verified");}
	    else {System.out.println("User not verified"+ userName + userId);}
	    Log.info("Verified the user information....");
	}

	@When("User checks all the news headings and Tip")
	public void user_checks_all_the_news_headings_and_tip() {
	    newsOrg = ac.allNews;
	    System.out.println("The total Number of News are = "+newsOrg.size());
	    for(WebElement elm : newsOrg) 
	    {
	    	String head = elm.getText();
	    	String tip = elm.getAttribute("title");
	    	if (head.equalsIgnoreCase(tip)) 
	    	{
	    		System.out.println(head+" = "+tip);
	    	}
	    }
	    Log.info("User checks all the news headings....");
	}
	
	@When("User clicks on news and verify it")
	public void user_clicks_on_news_and_verify_it() throws InterruptedException {
		System.out.println("-------------------------------------------------------------------------------------------------------");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		for (i=1;i<6;i++) 
		{
			driver.navigate().refresh();
			WebElement newsHeadList = driver.findElement(By.xpath("(//div[@class='ar_b_91bed31b ac_b_91bed31b']//a)["+i+"]"));
			wait.until(ExpectedConditions.elementToBeClickable(newsHeadList));
			Thread.sleep(3000);
			driver.findElement(By.xpath("(//div[@class='ar_b_91bed31b ac_b_91bed31b']//a)["+i+"]")).click();
			Thread.sleep(5000);
			WebElement headingLoc = ac.headingLocator;
			String insideHeading = headingLoc.getText();
			String insideHeadingTip = headingLoc.getAttribute("title");
			if (insideHeading.equalsIgnoreCase(insideHeadingTip)) 
	    	{
	    		System.out.println("***************"+insideHeading+" = "+insideHeadingTip+"***************");
	    	}
			else {System.out.println("Code Fata");}
			if (i<5) 
			{
				System.out.println(ac.getNewsInfo());
				newsInfoo.add(ac.getNewsInfo());
			}
			else 
			{
				String info_1 = ac.getNewsInfoLast();
				newsInfoo.add(info_1);
				System.out.println(info_1);
				
			}
			driver.navigate().back();
			System.out.println("******************************************************************************************************************************");
			
		}
		Log.info("User clicked and verified all the news....");
	}
	
	@When("User verifies the Home Page")
	public void User_verifies_the_Home_Page() 
	{
		System.out.println("------------------------------------------------------------------");
		ac.beCognizant.click();
		String exp_Title = "Be.Cognizant - Home";
		if(driver.getTitle().equals(exp_Title)) {
			System.out.println("Page Title is validated.");
			}
			else {
			System.out.println("Page Title is not validated.");
			}
	}

	@When("Print the list of apps")
	public void print_the_list_of_apps() {
		System.out.println("------------------------------------------------------------------");
		System.out.println("The list of appps is as follows :");
		WebElement element=ac.aboutCognizant;
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView()",element);
		List<WebElement>e=ac.appNames;
		for(int i=0;i<e.size();i++) {
		System.out.println(e.get(i).getText());
		}
	    Log.info("Printed the list of apps....");
	}
	
	@When("Printing all information in the Excel")
	public void Printing_all_information_in_the_Excel() 
	{
		String filePath = "C:\\Users\\2304035\\Downloads\\NewsFeatureOutput.xlsx";
			       
			try 
		    {
		    	FileOutputStream file = new FileOutputStream(filePath);
		        XSSFWorkbook workbook=new XSSFWorkbook();
		    	XSSFSheet sheet=workbook.createSheet("News Info");
		   		// Headers
		    	XSSFRow headerRow = sheet.createRow(0);
		        headerRow.createCell(0).setCellValue("News Heading");
		        headerRow.createCell(1).setCellValue("News Info");
		        // Data
		        int rowCount = 1;
		        for (int i = 0; i<5; i++) 
	            {
		        	XSSFRow row = sheet.createRow(rowCount++);
		            XSSFCell headingCell = row.createCell(0);
		            XSSFCell infoCell = row.createCell(1);
		 
		            headingCell.setCellValue(newsOrg.get(i).getText());
	                infoCell.setCellValue(newsInfoo.get(i));
	            }
		            // Adjusting cell width
		            sheet.autoSizeColumn(0);
		            sheet.autoSizeColumn(1);

		            // Adjusting cell height
		            for (int i = 0; i <= sheet.getLastRowNum(); i++) {
		                sheet.getRow(i).setHeight((short) -1);
		            }		 
		            workbook.write(file);
		            System.out.println("Excel file created successfully at: " + filePath);
		    	}
		    	catch (IOException e) 
		    	{
		    		e.printStackTrace();
		    	}
	}
	
	@Then("Printing all News Feeds in the txt file")
	public void Printing_all_News_Feeds_in_the_txt_file() throws IOException  
	{
		FileWriter writer = new FileWriter(System.getProperty("user.dir")+ "/reports/NewsInfo.txt");
		for (int i=0;i<5;i++) 
		{
			System.out.println();
			writer.write("***************"+ac.allNews.get(i).getText()+"***************");
			writer.write(newsInfoo.get(i)+"\n");
		}
		writer.close();
		driver.quit();
	}
}