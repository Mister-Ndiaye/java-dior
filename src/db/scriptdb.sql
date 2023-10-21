-- Create the User table
CREATE TABLE Users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);

-- Create the Keys table
CREATE TABLE User_keys (
    key_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    public_key TEXT,
    private_key TEXT,
    is_secret BOOLEAN NOT NULL,
    FOREIGN KEY (user_id) REFERENCES User(user_id)
);

-- Create the EncryptedMessages table
CREATE TABLE EncryptedMessages (
    message_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    message_text TEXT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES User(user_id)
);
