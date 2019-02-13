Feature: General business rules

  Scenario: Item degrades by one per day
    Given the following items
      | name  | quality | sellIn   |
      | item1 | 10      | tomorrow |
      | item2 | 5       | tomorrow |
      | item3 | 3       | tomorrow |
    When 1 day passed
    Then item1 has quality 9 and sell by date today
    And item2 has quality 4 and sell by date today
    And item3 has quality 2 and sell by date today