-- Create the database
CREATE DATABASE web_app;

-- Use the created database
USE web_app;

-- Create the table for user credentials
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,        -- Unique ID for each user
    username VARCHAR(255) NOT NULL UNIQUE,    -- Username (must be unique)
    password_hash VARCHAR(255) NOT NULL,      -- Hashed password
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Record creation time
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP -- Record last update time
);

-- Create the table for user profiles (linked to the users table)
CREATE TABLE user_profiles (
    id INT AUTO_INCREMENT PRIMARY KEY,        -- Unique ID for each profile
    user_id INT NOT NULL,                     -- Foreign key to the users table
    first_name VARCHAR(255),                  -- First name of the user
    last_name VARCHAR(255),                   -- Last name of the user
    email VARCHAR(255) NOT NULL UNIQUE,       -- Email (must be unique)
    phone VARCHAR(20),                        -- Optional phone number
    bio TEXT,                                 -- Optional bio for the user
    profile_pic_url VARCHAR(500),             -- URL for the profile picture
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Profile creation time
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- Profile last update time
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE -- Cascade deletes
);

-- Create the table for roles (e.g., admin, user)
CREATE TABLE roles (
    id INT AUTO_INCREMENT PRIMARY KEY,        -- Unique ID for each role
    role_name VARCHAR(50) NOT NULL UNIQUE,    -- Role name (must be unique)
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Role creation time
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP -- Role last update time
);

-- Create the table for mapping users to roles (many-to-many relationship)
CREATE TABLE user_roles (
    id INT AUTO_INCREMENT PRIMARY KEY,        -- Unique ID for each record
    user_id INT NOT NULL,                     -- Foreign key to the users table
    role_id INT NOT NULL,                     -- Foreign key to the roles table
    assigned_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Role assignment time
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE, -- Cascade deletes
    FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE -- Cascade deletes
);

-- Insert sample roles
INSERT INTO roles (role_name) VALUES ('admin'), ('user'), ('moderator');

-- Insert a sample user and assign them a role
-- Replace 'hashed_password_here' with an actual hashed password
INSERT INTO users (username, password_hash) VALUES ('sampleUser', 'hashed_password_here');
INSERT INTO user_profiles (user_id, first_name, last_name, email) VALUES (1, 'Sample', 'User', 'sampleuser@example.com');
INSERT INTO user_roles (user_id, role_id) VALUES (1, 1); -- Assign admin role to sampleUser
