@apartment @management @ui
Feature: Add New Apartment
  As a property manager or landlord
  I want to add new apartments to the system
  So that I can manage my rental properties

  Background:
    Given the user is on the add apartment page

  @smoke @regression @positive
  Scenario: Successfully add a new apartment with valid details
    When the user submits valid apartment details
    Then user is redirected to apartments page and the apartment is added to the apartment list

#  @regression @positive @boundary
#  Scenario: Add apartment with minimum required fields
#    When the user submits valid apartment details
#    Then user is redirected to apartments page and the apartment is added to the apartment list

  @regression @negative
  Scenario Outline: Add apartment form rejects invalid inputs
    When the user attempts to create apartment with completely invalid data
      | apartmentName   | <apartmentName>   |
      | city            | <city>            |
      | description     | <description>     |
      | pricePerNight   | <pricePerNight>   |
      | rating          | <rating>          |
    And the user submits the form
    Then validation errors should be displayed

    Examples:
      | apartmentName | city | description | pricePerNight | rating | field         | expected_error                       |
      | 1             |      | true        | 2.5           | Five   | apartmentName | Apartment name must be a text!       |
      | Luxury Apt    |      | Nice place  | 100           | 4.5    | city          | City name is required!               |
      | Luxury Apt    | Skopje |           | 100           | 4.5    | description   | Description must be a text!          |
      | Luxury Apt    | Skopje | Nice place| 2.5           | 4.5    | pricePerNight | Price must be an integer!            |
      | Luxury Apt    | Skopje | Nice place| 100           | Five   | rating        | Rating must be text!                 |


#  @negative
#  Scenario Outline: Cannot add apartment with invalid data
#    Given the user is on the add apartment page
#    When the user submits apartment with invalid <field> as <value>
#    Then an error message "<error>" should be displayed
#    And the user remains on the add apartment page
#
#    Examples:
#      | field       | value           | error                          |
#      | price       | -100            | Price must be positive         |
#      | bedrooms    | 0               | Bedrooms must be at least 1    |
#      | email       | invalid-email   | Please enter a valid email     |
#      | name        |                 | Apartment name is required     |
#
#  @edge-case
#  Scenario: Add apartment with special characters in name
#    Given the user is on the add apartment page
#    When the user submits apartment with name "Luxury & Co. Apartment #123"
#    Then user is redirected to apartments page and the apartment is added to the apartment list
#    And the apartment name appears correctly as "Luxury & Co. Apartment #123"