# esg-assignment

## Instruction
This assignment contains two projects, data-api and csv-processor <br />

Data-Api is a RESTful data service with a h2 database. It has a customer endpoint with GET and POST methods. Can be accessed via http://localhost:9002/api/customer/123 using GET. Default customer 123 is inserted. Or POST http://localhost:9002/api/customer with customer JSON body. Each reference must be unique, otherwise 422 will be returned if reference already exists. There are constraints on POST object fields , this is to match the database column size. Postcode and customerRef max length 10, all others 30. 

Csv-processor is a scheduled job reading a csv file from a directory and feeding the data into data-api. Cron expression can be found in yml and it's set to run every second. CSV file is in `assignment-csv-processor/files/test.csv`.


## Thought process
First setup data-api project
  - define h2 schema
  - create project structure layers - controller, service, jpa
  - expand further with dto, entity mapper and advice handler
  - create TDD tests
  - implement methods
  - fix tests
  - tidy up code

Then create csv-processor project with scheduler, file reader and data-api service. Create basic project layout first and then write TDD tests. Implement the methods. Fix tests and tidy up code. 
