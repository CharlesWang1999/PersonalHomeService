-- Create test table for PersonalHome
CREATE TABLE IF NOT EXISTS greeting (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    message VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Insert sample data
INSERT INTO greeting (message) VALUES ('Hello from MySQL!');
