package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddCustomerPage {

    public WebDriver ldriver;

    // This is Constructor Class
    public AddCustomerPage(WebDriver rdriver) {
        ldriver = rdriver;
        PageFactory.initElements(ldriver, this);
    }

    By lnkCustomers_menu = By.xpath("//a[@href='#']//span[contains(text(), 'Customers')]");
    By lnkCustomers_menuitem = By.xpath("//span[@class='menu-item-title'][contains(text(), 'Customers')]");

    By btnAddNew = By.xpath("//a[@class='btn bg-blue']"); //Add new

    By txtEmail = By.xpath("//input[@id='Email']");
    By txtPassword = By.xpath("//input[@id='Password']");

//    By txtCustomerRoles = By.xpath("//div[@class='k-multiselect-wrap k-floatwrap']");

//    By listItemAdministrators = By.xpath("//li[contains(text(), 'Administrators')]");
//    By listItemRegistered = By.xpath("//li[contains(text(), 'Registered')]");
//    By listItemGuests = By.xpath("//li[contains(text(), 'Guests')]");
//    By listItemVendors = By.xpath("//li[contains(text(), 'Vendors')]");

    By drpManagerOfVendor = By.xpath("//*[@id='VendorId']");
    By rdMaleGander = By.id("Gender_Male");
    By rdFeMaleGander = By.id("Gender_Female");

    By txtFirstName = By.xpath("//input[@id='FirstName']");
    By txtLastName = By.xpath("//input[@id='LastName']");

    By txtDob = By.xpath("//input[@id='DateOfBirth']");

    By txtCompanyName = By.xpath("//input[@id='Company']");

    By txtAdminContent = By.xpath("//textarea[@id='AdminComment']");

    By btnSave = By.xpath("//button[@name='save']");

    //Action Methods

    public  String getPageTitle()
    {
        return ldriver.getTitle();
    }

    public void clickOnCustomersMenu() {
        ldriver.findElement(lnkCustomers_menu).click();
    }

    public void clickOnCustomersMenuItem() {
        ldriver.findElement(lnkCustomers_menuitem).click();
    }

    public void clickOnAddNew() {
        ldriver.findElement(btnAddNew).click();
    }

    public void setEmail(String email) {
        ldriver.findElement(txtEmail).sendKeys(email);
    }

    public void setPassword(String password) {
        ldriver.findElement(txtPassword).sendKeys(password);
    }

//    public void setCustomerRoles(String role) throws InterruptedException {
//
//        if (!role.equals("Vendors"))
//        {
//            ldriver.findElement(By.xpath("//*[@id=\"SelectedCustomerRoleIds_taglist\"]/li/span"));
//
//        }
//
//        ldriver.findElement((txtCustomerRoles)).click();
//
//        WebElement listitem;
//
//        Thread.sleep(3000);
//
//        if (role.equals("Administrators"))
//        {
//            listitem = ldriver.findElement(listItemAdministrators);
//        }
//        else if (role.equals("Guests"))
//        {
//            listitem = ldriver.findElement(listItemGuests);
//        }
//        else if (role.equals("Registered"))
//        {
//            listitem = ldriver.findElement(listItemRegistered);
//        }
//        else if (role.equals("Vendors"))
//        {
//            listitem = ldriver.findElement(listItemVendors);
//        }
//        else
//        {
//            listitem = ldriver.findElement(listItemGuests);
//        }
//
////        listItem.click();
////        Thread.sleep(3000);
//
//        JavascriptExecutor js = (JavascriptExecutor)ldriver;
//        js.executeScript("arguments[0].click();", listitem);
//
//    }

    public void setManagerOfVendor(String value)
    {
        Select drp = new Select(ldriver.findElement(drpManagerOfVendor));
        drp.selectByVisibleText(value);
    }

    public void setGender(String gender)
    {
        if (gender.equals("Male"))
        {
            ldriver.findElement(rdMaleGander).click();
        }
        else if (gender.equals("Female"))
        {
            ldriver.findElement(rdFeMaleGander).click();
        }
        else
        {
            ldriver.findElement(rdMaleGander).click(); // Default
        }
    }

    public void setFirstName(String fname)
    {
        ldriver.findElement(txtFirstName).sendKeys(fname);
    }
    public void setLastName(String lname)
    {
        ldriver.findElement(txtLastName).sendKeys(lname);
    }
    public void setDob(String dob)
    {
        ldriver.findElement(txtDob).sendKeys(dob);
    }
    public void setCompanyName(String compname)
    {
        ldriver.findElement(txtCompanyName).sendKeys(compname);
    }
    public void setAdminContent(String content)
    {
        ldriver.findElement(txtAdminContent).sendKeys(content);
    }
    public void clickOnSave()
    {
        ldriver.findElement(btnSave).click();
    }

}
