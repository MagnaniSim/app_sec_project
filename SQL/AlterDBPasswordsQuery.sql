USE library;
SELECT * FROM Users;
SELECT * FROM Librarian;

ALTER TABLE Users MODIFY COLUMN UserPass VARCHAR(100);
UPDATE Users SET UserPass="$2a$10$TIEoJOWuSBELiPf4e8UixOLSz/QOUcrwERWtTTysnHNP0RCWU73Xa" WHERE UserID=1;
UPDATE Users SET UserPass="$2a$10$ot2ZQ0ydm6LVlHZOlVfkcOrRoz0Yh4U1.1sthzihJCpiBLv7SZlUu" WHERE UserID=2;
UPDATE Users SET UserPass="$2a$10$PixJsis2fFU1hJWWGaA8Oe9VC8g4YcoXGgOqqJgJTECNXqAMTPksC" WHERE UserID=3;

ALTER TABLE Librarian MODIFY COLUMN Password VARCHAR(100);
UPDATE Librarian SET Password="$2a$10$ea/BVdpIJmPCMjjEyQcm7ePBWQvGmVi0v3DbyRySPfLBktjGk8jpe" WHERE LibrarianID=1;
UPDATE Librarian SET Password="$2a$10$Ax9lNqv0oXXZjBznrE67NOuFARpl/7Xbzmh9kNqMNjEjdI/SYr.ne" WHERE LibrarianID=2;