Feature: To test all the features in home page

"""
The make my trip home page has varius set of functionalities and are being test automated in this feature.
"""

  Background: To navigate to the url

    Given user is on the "MakeMyTrip" login page

  @verifyPageHeaders
  Scenario: Verify the user can view all the functional page headers

    Then user is able to see the make my trip logo icon
    And the following page headers are visible
      | Flights               |
      | Hotels                |
      | Homestays & Villas    |
      | Holiday Packages      |
      | Trains                |
      | Buses                 |
      | Cabs                  |
      | Forex Card & Currency |
      | Travel Insurance      |


  @searchOneWayFlight
  Scenario Outline: Verify the user can search for a one way flight and get the results
    Given the home page has the following radio buttons
      | One Way | Round Trip | Multi City |
    When the user click on the one way radio button
    And Search for the flights with "<from>" "<to>" <adult> <children> <infant> "<class>" details and departure date current date
    Then the user should be able to get the result flights from "<from>" to "<to>"
    
    Examples:
      | from      | to        | adult | children | infant | class                   |
      | Kochi     | Bengaluru | 2     | 3        | 1      | Economy/Premium Economy |
      | Bengaluru | Kochi     | 1     | 4        | 2      | Premium Economy         |
      | Mumbai    | Bengaluru | 3     | 3        | 3      | Business                |

