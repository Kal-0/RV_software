-- Criação do banco de dados
--CREATE DATABASE IF NOT EXISTS ClinicalLab;

--USE ClinicalLab;

-- Criação das tabelas
CREATE TABLE IF NOT EXISTS ATTENDANTS (
    ATTENDANT_ID INT,
    PASSWORD VARCHAR(255),
    ID INT NOT NULL,
    PRIMARY KEY (ID),
    UNIQUE (ATTENDANT_ID)
);

CREATE TABLE IF NOT EXISTS CLIENTS (
    CLIENT_ID INT,
    ID INT NOT NULL,
    PRIMARY KEY (ID),
    UNIQUE (CLIENT_ID)
);

CREATE TABLE IF NOT EXISTS CLIENTS_SERVICE (
    CLIENT_SERVICE_ID BIGINT AUTO_INCREMENT NOT NULL,
    STATUS VARCHAR(255),
    EXAM_REQUEST_ID INT,
    SERVICE_NUMBER_ID INT,
    PRIMARY KEY (CLIENT_SERVICE_ID),
    UNIQUE (SERVICE_NUMBER_ID),
    UNIQUE (EXAM_REQUEST_ID)
);

CREATE TABLE IF NOT EXISTS EXAM_REQUEST (
    EXAM_REQUEST_ID INT AUTO_INCREMENT NOT NULL,
    PAYMENT_METHOD VARCHAR(255) NOT NULL,
    REQUEST_DATE DATE NOT NULL,
    STATUS VARCHAR(255) NOT NULL,
    TOTAL_PRICE FLOAT(53),
    CLIENT_ID INT,
    PRIMARY KEY (EXAM_REQUEST_ID)
);

CREATE TABLE IF NOT EXISTS EXAM_TEST (
    EXAM_TEST_ID INT AUTO_INCREMENT NOT NULL,
    STATUS VARCHAR(255),
    EXAM_ID INT,
    TEST_RESULT_ID INT,
    EXAMS_LIST_ID INT,
    PRIMARY KEY (EXAM_TEST_ID),
    UNIQUE (TEST_RESULT_ID)
);

CREATE TABLE IF NOT EXISTS EXAMS (
    ID INT AUTO_INCREMENT NOT NULL,
    ANALYSIS_TIME INT,
    NAME VARCHAR(255),
    PRICE FLOAT(53),
    REQUIREMENTS VARCHAR(255),
    PRIMARY KEY (ID)
);

CREATE TABLE IF NOT EXISTS PEOPLE (
    ID INT AUTO_INCREMENT NOT NULL,
    BIRTH_DATE DATE,
    CONTACT_EMAIL VARCHAR(255),
    CPF VARCHAR(255),
    NAME VARCHAR(255),
    PRIMARY KEY (ID)
);

CREATE TABLE IF NOT EXISTS SERVICE_NUMBER (
    ID INT AUTO_INCREMENT NOT NULL,
    IS_PRIORITY BOOLEAN NOT NULL,
    NUMBER VARCHAR(255),
    STATUS VARCHAR(255),
    PRIMARY KEY (ID)
);

CREATE TABLE IF NOT EXISTS TEST_RESULT (
    TEST_RESULT_ID INT AUTO_INCREMENT NOT NULL,
    RESULT_CONTENT VARCHAR(255),
    RESULT_DATE DATE NOT NULL,
    PRIMARY KEY (TEST_RESULT_ID)
);

-- Criação das chaves estrangeiras
ALTER TABLE ATTENDANTS
    ADD CONSTRAINT FK_ATTENDANT_PERSON FOREIGN KEY (ID) REFERENCES PEOPLE(ID);

ALTER TABLE CLIENTS
    ADD CONSTRAINT FK_CLIENT_PERSON FOREIGN KEY (ID) REFERENCES PEOPLE(ID);

ALTER TABLE CLIENTS_SERVICE
    ADD CONSTRAINT FK_CLIENT_SERVICE_SERVICE_NUMBER FOREIGN KEY (SERVICE_NUMBER_ID) REFERENCES SERVICE_NUMBER(ID);

ALTER TABLE CLIENTS_SERVICE
    ADD CONSTRAINT FK_CLIENT_SERVICE_EXAM_REQUEST FOREIGN KEY (EXAM_REQUEST_ID) REFERENCES EXAM_REQUEST(EXAM_REQUEST_ID);

ALTER TABLE EXAM_REQUEST
    ADD CONSTRAINT FK_EXAM_REQUEST_CLIENT FOREIGN KEY (CLIENT_ID) REFERENCES CLIENTS(ID);

ALTER TABLE EXAM_TEST
    ADD CONSTRAINT FK_EXAM_TEST_EXAM FOREIGN KEY (EXAM_ID) REFERENCES EXAMS(ID);

ALTER TABLE EXAM_TEST
    ADD CONSTRAINT FK_EXAM_TEST_TEST_RESULT FOREIGN KEY (TEST_RESULT_ID) REFERENCES TEST_RESULT(TEST_RESULT_ID);

ALTER TABLE EXAM_TEST
    ADD CONSTRAINT FK_EXAM_TEST_EXAMS_LIST FOREIGN KEY (EXAMS_LIST_ID) REFERENCES EXAM_REQUEST(EXAM_REQUEST_ID);





-- Populando a tabela PEOPLE
INSERT INTO PEOPLE (BIRTH_DATE, CONTACT_EMAIL, CPF, NAME) VALUES
('1990-01-01', 'john.doe@example.com', '123.456.789-00', 'John Doe'),
('1985-06-15', 'jane.smith@example.com', '987.654.321-00', 'Jane Smith'),
('2000-03-22', 'alice.brown@example.com', '321.654.987-00', 'Alice Brown'),
('1978-11-30', 'bob.white@example.com', '654.321.987-00', 'Bob White'),
('1995-07-10', 'charlie.green@example.com', '789.123.456-00', 'Charlie Green');

-- Populando a tabela CLIENTS
INSERT INTO CLIENTS (CLIENT_ID, ID) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5);

-- Populando a tabela ATTENDANTS
INSERT INTO ATTENDANTS (ATTENDANT_ID, PASSWORD, ID) VALUES
(101, 'password1', 1),
(102, 'password2', 2),
(103, 'password3', 3),
(104, 'password4', 4),
(105, 'password5', 5);

-- Populando a tabela EXAMS
INSERT INTO EXAMS (ANALYSIS_TIME, NAME, PRICE, REQUIREMENTS) VALUES
(24, 'Blood Test', 50.00, '12-hour fasting'),
(48, 'Urine Test', 30.00, 'First morning urine'),
(72, 'X-Ray', 100.00, 'No specific requirements'),
(24, 'MRI', 500.00, 'Doctor prescription'),
(12, 'COVID Test', 150.00, 'No symptoms required');

-- Populando a tabela TEST_RESULT
INSERT INTO TEST_RESULT (RESULT_CONTENT, RESULT_DATE) VALUES
('Normal', '2024-11-01'),
('High Cholesterol', '2024-11-02'),
('Clear', '2024-11-03'),
('Anomaly Detected', '2024-11-04'),
('Positive', '2024-11-05');

-- Populando a tabela EXAM_REQUEST
INSERT INTO EXAM_REQUEST (PAYMENT_METHOD, REQUEST_DATE, STATUS, TOTAL_PRICE, CLIENT_ID) VALUES
('Credit Card', '2024-11-01', 'Completed', 80.00, 1),
('Cash', '2024-11-02', 'Pending', 100.00, 2),
('Debit Card', '2024-11-03', 'Completed', 50.00, 3),
('Credit Card', '2024-11-04', 'In Progress', 200.00, 4),
('Cash', '2024-11-05', 'Completed', 150.00, 5);

-- Populando a tabela SERVICE_NUMBER
INSERT INTO SERVICE_NUMBER (IS_PRIORITY, NUMBER, STATUS) VALUES
(TRUE, 'A001', 'Active'),
(TRUE, 'A002', 'Completed'),
(FALSE, 'B001', 'Active'),
(FALSE, 'B002', 'Pending'),
(TRUE, 'A003', 'Active');

-- Populando a tabela CLIENTS_SERVICE
INSERT INTO CLIENTS_SERVICE (STATUS, EXAM_REQUEST_ID, SERVICE_NUMBER_ID) VALUES
('In Progress', 1, 1),
('Completed', 2, 2),
('Pending', 3, 3),
('In Progress', 4, 4),
('Completed', 5, 5);

-- Populando a tabela EXAM_TEST
INSERT INTO EXAM_TEST (STATUS, EXAM_ID, TEST_RESULT_ID, EXAMS_LIST_ID) VALUES
('Completed', 1, 1, 1),
('Pending', 2, 2, 2),
('In Progress', 3, 3, 3),
('Completed', 4, 4, 4),
('Completed', 5, 5, 5);