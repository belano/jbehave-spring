Scenario:  2+2

Given a variable x with value 2
When I add 2 to x
Then x should equals to 4
And the calculator should not be in error

Scenario: Undefined variable displays error message

When I add 5 to y
Then the calculator should display the message 'Variable <y> is not defined'