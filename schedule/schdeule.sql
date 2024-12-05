CREATE TABLE schedule (
                          id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                          title VARCHAR(255) NOT NULL,
                          author VARCHAR(255) NOT NULL,
                          password VARCHAR(255) NOT NULL,
                          content TEXT NOT NULL,
                          created_at DATETIME NOT NULL,
                          updated_at DATETIME NOT NULL
);