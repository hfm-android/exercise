insert into authority(id,type) values (1,'ADMIN');
insert into authority(id,type) values (2,'USER');
insert into user(id,username,password) values (3,'admin','$2a$10$oIlMw.Zd89xMG2NoXfmEZu0OZvoYqQQoZchOVIH.Q/QhsBoysX7xO');
insert into user(id,username,password) values (4,'user','$2a$10$oIlMw.Zd89xMG2NoXfmEZu0OZvoYqQQoZchOVIH.Q/QhsBoysX7xO');
insert into user_authority(fk_user_id,fk_authority_id) values (3,1);
insert into user_authority(fk_user_id,fk_authority_id) values (4,2);
insert into category(id,name) values (5,'clothes');
insert into product(id,name,brand,description,price) values (6,'jacket','LC','a nice jacket','35');
insert into product_category(fk_product_id,fk_category_id) values (6,5);
alter sequence HIBERNATE_SEQUENCE restart with 7