insert into User(id,email, is_admin, password) values (default,'dominik.dusinski@gmail.com', true,'dd');
insert into User(id,email, is_admin, password) values (default,'dusiud@gmail.com', false,'');

insert into Calendar_Event(event_id,title, start, end, user_Id, is_Enabled, event_Type)
values (default ,'event1','2020-08-21','2020-08-23',1, true,'HOLIDAY' );

insert into Calendar_Event(event_id,title, start, end, user_Id, is_Enabled, event_Type)
values (default,'event1', '2020-08-21','2020-08-25',1, true,'TRAINING');



