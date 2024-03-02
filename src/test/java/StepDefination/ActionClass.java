package StepDefination;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ActionClass {
	public WebDriver driver;
	public ActionClass(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    }
	@FindBy (xpath="//div[@id='O365_HeaderRightRegion']")
	WebElement accountManager;
	
	@FindBy (xpath="//div[@id='mectrl_currentAccount_primary']")
	WebElement usrName;
	
	public String getUserName() 
	{
		return usrName.getText();
	}
	
	@FindBy (xpath="//div[@id='mectrl_currentAccount_secondary']")
	WebElement usrId;
	
	public String getUserId() 
	{
		return usrId.getText();
	}
	
	@FindBy (xpath="//div[@class='ar_b_91bed31b ac_b_91bed31b']//a")
	List <WebElement> allNews;
	
	@FindBy (xpath="//div[contains(@class,'_9f38462c') and @aria-level='1']")
	WebElement headingLocator;
	
	@FindBy (xpath="//div[@id='fa45f946-463e-428f-a84b-0bbbde09c3ba']")
	WebElement newsInfo;
	
	public String getNewsInfo() 
	{
		return newsInfo.getText();
	}
	
	@FindBy (xpath="//div[@id='c6c4fb50-d4a7-4791-81f8-a4f069ac45f3']")
	WebElement newsInfoLast;
	
	public String getNewsInfoLast() 
	{
		return newsInfoLast.getText();
	}
	
	@FindBy (xpath="//*[@id='5d7d4eec-cbe0-4c55-ae2e-f38d926d82a0']/div/div/div/p/span/strong")
	WebElement aboutCognizant;
	
	@FindBy (xpath="//div[contains(@id,'89c5ffca-2ffb-4052-a723-e99c8c9a14ef')]//div[contains(@class,'labelTextWrapper')]")
	List<WebElement> appNames;
	
	@FindBy (xpath="//div[@id='spTopPlaceholder']//div[@role='none']//div[1]")
	WebElement beCognizant;
}