package pageobjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.provar.core.testapi.annotations.*;
import com.provar.core.testapi.annotations.SteelBrickBy.Facet;

@SalesforcePage( title="Sbqq__sb"                                
               , summary=""
               , page="sb"
               , namespacePrefix="SBQQ"
               , object=""
               , connection="CPQ"
     )             
public class sbqq__sb {

	@ButtonType()
	@SteelBrickBy(customAction = "Add Products")
	public WebElement addProducts;

	@PageRow()
	public static class ProductSelect {

		@BooleanType()
		@SteelBrickBy(fieldSetTableItem = "checkbox")
		public WebElement productCode;
	}

	@SteelBrickBy(pageTableRow = "sb-lookup-layout#lookupLayout")
	@PageTable(firstRowContainsHeaders = false, row = ProductSelect.class)
	public List<ProductSelect> ProductSelect;
	@ButtonType()
	@SteelBrickBy(customAction = "plSelect")
	public WebElement select;
	@PageWaitAfter.BackgroundActivity(timeoutSeconds = 60)
	@ButtonType()
	@SteelBrickBy(customAction = "Save")
	public WebElement save;
	@ButtonType()
	@SteelBrickBy(customAction = "Calculate")
	public WebElement calculate;
	@TextType()
	@SteelBrickBy(fieldSetTableItem = "sb-le-footer")
	public WebElement QuoteTotal;
	
}
