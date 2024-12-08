# Backend and Frontend Workspaces

**Note:** Frontend and backend projects now have separate workspaces for better organization. Follow the setup instructions below to get started.

---

## Backend Setup

### 1. Download Apache Maven
- Download Apache Maven 3.9.9 from the official website: [Maven Downloads](https://maven.apache.org/download.cgi)

---

### 2. Import the Backend Project
1. **Clean the `backend` folder**:
   - Delete all files in the `backend` folder except `README.md`.
2. **Import the project**:
   - Place the provided zip file into the `./backend/` directory and extract it.
3. **Resolve `pom.xml` errors**:
   - If there are errors in the `pom.xml` file, go to:
     - `Preferences` → `Maven`
     - Select all **DOWNLOAD** options.
4. **Note on Dependencies**:
   - Dependencies such as `gson` and `sql connector` are now managed via the `pom.xml` file.
   - For backup purposes, these files are also available in:
     ```
     backend/20124fallFP/src/main/webapp/WEB-INF/lib
     ```

---

### 3. Deploy the Backend Code
1. **Frontend Integration**:
   - After building the React frontend project, copy the generated static files (`build/` folder contents) into:
     ```
     src/main/webapp/static/
     ```
   - Alternatively, send the files via Discord for inclusion.
2. **Build the backend project**:
   - Run the following command in the backend project directory:
     ```bash
     mvn clean package
     ```
   - This will generate a `.war` file in the `target/` folder.
3. **Deploy the `.war` file**:
   - Copy the `.war` file to:
     ```
     Tomcat/webapps/
     ```
   - Start the server and access the web application at:
     ```
     http://localhost:8080/20124fallFP
     ```
   - *Optional*: You can also try right-clicking the `.war` file in Eclipse IDE and selecting **Run on Tomcat** (never tried).

---
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


