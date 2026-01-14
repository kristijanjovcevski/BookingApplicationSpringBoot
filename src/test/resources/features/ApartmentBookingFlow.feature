Feature: Apartment Booking Flow
  Scenario: User adds new apartment and sees it on the list
    Given the user is on the add apartment page
    When the user submits valid apartment details
    Then user is redirected to apartments page and the apartment is added to the apartment list
