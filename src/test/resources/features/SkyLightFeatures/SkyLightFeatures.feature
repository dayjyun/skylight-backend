Feature: SkyLight API Functionalities

  # Public
  Scenario: User is able to view a list of airports
    Given a list of airports are available
    When I search for airports
    Then I can see a list of airports

  Scenario: User is able to view details of an airport
    Given an airport is available
    When I search by airport ID
    Then I can see the airport details

  Scenario: Use is able to view a list of arriving flights
    Given I can see a list of arriving flights are available
    When I search for arriving flights
    Then I can see a list of arriving flights