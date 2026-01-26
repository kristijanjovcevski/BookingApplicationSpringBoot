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
  Scenario Outline: Apartment name must start with a letter
    When the user submits the apartment with "<field>" invalid value "<value>"
      | apartmentName | Good hotel |
      | city          | New York   |
      | description   | Nice view  |
      | pricePerNight | 100        |
      | rating        | 4.5        |
    And the user submits the form
    Then the validation error for "<field>" should be displayed

    Examples:
      | field         | value                                                  |
      | apartmentName | 1                                                      |
      | apartmentName | !apt                                                   |
      | apartmentName | 2.5                                                    |
      | apartmentName | -3.4                                                   |
      | apartmentName | -2                                                     |
      | apartmentName | fieojfiwoaejfwiowejfriowejrfiowejiweiowejrioweriojweio |


  @regression @negative
  Scenario Outline: City must start with a letter and must not be empty
    When the user submits the apartment with "<field>" invalid value "<value>"
      | apartmentName | Good hotel |
      | city          | New York   |
      | description   | Nice view  |
      | pricePerNight | 100        |
      | rating        | 4.5        |
    And the user submits the form
    Then the validation error for "<field>" should be displayed

    Examples:
      | field | value                                                  |
      | city  | 1                                                      |
      | city  |                                                        |
      | city  | -3.7                                                   |
      | city  | -100                                                   |
      | city  | 2.1                                                    |
      | city  | fieojfiwoaejfwiowejfriowejrfiowejiweiowejrioweriojweio |


  @regression @negative
  Scenario Outline: Description of the apartment must start with a letter and must not be empty
    When the user submits the apartment with "<field>" invalid value "<value>"
      | apartmentName | Good hotel |
      | city          | New York   |
      | description   | Nice view  |
      | pricePerNight | 100        |
      | rating        | 4.5        |
    And the user submits the form
    Then the validation error for "<field>" should be displayed

    Examples:
      | field       | value                                                  |
      | description | 1                                                      |
      | description |                                                        |
      | description | -3.7                                                   |
      | description | -100                                                   |
      | description | 2.1                                                    |
