package pageobjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.provar.core.testapi.annotations.*;

@SalesforcePage( title="Product Selector"                                
               , summary=""
               , page="ProductSelector"
               , namespacePrefix=""
               , object="Opportunity"
               , connection="Demo"
     )             
public class ProductSelector {

	@VisualforceBy(componentXPath = "apex:inputField[@value = \"{!Opportunity.Name}\"]")
	@SalesforceField(name = "Name", object = "Opportunity")
	public WebElement opportunityName;
	@VisualforceBy(componentXPath = "apex:inputField[@value = \"{!Opportunity.CloseDate}\"]")
	@SalesforceField(name = "CloseDate", object = "Opportunity")
	public WebElement closeDate;
	@ButtonType()
	@VisualforceBy(componentXPath = "apex:commandButton[@action='{!save}']")
	public WebElement save;
	
}
