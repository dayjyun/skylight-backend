Feature: Flights functionalities

  # Public
  Scenario: User is able to view a list of flights
    Given a list of flights are available
    When I search for flights
    Then I can see a list of flights

  # Public
  Scenario: User is able to view details of a flight
    Given a flight is available
    When I search by flight ID
    Then I can see the flight details

  # Private
  Scenario: Admin is able to view all tickets for a flight
    Given a flight has a list of tickets
    When I search for the list of tickets for the flight
    Then I can see the list of tickets for the flight

  #Private
  Scenario: User is able to create tickets for a flight
    Given a flight is available
    When I create a ticket
    Then I can see the details of the newly created ticket

  # Private
  Scenario: User is able to delete a flight they created
    Given a flight is available
    When I delete the flight
    Then I can see the details of the deleted flight