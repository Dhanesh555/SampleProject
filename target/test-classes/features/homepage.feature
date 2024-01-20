Feature: To test all the features in home page

"""
The make my trip home page has varius set of functionalities and are being test automated in this feature.
"""


Background: To navigate to the url

Given user is on the "MakeMyTrip" login page

Scenario: Verify the user can view all the functional page headers

Then user is able to see the make my trip logo icon
And the following page headers are visible
|Flights|
|Hotels|
|Homestays & Villas|
|Holiday Packages|
|Trains|
|Buses|
|Cabs|
|Forex Card & Currency|
|Travel Insurance|