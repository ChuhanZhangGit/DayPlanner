# Android day planner


## Overview

Day Planner app is for planning daily tasks on the customizable time slot basis. It provides a template to assign daily to-dos in a convinient template.  

App is still under development.

## Development plan

1. Choose architectureal pattern.   Design app with MVVM pattern.
2. Build database - done
### To dos 
1. dateOfTask column references a foreign key but it is not part of an index. This may trigger full table scans whenever parent table is modified so you are highly advised to create an index that covers this column.  
   Remember to optimize the foreign key implementation of the one to many relation. 

2. Write method to input and retrieve data from DAO.

3. Explore dependency injection tool Dagger2 that allow injection of database and repository. This allows easier testing of the code.

3. Build main API
### To dos
1. create custom tool bar with drawer that allow select date from calenar
2. link date select with main list view.
