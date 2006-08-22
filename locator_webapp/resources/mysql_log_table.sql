DROP TABLE LOG_MESSAGE;
DROP TABLE OBJECTATTRIBUTE;
DROP TABLE OBJECT_ATTRIBUTES;
DROP TABLE SIGNIN_USERS;

CREATE TABLE LOG_MESSAGE (
  LOG_ID bigint(200) NOT NULL auto_increment,
  APPLICATION varchar(25) default NULL,
  SERVER varchar(50) default NULL,
  CATEGORY varchar(255) default NULL,
  THREAD varchar(255) default NULL,
  USERNAME varchar(255) default NULL,
  SESSION_ID varchar(255) default NULL,
  MSG text,
  THROWABLE text,
  NDC text,
  CREATED_ON bigint(20) NOT NULL,
  OBJECT_ID varchar(255) default NULL,
  OBJECT_NAME varchar(255) default NULL,
  ORGANIZATION varchar(255) default NULL,
  OPERATION varchar(50) default NULL,
  PRIMARY KEY  (`LOG_ID`)
);

create index LOGID_LOGTAB_INDX on LOGTAB(LOG_ID);
create index APPLICATION_LOGTAB_INDX on LOGTAB(APPLICATION);
create index SERVER_LOGTAB_INDX on LOGTAB(SERVER);
create index THREAD_LOGTAB_INDX on LOGTAB(PRIORITY);
create index CREATED_ON_LOGTAB_INDX on LOGTAB(CREATED_ON);

CREATE TABLE  OBJECT_ATTRIBUTES (
  OBJECT_ATTRIBUTE_ID bigint(200) NOT NULL auto_increment,
  CURRENT_VALUE varchar(255) default NULL,
  PREVIOUS_VALUE varchar(255) default NULL,
  ATTRIBUTE varchar(255) NOT NULL default '',
  PRIMARY KEY  (OBJECT_ATTRIBUTE_ID)
);
create index OAID_INDX on OBJECT_ATTRIBUTE(OBJECT_ATTRIBUTE_ID);


CREATE TABLE  OBJECTATTRIBUTES (
  LOG_ID bigint(200) NOT NULL default '0',
  OBJECT_ATTRIBUTE_ID bigint(200) NOT NULL default '0'
);
create index FK_objectattributes_1 on OBJECTATTRIBUTES(LOG_ID);
create index FK_objectattributes_2 on OBJECTATTRIBUTES(OBJECT_ATTRIBUTE_ID);

CREATE TABLE SIGNIN_USERS (
       USERNAME VARCHAR(255) NOT NULL,
       PASSWORD VARCHAR(255),
       APPLICATION VARCHAR(100) NOT NULL,
       PRIMARY KEY(USERNAME, APPLICATION) );

create index USERNAME_SIGNIN_USERS_INDEX on SIGNIN_USERS(USERNAME);
create index PASSWORD_SIGNIN_USERS_PASSWORD on SIGNIN_USERS(PASSWORD);
create index APPLICATION_SIGNIN_USERS_INDEX on SIGNIN_USERS(APPLICATION);


INSERT INTO signin_users (username, password) VALUES ('admin','pX4bLclVVdFwnQoyStFF3jIVAYI=');
INSERT INTO signin_users (username, password, application) VALUES ('mytest','N/omUzCtg+qoee+x4ttjgIls9jk=', 'upt');
INSERT INTO signin_users (username, password, application) VALUES ('mytest','N/omUzCtg+qoee+x4ttjgIls9jk=', 'csm');


