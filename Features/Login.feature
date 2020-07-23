Feature: Login

  @tag1
  Scenario: Successful Login with Valid Credentials
    Given User launch Chrome browser
    When User open URL "http://admin-demo.nopcommerce.com/login"
    And User enter email as "admin@yourstore.com" and password as "admin"
    And Click on Login
    Then Page title should be "Dashboard / nopCommerce administration"
    When User click on Log out link
    Then Page title should be "Your store. Login"
    And Close the browser

  @tag2
  Scenario Outline: Login Data Driven
    Given User launch Chrome browser
    When User open URL "http://admin-demo.nopcommerce.com/login"
    And User enter email as "<email>" and password as "<password>"
    And Click on Login
    Then Page title should be "Dashboard / nopCommerce administration"
    When User click on Log out link
    Then Page title should be "Your store. Login"
    And Close the browser

    Examples:
      | email                | password |
      | admin@yourstore.com  | admin    |
      | admin1@yourstore.com | admin123 |