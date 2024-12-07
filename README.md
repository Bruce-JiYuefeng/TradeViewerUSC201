feel free to change!
it would be great if you guys can list down what you are working on.


## Setup-MySQL
### Download Instructions
Download and install MySQL server community 8.0 (or later) and MySQL Workbench 8.0 (or later)<br />
During install options, select "Custom" and ensure server and workbench are both selected<br />

### Configuration options
*Everything is mostly default, leave as-is<br />
Legacy authentication<br />
Server port 3306<br />
Use "root" for both user and password<br />

### Creating the database
Open MySQL workbench<br />
Open the db.sql file<br />
Run/execute the file<br />

## Database Design
### Datatypes
Used DATETIME over TIMESTAMP because TIMESTAMP only allows up to dates in 2038. 2038 problem<br />
DATETIME format is 'YYYY-MM-DD HH:MM:SS'
Used DECIMAL over FLOAT/DOUBLE for exact precision<br />
See [documentation](https://dev.mysql.com/doc/refman/8.4/en/data-types.html) for details<br />


！！！Just created a new empty project. Please place code to proper place.
