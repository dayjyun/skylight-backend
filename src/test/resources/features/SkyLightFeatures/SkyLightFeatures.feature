Feature: SkyLight API Functionalities

  # Users
  # PRIVATE
  Scenario: User is able to view their account details
    Given my account is available
    When I go to my profile
    Then I can see my account details

  # Private
  Scenario: User is able to see a list of flights they booked
    Given a list of flights are booked
    When I search for the list of flights I booked
    Then I can see the list of flights I booked

  # PRIVATE
  Scenario: User is able to edit their account details
    Given my account is available
    When I edit my profile
    Then I see my profile is updated

  # Airports
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
  Scenario: User is able to view a list of arriving flights
    Given a list of arriving flights are available
    When I search for arriving flights
    Then I can see a list of arriving flights

  # Public
  Scenario: User is able to view a list of departure flights
    Given a list of departing flights are available
    When I search for departing flights
    Then I can see a list of departing flights

  # Flights
  # Public
  Scenario: User is able to view a list of flights
    Given a list of flights are available
    When I search for flight
    Then I can see a list of flights

  # Private
  Scenario: User is able to schedule a flight
    Given I am an admin
    When I create a flight
    Then I can see the details of the flight created

  # Public
  Scenario: User is able to view details of a flight
    Given a flight is available
    When I search by flight ID
    Then I can see the flight details

  # Private
  Scenario: User is able to view all tickets for flight
    Given a flight has a list of tickets
    When I search for the list of tickets for the flight
    Then I can see the list of tickets for the flight

  #Private
  Scenario: User is able to create tickets for a flight
    Given I am an admin
    When I create a ticket
    Then I can see the details of the newly created ticket

  # Tickets
  # Private
  Scenario: User is able to book a flight
    Given a ticket is available
    When I book the ticket
    Then I can see the details of the ticket I booked

  # Private
  Scenario: User is able to see ticket details
    Given I have booked a ticket
    When I search for the ticket
    Then I can see the details for that ticket

  # Delete
  # Private Ticket
  Scenario:  User is able to delete their booked ticket
    Given I have bookted a ticket
    When I delete the ticket
    Then I can see the details of the deleted ticket
    
    # Private Flight
  Scenario: User is able to delete a flight they created
    Given I have admin access to the flight
    When I delete the flight
    Then I can see the details of the deleted flight
