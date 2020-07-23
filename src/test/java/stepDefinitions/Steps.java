package stepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Steps extends BaseClass{

    @Before // from cucumber.io
    public void setup() throws IOException {

        //Added logger
        logger = Logger.getLogger("nopCommerce");
        PropertyConfigurator.configure("Log4j.properties");

        //Reading properties
        configProp = new Properties();
        FileInputStream configPropFile = new  FileInputStream("config.properties");
        configProp.load(configPropFile);

        String br = configProp.getProperty("browser");

        if (br.equals("chrome"))
        {
            System.setProperty("webdriver.chrome.driver", configProp.getProperty("chromepath"));
            driver = new ChromeDriver();
        }
        else if (br.equals("firefox"))
        {
            System.setProperty("webdriver.chrome.driver", configProp.getProperty("firefoxpath"));
            driver = new FirefoxDriver();
        }
        else if (br.equals("ie"))
        {
            System.setProperty("webdriver.chrome.driver", configProp.getProperty("iepath"));
            driver = new InternetExplorerDriver();
        }

        logger.info("***** Launching browser *****");
    }

    @Given("User launch Chrome browser")
    public void user_launch_chrome_browser() {

        lp = new LoginPage(driver);
    }

    @When("User open URL {string}")
    public void user_open_url(String url) {
        logger.info("***** Opening URL *****");
        driver.get(url);
        driver.manage().window().maximize();
    }

    @When("User enter email as {string} and password as {string}")
    public void user_enter_email_as_and_password_as(String email, String password) {
        logger.info("***** Providing login details *****");
        lp.setUserName(email);
        lp.setPassword(password);
    }

    @When("Click on Login")
    public void click_on_login() throws InterruptedException {
        logger.info("***** Started Login *****");
        lp.clickLogin();
        Thread.sleep(3000);
    }

    @Then("Page title should be {string}")
    public void page_title_should_be(String title) throws InterruptedException {
        if (driver.getPageSource().contains("Login was unsuccessful")) {
            driver.close();
            logger.info("***** Login passed *****");
            Assert.assertTrue(false);
        }else {
            logger.info("***** Login failed *****");
            Assert.assertEquals(title, driver.getTitle());
        }
        Thread.sleep(3000);
    }

    @When("User click on Log out link")
    public void user_click_on_Log_out_link() throws InterruptedException {
        logger.info("***** Click on logout link *****");
        lp.clickLogout();
        Thread.sleep(3000);
    }

    @Then("Close browser")
    public void close_browser() {
        logger.info("***** Closing browser *****");
        driver.quit();
    }

    //Customer feature step definitions...................................................................

    @Then("User can view Dashboard")
    public void user_can_view_dashboard() {
        addCust = new AddCustomerPage(driver);
        Assert.assertEquals("Dashboard / nopCommerce administration", addCust.getPageTitle());
    }

    @When("User click on customers Menu")
    public void user_click_on_customers_menu() throws InterruptedException {
        Thread.sleep(3000);
        addCust.clickOnCustomersMenu();
    }

    @When("Click on customers Menu Item")
    public void click_on_customers_menu_item() throws InterruptedException {
        Thread.sleep(3000);
        addCust.clickOnCustomersMenuItem();
    }

    @When("Click on Add new button")
    public void click_on_add_new_button() throws InterruptedException {
        addCust.clickOnAddNew();
        Thread.sleep(2000);
    }

    @Then("User can view Add new customer page")
    public void user_can_view_add_new_customer_page() {
        Assert.assertEquals("Add a new customer / nopCommerce administration", addCust.getPageTitle());
    }

    @When("User enter customer info")
    public void user_enter_customer_info() throws InterruptedException {
        logger.info("***** Adding new customer *****");
        logger.info("***** Providing customer details *****");
        String email = randomestring()+"@gmail.com"; //Calling Method From BaseClass
        addCust.setEmail(email);
        addCust.setPassword("test123");
        //Registered - Default
        //The customer cannot be in both 'Guests' and 'Registered' customer roles
        //Add the customer to 'Guests' or 'Registered' customer role

        //NOTE: Customer roles, and Newsletter have the same xPath ("k-multiselect-wrap k-floatwrap")!!!!! DISABLED

        //addCust.setCustomerRoles("Guest");
        Thread.sleep(3000);

        addCust.setManagerOfVendor("Vendor 2");
        addCust.setGender("Male");
        addCust.setFirstName("Pavan");
        addCust.setLastName("Kumar");
        addCust.setDob("7/05/1985");
        addCust.setCompanyName("busyQA");
        addCust.setAdminContent("This is for testing........");
    }

    @When("Click on Save button")
    public void click_on_save_button() throws InterruptedException {
        logger.info("***** Saving customer data *****");
        addCust.clickOnSave();
        Thread.sleep(2000);
    }

    @Then("User can view confirmation message {string}")
    public void user_can_view_confirmation_message(String msg) {
        Assert.assertTrue(driver.findElement(By.tagName("body")).getText()
        .contains("The new customer has been added successfully"));
    }

    @Then("Close the browser")
    public void close_the_browser() {
        driver.quit();
    }

    //Steps for searching a customer using EMailID .......................................................

    @When("Enter customer EMail")
    public void enter_customer_e_mail() {
        logger.info("***** Searching customer by email ID *****");
        searchCust = new SearchCustomerPage(driver);
        searchCust.setEmail("victoria_victoria@nopCommerce.com");
    }

    @When("Click on search button")
    public void click_on_search_button() throws InterruptedException {
        searchCust.clickSearch();
        Thread.sleep(3000);
    }

    @Then("User should found EMail in the Search table")
    public void user_should_found_e_mail_in_the_search_table() {
        boolean status = searchCust.searchCustomersByEmail("victoria_victoria@nopCommerce.com");
        Assert.assertEquals(true, status);
    }

    //Steps for searching a customer by using Firstname & Lastname

    @When("Enter customer FirstName")
    public void enter_customer_first_name() {
        logger.info("***** Searching customer by a firstname *****");
        searchCust = new SearchCustomerPage(driver);
        searchCust.setFirstName("Victoria");

    }

    @When("Enter customer LastName")
    public void enter_customer_last_name() {
        logger.info("***** Searching customer by a lastname *****");
        searchCust = new SearchCustomerPage(driver);
        searchCust.setLastName("Terces");

    }

    @Then("User should found Name in the Search table")
    public void user_should_found_name_in_the_search_table() {
        boolean status = searchCust.searchCustomersByName("Victoria Terces");
        Assert.assertEquals(true, status);
    }

}
