feel free to change!
it would be great if you guys can list down what you are working on.


## Setup-MySQL
### Download Instructions
Download and install MySQL server community 8.0 (or later) and MySQL Workbench 8.0 (or later)
During install options, select "Custom" and ensure server and workbench are both selected

### Configuration options
*Everything is mostly default, leave as-is
Legacy authentication
Server port 3306
Use "root" for both user and password


## Database Design
### Datatypes
Used DATETIME over TIMESTAMP because TIMESTAMP only allows up to dates in 2038. 2038 problem
Used DECIMAL over FLOAT/DOUBLE for exact precision
See [documentation](https://dev.mysql.com/doc/refman/8.4/en/data-types.html) for details