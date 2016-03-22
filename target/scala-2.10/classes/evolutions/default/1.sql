# Users schema

# --- !Ups

   CREATE TABLE permission (
         permission_type int    NOT NULL ,
         permission_desc varchar(50)    NOT NULL ,
         CONSTRAINT permission_pk PRIMARY KEY (permission_type)
    );

-- Table: privilege
CREATE TABLE privilege (
    study_id int    NOT NULL ,
    permission_type int    NOT NULL ,
    username varchar(100)    NOT NULL ,
    CONSTRAINT privilege_pk PRIMARY KEY (study_id)
);

-- Table: session
CREATE TABLE session (
    signal_seq int    NOT NULL ,
    subject_seq int    NOT NULL ,
    session_no int    NOT NULL ,
    run_no int    NOT NULL ,
    session_name varchar(100)    NOT NULL ,
    signal_loc varchar(500)    NOT NULL ,
    signal_json varchar(20000)    NOT NULL ,
    is_general int    NOT NULL ,
    is_baseline int    NOT NULL ,
    signal_signal_code int    NOT NULL,
    order_to_show int NOT Null
);

-- Table: `signal`
CREATE TABLE signals (
      signal_code int    NOT NULL  AUTO_INCREMENT,
      signal_desc varchar(1000)    ,
      signal_extension varchar(100)    NOT NULL ,
      data_type int    NOT NULL ,
      ytitle varchar(1000)    NOT NULL ,
      frame_rate int   ,
      first_row int ,
      first_col int ,
      min_yvalue DECIMAL(12,6)  ,
      max_yvalue DECIMAL(12,6) ,
      isLogarithmic int DEFAULT 0,
      owner varchar(100)  ,
      CONSTRAINT signal_pk PRIMARY KEY (signal_code)
  );


CREATE TABLE psychometric (
    p_code int   NOT NULL  AUTO_INCREMENT,
    p_name varchar(1000)    ,
    min_value int    NOT NULL ,
    max_value int    NOT NULL ,
    owner varchar(100)  ,
    CONSTRAINT psychometric_pk PRIMARY KEY (p_name,owner )
);



-- Table: study
CREATE TABLE study (
    study_id int   NOT NULL ,
    study_name varchar(100)    NOT NULL ,
    study_creation_date date    NOT NULL ,
    study_type int    NOT NULL ,
    study_owner varchar(100)    NOT NULL ,
    portrait_string varchar(100000) NULL,
    top_summary varchar(100000) NULL,
    radar varchar(100000) NULL,
    study_descriptor_url varchar(500) NULL,
    CONSTRAINT study_pk PRIMARY KEY (study_id)
);

-- Table: subject
CREATE TABLE subject (
    subject_seq int    NOT NULL AUTO_INCREMENT,
    subject_id varchar(100)    NOT NULL ,
    study_id int    NOT NULL ,
    radar_value varchar(10000)  NULL,
    f_name varchar(20)    NULL ,
    l_name varchar(20)    NULL ,
    DOB date    NULL ,
    hide int    NULL ,
    PAS int    NULL ,
    NAS int    NULL ,
    bio_code int    NULL ,
    psycho int    NULL,
    physio int     NULL,
    CONSTRAINT subject_pk PRIMARY KEY (subject_seq)
);

-- Table: user
CREATE TABLE user (
    fullname varchar(100)    NOT NULL ,
    username varchar(100)    NOT NULL ,
    password varchar(20)    NOT NULL ,
    email varchar(100)    NOT NULL ,
    CONSTRAINT user_pk PRIMARY KEY (username)
);

-- Table: credential
CREATE TABLE credential (
    userId varchar(100)    NOT NULL ,
    access_token varchar(200)    NULL ,
    refresh_token varchar(200)    NULL ,
    CONSTRAINT credential_pk PRIMARY KEY (userId)
);



CREATE TABLE lookup (
    code int    NOT NULL ,
    description varchar(1000)    NULL ,
    CONSTRAINT lookup_pk PRIMARY KEY (code)
);

-- foreign keys
-- Reference:  privilege_Users (table: privilege)


ALTER TABLE privilege ADD CONSTRAINT privilege_credential FOREIGN KEY (username)
    REFERENCES credential (userId);
-- Reference:  privilege_permissions (table: privilege)

-- Reference:  privilege_permissions (table: privilege)


ALTER TABLE privilege ADD CONSTRAINT privilege_permissions FOREIGN KEY (permission_type)
    REFERENCES permission (permission_type);
-- Reference:  privilege_study (table: privilege)


ALTER TABLE privilege ADD CONSTRAINT privilege_study FOREIGN KEY (study_id)
    REFERENCES study (study_id);
-- Reference:  session_signal (table: session)


ALTER TABLE session ADD CONSTRAINT session_signal FOREIGN KEY session_signal (signal_signal_code)
    REFERENCES signals (signal_code);


ALTER TABLE session ADD CONSTRAINT session_subject FOREIGN KEY (subject_seq)
    REFERENCES subject (subject_seq);
-- Reference:  study_Users (table: study)


ALTER TABLE study ADD CONSTRAINT study_credential FOREIGN KEY (study_owner)
    REFERENCES credential (userId);

ALTER TABLE psychometric ADD CONSTRAINT psychometric_credential FOREIGN KEY (owner)
    REFERENCES credential (userId);


ALTER TABLE session ADD CONSTRAINT session_lookup FOREIGN KEY (data_type)
    REFERENCES lookup (code);


ALTER TABLE subject ADD CONSTRAINT subject_lookup FOREIGN KEY (hide)
    REFERENCES lookup (code);
-- Reference:  subject_study (table: subject)



ALTER TABLE subject ADD CONSTRAINT subject_study FOREIGN KEY (study_id)
    REFERENCES study (study_id);
# --- !Downs
