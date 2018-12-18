package pageobjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.provar.core.testapi.annotations.*;

@Page( title="MyPageObject"                                
     , summary=""
     , relativeUrl=""
     , connection="Demo"
     )             
public class MyPageObject {

	@TextType()
	@FindBy(xpath = "//span[text()='Reports To']/../following-sibling::div//ul[@class='orderedList']//a//span[@class='deleteIcon']")
	public WebElement ReportsToId;
	@TextType()
	@FindBy(xpath = "//span[text()='Account Name']/../following-sibling::div//ul[@class='orderedList']//a//span[@class='deleteIcon']")
	public WebElement AccountId;
	@TextType()
	@FindBy(xpath = "//input[@name=\"firstname\"]")
	public WebElement FirstName;
	@TextType()
	@FindBy(xpath = "//input[@name=\"lastname\"]")
	public WebElement Surname;
			
}
