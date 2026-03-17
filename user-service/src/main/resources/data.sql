-- Test User
MERGE INTO user_entity (`id`, `name`, `email`, `location`, `username`, `password`, `number`)
    KEY(`id`)
    VALUES
        (1, 'John Doe', 'john.doe@example.com', 'New York', 'johndoe', 'password123', '9876543210'),
        (2, 'Jane Smith', 'jane.smith@example.com', 'London', 'janesmith', 'securePass!45', '1234567890');