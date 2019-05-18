package utilities;


	import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.Select;
	import org.openqa.selenium.support.ui.WebDriverWait;
	import org.testng.Assert;
	import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
	import java.util.concurrent.TimeUnit;


	public class Utils {

//		private WebDriver driver;
//
//	    public Utils(WebDriver driver){
//	        this.driver=driver;
//	    }
		Browser obj= new Browser();
	
	    
	    public void click(By by){
	    	obj.driver.findElement(by).click();
	    	}

	    public WebElement getElement(By by){ 
	    	return obj.driver.findElement(by);
	    	}

	    public String getPageTitle(){

	        return  obj.driver.getTitle();
	    }

	    public void verify_Element_displayed(By by) throws InterruptedException{

	        wait_explicit_till_element_Displayed(by);
	        boolean result =obj.driver.findElement(by).isDisplayed();
	        Assert.assertEquals(result, true, "Element not displayed");

	    }
	    public void verify_Element_Not_displayed(By by) throws InterruptedException{

	        wait_explicit_till_element_Displayed(by);
	        boolean result =obj.driver.findElement(by).isDisplayed();
	        Assert.assertFalse(result, "Element not displayed as expected");

	    }
	    
	    public void verify_Element_displayed(By by, String msg){

	        wait_explicit_till_element_Displayed(by);
	        boolean result =obj.driver.findElement(by).isDisplayed();

	        Assert.assertEquals(result, true, "Element not displayed. "+msg);

	    }

	    public String get_Element_Text(By by){
	        return  obj.driver.findElement(by).getText();
	    }

	    public void verify_Element_Text(By by, String text){

	        String strExpected = obj.driver.findElement(by).getText().toLowerCase().trim();
	        Assert.assertEquals(strExpected, text.toLowerCase().trim(), "Element text not displayed as expected .Expected: "+text.toLowerCase()+" and Actual is : "+strExpected);

	    }

	    public String get_Attribute_Value(By by, String strAttribute){

	        String result = obj.driver.findElement(by).getAttribute(strAttribute);
	        return  result;
	    }


	    public Date get_Current_Date(){

	        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
	        //getTime() returns the current date in default time zone
	        Date date = calendar.getTime();
	        return date;
	    }


	    public void wait_explicit_till_element_Displayed(By by){


	        WebDriverWait waitnew=new WebDriverWait(obj.driver,20);
	        WebElement element = waitnew.until(ExpectedConditions.visibilityOfElementLocated(by));

	    }

	    public void waitForPageLoad() {
	    	 obj.driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);  
	    	 WebDriverWait wait = new WebDriverWait(obj.driver, 30);
	    	 JavascriptExecutor js = (JavascriptExecutor)obj.driver;
	    	  if (js.executeScript("return document.readyState").toString().equals("complete")){ 
	    	   System.out.println("Page Is loaded.");
	    	   return;
	    	 }
	    }

	    //*******************************************************//
	    public void sendText(By by, String text) {

	        WebElement objInput = obj.driver.findElement(by);

	        objInput.clear();
	        objInput.sendKeys(text);
	        obj.driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	        objInput.sendKeys(Keys.TAB);
	    }

	    public List get_Element_List(By by) {

	        List<WebElement> lisElement = obj.driver.findElements(by);

	        return lisElement;
	    }

	    public void click(WebElement objElement) {

	        wait_explicit_till_element_Clickable(objElement);
	        objElement.click();
	    }

	    public void wait_explicit_till_element_Clickable(WebElement objElement) {


	        WebDriverWait waitnew = new WebDriverWait(obj.driver, 20);
	        waitnew.until(ExpectedConditions.elementToBeClickable(objElement));

	        //  .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='WebDriver']")));

	    }

	    public void wait_Specific_Seconds(long sec) {

	        try {
	            Thread.sleep(sec);
	        } catch (Exception e) {

	        }
	        obj.driver.manage().timeouts().implicitlyWait(sec, TimeUnit.SECONDS);
	    }

		public void enterText(By by, String text) throws InterruptedException {
			// TODO Auto-generated method stub
			WebElement objInput = obj.driver.findElement(by);
			objInput.clear();
	        objInput.sendKeys(text);
	        obj.driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	        objInput.sendKeys(Keys.ENTER);
	       
			
		}
		
	public void validateText(By by, String validatestring){
		
		String actualString=obj.driver.findElement(by).getText();
		boolean b=actualString.contains(validatestring);
		Assert.assertEquals(b, true, "Actual is "+ b);
		
		}
	public String fetchSelectedOption(By by){
		WebElement objInput = obj.driver.findElement(by);
		Select sel= new Select(objInput);
		String selectedValue=sel.getFirstSelectedOption().getText();
		return selectedValue;
		}
	
	public  List<WebElement> findElements(By by){
		List<WebElement> list= obj.driver.findElements(by);
		int count= list.size();
//		System.out.println("Number of matching elements found is"+ count);
		return list;
		}
	public void refreshPage(){
		obj.driver.navigate().refresh();
		waitForPageLoad();
		}
	public boolean verifySortedList(List<WebElement> list){
		List<String> original=new ArrayList<String>();
		List<String> temp=new ArrayList<String>();
		for(int i=0; i<list.size(); i++){
			System.out.println("Discount is :"+list.get(i).getText());
			temp.add(list.get(i).getText());
			original.add(list.get(i).getText());
		}
		
		Collections.sort(temp, Collections.reverseOrder());
		boolean sorted = temp.equals(original);
		System.out.println("list sorted :"+ sorted);
		return sorted;
		}
	public void hoverOverAndClick(By deals){
		Actions builder = new Actions(obj.driver);
		builder.moveToElement(getElement(deals)).perform();
		By locator = By.xpath("//a[text()='Last Minute Deals']");
		click(locator);
		}
	public void switchWindow(){
	  Set <String> handles =obj.driver.getWindowHandles();
	  Iterator<String> it = handles.iterator();
	  //iterate through opened windows
	  while (it.hasNext()){
	  String parentWindow = it.next();
	  String childWindow=it.next();
	  obj.driver.switchTo().window(childWindow);
//	  obj.driver.switchTo().window(parentWindow);
		}
	}
	
	public void scroll(int scroll, By by){
		JavascriptExecutor js=(JavascriptExecutor) obj.driver;
		js.executeScript("window.scrollBy(0,450)");
//		js.executeScript("arguments[0].scrollIntoView();", by);
	}
	
	public void verifyAllUIElements(By[] xpathElementList){
		int elementCount = 0;
		for (By xpathStr : xpathElementList) {
			try{
				obj.driver.findElement(xpathStr).isDisplayed();
				elementCount++;
				System.out.println(xpathStr+ "is displayed");
				}catch(Exception e){
				System.out.println(xpathStr+" is not displayed");
			}
		}
		if (elementCount == xpathElementList.length) {
			Assert.assertTrue(true, "All the elements in the page are verified");
			System.out.println("All the elements in the page are verified");
		} else {
				Assert.fail("One or more elements are missing from the page");
			}
		
	}
}


