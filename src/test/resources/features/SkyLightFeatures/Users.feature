Feature: Users functionalities

  # Public
  Scenario: User is able to register
    Given an email is not registered
    When a user registers with a unique email and password
    Then a new user account is created

  # Public
  Scenario: User is able to login
    Given an email is in the database
    When a user logs in
    Then a JWT key is returned

  # My Profile

  # Private
  Scenario: User is able to view their account details
    Given I am logged in
    When I go to my profile
    Then I can see my account details

  # Private
  Scenario: User is able to see a list of flights they booked
    Given a list of flights are booked
    When I search for the flights I booked
    Then I can see the flights I booked

  # Private
  Scenario: Admin is able to see a list of flights they scheduled
    Given I have a list of flights scheduled
    When I search for the flights I scheduled
    Then I can see the flights I scheduled

  # Private
  Scenario: User is able to submit request to become a pilot
    Given I am logged in
    When I submit a request to become a pilot
    Then I am a pilot

  # Private
  Scenario: User is able to edit their account details
    Given I am logged in
    When I edit my profile
    Then I see my profile is updated