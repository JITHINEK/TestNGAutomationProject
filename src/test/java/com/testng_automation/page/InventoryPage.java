package com.testng_automation.page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class InventoryPage extends BasePage{

	public InventoryPage(WebDriver driver) {
		super(driver);
	}
	
	private By SELECT_FILTER = By.xpath("//*[@id='header_container']//select");
	private By INVENTORY_ITEMS = By.xpath("//div[@class='inventory_item_description']");
	private By INVENTORY_ITEM_NAME = By.xpath("By.className('inventory_item_name')");
	private By ADD_TO_CART_BUTTON = By.xpath("//button[text()='Add to cart']");
	
	public boolean addToCart(String productName) {
		
		List<WebElement> inventoryItem = getElements(INVENTORY_ITEMS);
		
		boolean itemFound = false;
		
		for(WebElement element: inventoryItem) {
			if(element.findElement(INVENTORY_ITEM_NAME).getText().equals(productName)) {
				element.findElement(ADD_TO_CART_BUTTON).click();
				itemFound = true;
				break;
			}
		}
		
		return itemFound ? true : false;
		
	}
	
	public boolean validateInventoryPage() {
		if(driver.getCurrentUrl().endsWith("/inventory.html"))
			return true;
		else 
			return false;
	}

}
