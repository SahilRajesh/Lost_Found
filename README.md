# Lost_Found
Steps to use these files:
1. Download zip file.
2. Extract zip file.
3. Copy and paste the files into project folder.
4. Open eclipse.
5. Run the mainPage.java file
6. NOTE: Lost.jpg is for ImageIcon.

7. All the SQL queries done so far...
8. Create database projectLost;
9. use projectLost;
   CREATE TABLE users (
    name VARCHAR(100),
    phone VARCHAR(20),
    usn VARCHAR(30),
    branch VARCHAR(50),
    gender VARCHAR(10)
);

CREATE TABLE admins(
	id int auto_increment primary key,
        name varchar(50),
        usn varchar(50)
);

CREATE TABLE item_complaint (
    id INT PRIMARY KEY AUTO_INCREMENT,
    category VARCHAR(50) NOT NULL,
    complaint TEXT NOT NULL,
    last_seen_location TEXT NOT NULL,
    unique_mark TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE item_history (
	id int auto_increment primary key,
    category varchar(50),
    isFound boolean default False
);

INSERT INTO admins(name,usn)
    VALUES("Sahil","112"),("Ranjitha","106");
