# AndoirdToDoList

## To dos 
1. dateOfTask column references a foreign key but it is not part of an index. This may trigger full table scans whenever parent table is modified so you are highly advised to create an index that covers this column.  
   Remember to optimize the foreign key implementation of the one to many relation. 

2. Write method to input and retrieve data from DAO.

3. Explore dependency injection tool Dagger2 that allow injection of database and repository. This allows easier testing of the code.

4. Find out how calendar's database look like?

5. Save the user input on Pause/ Destroy