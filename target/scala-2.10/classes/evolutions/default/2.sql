# Users schema

# --- !Ups


INSERT INTO lookup VALUES (1, 'signal data');
INSERT INTO lookup VALUES (2, 'videos');
INSERT INTO lookup VALUES (3, 'Information data');
INSERT INTO lookup VALUES (4, 'tabular data');
INSERT INTO lookup VALUES (5, 'Psychometric data');
INSERT INTO lookup VALUES (6, 'activity data');
INSERT INTO lookup VALUES (7, 'Performance');
INSERT INTO lookup VALUES (8, 'yes');
INSERT INTO lookup VALUES (9, 'no');


INSERT INTO signals VALUES(1, 'perspiration', 'perspiration', 1, 'Perinasal EDA', 8,9, 2, null,null,0, 'cplsubjectbook@gmail.com');
INSERT INTO signals VALUES(2, 'Simulation', 'sim', 1, '', 8,9, 2, null,null,0,'cplsubjectbook@gmail.com');
INSERT INTO signals VALUES(3, 'Q_Sensor', 'q_eda', 1, 'Palm EDA', 8,9, 2, null,null,0,'cplsubjectbook@gmail.com');
INSERT INTO signals VALUES(4, 'hrv', 'hrv', 1, '', 8,9, 2, null,null, 0,'cplsubjectbook@gmail.com');
INSERT INTO signals VALUES(5, 'expression', 'expression', 1, '', 8,9, 2, null,null,0, 'cplsubjectbook@gmail.com');
INSERT INTO signals VALUES(6, 'Motion', 'q_motion', 1, 'Energy', 8,9, 2, null,null,0, 'cplsubjectbook@gmail.com');
INSERT INTO signals VALUES(7, 'breathing', 'breathing', 1, '', 8,9, 2,null,null,0, 'cplsubjectbook@gmail.com');
INSERT INTO signals VALUES(8, 'Heart Rate', 'Z_ECG', 1, '', 8,9, 2, null,null,0,'cplsubjectbook@gmail.com');
INSERT INTO signals VALUES(9, 'Belt breathing', 'z_breathing', 1, '', 8, 9, 2, null,null,0,'cplsubjectbook@gmail.com');
INSERT INTO signals VALUES(10, 'Temperature', 'q_temperature', 1, '', 8,9, 2, null,null, 0, 'cplsubjectbook@gmail.com');
INSERT INTO signals VALUES(11, 'Nasal Persperiation', 'nperspiration', 1, '', 8,9, 2,null,null,0, 'cplsubjectbook@gmail.com');
INSERT INTO signals VALUES(12, 'bar', 'bar', 4, '', 8,9, 2, null,null,0,'cplsubjectbook@gmail.com');
INSERT INTO signals VALUES(13, 'eye', 'eye', 1, '', 8,9, 2, null,null,0,'cplsubjectbook@gmail.com');
INSERT INTO signals VALUES(100, 'Video', 'avi', 2, '', 8,9, 2, null,null,0, 'cplsubjectbook@gmail.com');
INSERT INTO signals VALUES(101, 'Info', 'info', 3, '', 8,9, 2, null,null,0,'cplsubjectbook@gmail.com');
INSERT INTO signals VALUES(102, 'Activity', 'activity', 6, '', 8,9, 2, null,null,0,'cplsubjectbook@gmail.com');
INSERT INTO signals VALUES(103, 'Physchometric', 'pm', 5, '', 8,9, 2, null,null,0,'cplsubjectbook@gmail.com');
INSERT INTO signals VALUES(104, 'Performance', 'prf', 7, '', 8,9, 2, null,null,0,'cplsubjectbook@gmail.com');
INSERT INTO signals VALUES(105, 'Other', 'xlsx', 1, '', 8,9, 2, null,null,0,'cplsubjectbook@gmail.com');
INSERT INTO signals VALUES(106, 'sim2', 'sim2', 1, '', 8,9, 2, null,null,0,'cplsubjectbook@gmail.com');
INSERT INTO signals VALUES(107, 'res', 'res', 1, '', 8,9, 2, null,null,0,'cplsubjectbook@gmail.com');
INSERT INTO signals VALUES(108, 'res2', 'res2', 1, '', 8,9, 2, null,null,0,'cplsubjectbook@gmail.com');
INSERT INTO signals VALUES(109, 'Simulate', 'stm', 6, '', 8,9, 2, null,null,0,'cplsubjectbook@gmail.com');
INSERT INTO signals VALUES(110, 'Simulate2', 'stimuli', 6, '', 8,9, 2, null,null,0,'cplsubjectbook@gmail.com');


INSERT INTO permission VALUES(1, 'Full_Access');

INSERT INTO psychometric(p_name, min_value, max_value,owner) VALUES( 'TYPE AB', 35, 380, 'cplsubjectbook@gmail.com');
INSERT INTO psychometric(p_name, min_value, max_value,owner) VALUES( 'PA', 20, 80, 'cplsubjectbook@gmail.com');
INSERT INTO psychometric(p_name, min_value, max_value,owner) VALUES( 'SAI', 20, 80, 'cplsubjectbook@gmail.com');
INSERT INTO psychometric(p_name, min_value, max_value,owner) VALUES( 'TAI', 20, 80, 'cplsubjectbook@gmail.com');






# --- !Downs
