INSERT INTO users(username,password,role,active)
VALUES ('admin','$2a$10$hashdemo','ADMIN',1);

INSERT INTO players(name,score,win_count,total_match,active)
VALUES ('Nguyen Van A',100,5,10,1);

INSERT INTO games(name,category,online)
VALUES ('LOL','MOBA',1);
