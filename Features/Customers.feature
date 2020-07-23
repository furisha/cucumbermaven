Feature: Custumers

  Background: Below are the common steps for each scenario
    Given User launch Chrome browser
    When User open URL "http://admin-demo.nopcommerce.com/login"
    And User enter email as "admin@yourstore.com" and password as "admin"
    And Click on Login
    Then User can view Dashboard

  Scenario: Add a new Customer
    When User click on customers Menu
    And Click on customers Menu Item
    And Click on Add new button
    Then User can view Add new customer page
    When User enter customer info
    And Click on Save button
    Then User can view confirmation message "The new customer has been added successfully"
    And Close the browser

  Scenario: Search customer by EMailID
    When User click on customers Menu
    And Click on customers Menu Item
    And Enter customer EMail
    When Click on search button
    Then User should found EMail in the Search table
    And Close the browser

  Scenario: Search customer by Name
    When User click on customers Menu
    And Click on customers Menu Item
    And Enter customer FirstName
    And Enter customer LastName
    When Click on search button
    Then User should found Name in the Search table
    And Close the browser