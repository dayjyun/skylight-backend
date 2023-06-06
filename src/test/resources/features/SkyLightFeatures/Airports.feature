Feature: Airports functionalities

  # Public
  Scenario: User is able to view a list of airports
    Given a list of airports are available
    When I search for airports
    Then I can see a list of airports

  # Public
  Scenario: User is able to view details of an airport
    Given an airport is available
    When I search by airport ID
    Then I can see the airport details

  # Public
  Scenario: User is able to search an airport by its code
    Given there is an airport is available
    When I search by airport code
    Then I can see the details for the airport

  # Private
  Scenario: User is able to schedule flight origin
    Given I am an admin
    When I create a flight origin
    Then I can see the details of the flight created

  # Private
  Scenario: User is able to schedule flight destination
    Given I am an admin
    When I create a flight destination
    Then I can see the details of the flight destination

  # Public
  Scenario: User is able to view a list of arriving flights
    Given a list of arriving flights are available
    When I search for arriving flights
    Then I can see a list of arriving flights

  # Public
  Scenario: User is able to view a list of departure flights
    Given a list of departing flights are available
    When I search for departing flights
    Then I can see a list of departing flights