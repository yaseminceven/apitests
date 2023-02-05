Feature: Test rest countries api
  Scenario: Search by country name
    When User search "turkey" with search by country endpoint
    Then User should see the relevant "turkey" result