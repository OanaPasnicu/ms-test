Feature: Adding products to a customer's bag
  In order to buy the products I want
  As a customer
  I need to be able to add products to my bag

  Scenario: Add shirt to bag and view bag
    Given I have added a shirt to my bag
    When I view the contents of my bag
    Then I can see the contents of the bag include a shirt