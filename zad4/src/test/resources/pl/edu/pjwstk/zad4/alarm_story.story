
Scenario: Alarm should ring when it reaches given time

Given there is a <time>
When clock was given an <alarm>
Then clock should ring: <summary>

Examples:
|time|alarm|summary|
|"210001"|"210001"|true|
|"210001"|"210001"|false|
|"210001"|"203500"|false|
|"210001"|"210002"|true|
|"222431"|"222431"|true|
|"222431"|"222431"|false|
|"222431"|"222431"|false|
|"222431"|"222431"|false|
|"222431"|"210001"|false|
|"222431"|"222433"|true|
|"222431"|"222431"|false|