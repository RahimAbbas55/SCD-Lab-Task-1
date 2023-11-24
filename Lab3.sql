CREATE DATABASE lab3

create table hotel
(
    hotelno varchar(10) primary key,
    hotelname varchar(20),
    city varchar(20),
)

insert into hotel values('fb01', 'Grosvenor', 'London');
insert into hotel values('fb02', 'Watergate', 'Paris');
insert into hotel values('ch01', 'Omni Shoreham', 'London');
insert into hotel values('ch02', 'Phoenix Park', 'London');
insert into hotel values('dc01', 'Latham', 'Berlin');

 -- create a table named hotel

create table room
(
    roomno numeric(5),
    hotelno varchar(10),
    type varchar(10),
    price decimal(5,2),
    primary key (roomno, hotelno),
    foreign key (hotelno) REFERENCES hotel(hotelno)
)

insert into room values(501, 'fb01', 'single', 19);
insert into room values(601, 'fb01', 'double', 29);
insert into room values(701, 'fb01', 'family', 39);
insert into room values(1001, 'fb02', 'single', 58);
insert into room values(1101, 'fb02', 'double', 86);
insert into room values(1001, 'ch01', 'single', 29.99);
insert into room values(1101, 'ch01', 'family', 59.99);
insert into room values(701, 'ch02', 'single', 10);
insert into room values(801, 'ch02', 'double', 15);
insert into room values(901, 'dc01', 'single', 18);
insert into room values(1001, 'dc01', 'double', 30);
insert into room values(1101, 'dc01', 'family', 35);

create table guest
(
    guestno numeric(5),
    guestname varchar(20),
    guestaddress varchar(50),
    primary key (guestno)
)

insert into guest values(10001, 'John Kay', '56 High St, London');
insert into guest values(10002, 'Mike Ritchie', '18 Tain St, London');
insert into guest values(10003, 'Mary Tregear', '5 Tarbot Rd, Aberdeen');
insert into guest values(10004, 'Joe Keogh', '2 Fergus Dr, Aberdeen');
insert into guest values(10005, 'Carol Farrel', '6 Achray St, Glasgow');
insert into guest values(10006, 'Tina Murphy', '63 Well St, Glasgow');
insert into guest values(10007, 'Tony Shaw', '12 Park Pl, Glasgow');

create table booking
(
    hotelno varchar(10),
    guestno numeric(5),
    datefrom date,
    dateto date,
    roomno numeric(5),
    primary key (hotelno, guestno, datefrom),
    foreign key (roomno, hotelno) REFERENCES room(roomno, hotelno),
    foreign key (guestno) REFERENCES guest(guestno)
)

 

insert into booking values('fb01', 10001, '04-04-01', '04-04-08', 501);
insert into booking values('fb01', 10004, '04-04-15', '04-05-15', 601);
insert into booking values('fb01', 10005, '04-05-02', '04-05-07', 501);
insert into booking values('fb01', 10001, '04-05-01', null, 701);
insert into booking values('fb02', 10003, '04-04-05', '10-04-04', 1001);
insert into booking values('ch01', 10006, '04-04-21', null, 1101);
insert into booking values('ch02', 10002, '04-04-25', '04-05-06', 801);
insert into booking values('dc01', 10007, '04-05-13', '04-05-15', 1001);
insert into booking values('dc01', 10003, '04-05-20', null, 1001);

--LAB QUERIES
--Q1
SELECT * FROM hotel
where city = 'London'
ORDER BY hotelno , hotelname DESC

--Q2
SELECT * from hotel 
where hotelname like '%__t%'

--Q3
SELECT * from booking
where dateto IS NULL

--Q4
SELECT g.guestname , g.guestaddress from guest AS g
where g.guestname like 'John%' and  g.guestname like '%Farrel' and g.guestaddress like '%Glasgow'

--Q5
SELECT b.roomno from booking as b
where datefrom BETWEEN '2005-04-04' and '2010-12-31'

--Q6(List all the rooms of hotel no. 1001 which are of single type and the price is between 20 to 40.)
SELECT * from room AS r
where type = 'single' and price BETWEEN 20 and 40

--Q7(List the room no. along with its hotel no. whose price is maximum)
SELECT roomno , hotelno from room
where price = ( SELECT max(price) from room)

--Q8
SELECT 'The hotel whose is is ' + hotelno + ' is in ' + city + ' and its name is ' + hotelname
FROM hotel

SELECT CONCAT('The hotel whose is is ' , hotelno , ' is in ' + city , ' and its name is ' , hotelname)
FROM hotel

--Q9
SELECT h.hotelname from hotel as h
inner JOIN room AS r
on h.hotelno = r.hotelno 
where (r.type = 'double' and r.type = 'family')

--Q10(List all the hotels which have single as well as double type rooms but no family type room)
SELECT h.hotelname ,h.hotelno,r.roomno from hotel as h
inner JOIN room AS r
on h.hotelno = r.hotelno 

where (r.type = 'double' or r.type = 'single') and r.type <> 'family'
--Q11(. List all the rooms which have been booked by either guest no. 10003 or by guest no.1007 but not by guest no 1001.)
select b.hotelno , b.roomno , b.guestno, b.roomno from booking AS b
where b.guestno = '10003' or b.guestno = '1007' and b.guestno <> '1001'



SELECT * from room
SELECT * from guest
SELECT * from hotel
SELECT * from booking
