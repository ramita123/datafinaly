ufff y duniaa
 package dataProviderDemo;

import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class firstData {
	public WebDriver driver;
	@BeforeClass
	public void browser()
	{
		String driverpath= System.getProperty("user.dir")+"\\src\\Browser2\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver" ,driverpath);
		 driver=new ChromeDriver();
		//get Url 
		driver.get("https://www.facebook.com/");
	}
	
	@DataProvider
	public Object[][] readData()
	{
		String sheetPath=System.getProperty("user.dir")+"\\src\\TestDataProvider\\sheetData.xlsx";
		//file file=new file();
		Xls_Reader xlr=new Xls_Reader(sheetPath );
	//count total number of rows
		int rowcount=xlr.getRowCount("Sheet1");
		System.out.println(rowcount);
		//count no. of columns
		int colCount=xlr.getColumnCount("Sheet1");
		System.out.println(colCount);
		
		Object[][] testArray=new String[rowcount-1][colCount];
		int ai=0;
		for(int i=2;i<=rowcount;i++,ai++)
		{	int aj=0;
			for(int j=0;j<colCount;j++,aj++)
			{
				//to read the data from the column
				  testArray[ai][aj]=xlr.getCellData("Sheet1", j, i);
				  System.out.println(testArray[ai][aj]);
 			}
		}
		//System.out.println(Arrays.toString(testArray[ai][aj]));
		return testArray;
	}
	@Test(dataProvider="readData")
	public void login(String username,String password)
	{driver.findElement(By.id("email")).sendKeys(username);
	driver.findElement(By.id("pass")).sendKeys(password);
	 driver.findElement(By.id("loginbutton")).click();
}
	@AfterClass
	public void logout()
	{
		driver.quit();
		
	}
		
		
		 }
	


