# browser_db

## documentation.  http://localhost:8091/swagger-ui/index.html 

# Steps.

1. to create connection, we must first create vendor and pass vendor id to the "/connection/save" request
2. to work with db-schema-controller, db-statistic controller and db-table-controller we have to call "connection/connect" (request parameter is "connection name") endpoint. after connection we can work with
db-schema-controller, db-statistic controller and db-table-controller endpoints 
