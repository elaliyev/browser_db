# browser_db

## What is it?

Implemented backend rest full services for saving, updating, listing and deleting connection details MySQL
relational database.

● name - custom name of the database instance

● hostname - hostname of the database

● port - port where the database runs

● databaseName - name of the database

● username - username for connecting to the database

● password - password for connecting to the database

### Connection details themselves should be stored in database of your choice.

API should support the following operations:

● Listing schemas (if your selected database supports them)

● Listing tables

● Listing columns

● Data preview of the table

● Single endpoint for statistics about each column: min, max, avg value of the
column.

● Single endpoint for statistics about each table: number of records, number of attributes.



## documentation.  http://localhost:8091/swagger-ui/index.html 

# Steps.

1. to create connection, we must first create vendor and pass vendor id to the "/connection/save" request
2. to work with db-schema-controller, db-statistic controller and db-table-controller we have to call "connection/connect" (request parameter is "connection name") endpoint. after connection we can work with
db-schema-controller, db-statistic controller and db-table-controller endpoints 
