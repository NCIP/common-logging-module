/*L
   Copyright Ekagra Software Technologies

   Distributed under the OSI-approved BSD 3-Clause License.
   See http://ncip.github.com/common-logging-module/LICENSE.txt for details.
L*/

create table item (id int(11) not null primary key,
name varchar(255),
manufacturer varchar(255),
price float,
customer_id int(11)
)