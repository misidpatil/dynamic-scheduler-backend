CREATE TABLE scheduler_config (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cron_expression VARCHAR(255) NOT NULL,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    last_triggered_time TIMESTAMP NULL,
    last_completion_time TIMESTAMP NULL
);
