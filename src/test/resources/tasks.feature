Feature: Should test tasks service

    Scenario: Should return all tasks
        When request for all tasks is made
        Then task list contains 5 elements