Feature: Test rest countries api
  Background:
    Given User can send request

  Scenario: Search by country name
    When User search "turkey" with "name" endpoint
    Then User should see the relevant "turkey" result

  Scenario: Search by currency
    When User search "peso" with "currency" endpoint
    Then User should see "peso" inside currency name