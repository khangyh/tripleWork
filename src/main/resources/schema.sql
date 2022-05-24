DROP TABLE IF EXISTS USER_REVIEW;
DROP TABLE IF EXISTS USER_REVIEW_POINT;

#BackEnd_HomeWork_2 회원 리뷰 점수 관리
CREATE TABLE IF NOT EXISTS `USER_REVIEW` (
                         `SEQ` integer(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
                         `USER_ID` varchar(36) NOT NULL ,
                         `PLACE_ID` varchar(36) NOT NULL ,
                         `REVIEW_ID` varchar(36) NOT NULL,
                         `CONTENT` varchar(250) NULL ,
                         `ATTACHED_PHOTO_ID` varchar(1000) NULL ,
                         `REGISTER_DATE` datetime DEFAULT current_timestamp(),
                         `UPDATE_DATE` datetime DEFAULT current_timestamp()
#                          PRIMARY KEY (USER_ID, PLACE_ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `USER_REVIEW_POINT` (
                                     `SEQ` integer(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
                                     `USER_ID` varchar(36) NOT NULL ,
                                     `PLACE_ID` varchar(36) NOT NULL ,
                                     `POINT` TINYINT,
                                     `PHOTO_POINT` TINYINT,
                                     `CONTENT_POINT` TINYINT,
                                     `PLACE_POINT` TINYINT,
                                     `REGISTER_DATE` datetime DEFAULT current_timestamp(),
                                     `UPDATE_DATE` datetime DEFAULT current_timestamp()
#                                      PRIMARY KEY (USER_ID, PLACE_ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE INDEX IDX_USER_REVIEW ON USER_REVIEW (USER_ID, PLACE_ID);
CREATE INDEX IDX_USER_REVIEW_POINT ON USER_REVIEW_POINT (USER_ID, PLACE_ID);
CREATE INDEX IDX_USER_REVIEW_POINT_PLACE ON USER_REVIEW_POINT (PLACE_ID);


