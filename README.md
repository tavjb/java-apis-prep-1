## Task

Build a counting system that allows the user to create a counter that writes to a txt
file until one of two conditions are matched:
1. The counter's expiration time has arrived
2. The counter reached it's goal

Additionally, every 1 minute, a cleaning task will delete all the finished counters (This task deletes all the counters that reached their goal, not the ones that
expired before reaching their goal)

The counting system will display the following options:

Hello! Please choose an action: 1 - add new counter, 2 - reactivate dormant counter, 3 - view all counters, 4 - exit

1:
  Please enter the counter goal: <goal> : 400
  Please enter the counter name: <name> : "counter_1"
  Please enter the counter expiration date: <year> <month> <day> <hour> <minute> <second> : 2022 01 23 15 00 00

2: 
  Please enter the counter name: <name> : "counter_1"
  Please enter the counter expiration date: <year> <month> <day> <hour> <minute> <second> : 2022 01 23 15 00 00

3:
  List[<Counter details> (Java object)]
  
4:
  Thank you!
  Process finished with code 0
