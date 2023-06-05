Feature: Tickets functionalities
  # Public
  Scenario: User is able to book a flight
    Given a ticket is available
    When I book the ticket
    Then I can see the details of the ticket I booked

  # Public/Private
  Scenario: User is able to see ticket details
    Given I have booked a ticket
    When I search for the ticket
    Then I can see the details for that ticket

  # Private
  Scenario: Admin is able to delete a ticket
    Given a ticket is available
    When I delete the ticket
    Then I can see the details of the deleted ticket