Feature: SkyLight API Functionalities

  # Public
  Scenario: User is able to view a list of airports
    Given a list of airports are available
    When I search for a airports
    Then I can see a list of airports

  Scenario: User is able to view details of an airport
    Given an airport is available
    When I search by airport ID
    Then I can see the airport details