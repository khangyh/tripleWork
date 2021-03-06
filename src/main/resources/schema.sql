#BackEnd_HomeWork_2 회원 리뷰 점수 관리
DROP TABLE IF EXISTS USER_REVIEW;
DROP TABLE IF EXISTS USER_REVIEW_POINT;

#사용자 리뷰 Table
CREATE TABLE IF NOT EXISTS `USER_REVIEW` (
     `SEQ` integer(11) NOT NULL PRIMARY KEY AUTO_INCREMENT, #시퀀스
     `USER_ID` varchar(36) NOT NULL ,                       #사용자 아이디
     `PLACE_ID` varchar(36) NOT NULL ,                      #장소 아이디
     `REVIEW_ID` varchar(36) NOT NULL,                      #리뷰 아이디
     `CONTENT` varchar(250) NULL ,                          #리뷰 정보
     `ATTACHED_PHOTO_ID` varchar(1000) NULL ,               #첨부 사진 정보
     `REGISTER_DATE` datetime DEFAULT current_timestamp(),  #등록일
     `UPDATE_DATE` datetime DEFAULT current_timestamp()     #수정일
#     PRIMARY KEY (USER_ID, PLACE_ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#사용자 리뷰 포인트 Table
CREATE TABLE IF NOT EXISTS `USER_REVIEW_POINT` (
    `SEQ` integer(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,  #시퀀스
    `USER_ID` varchar(36) NOT NULL ,                        #사용자 아이디
    `PLACE_ID` varchar(36) NOT NULL ,                       #장소 아이디
    `POINT` TINYINT,                                        #사용자 포인트 총합
    `PHOTO_POINT` TINYINT,                                  #사진 추가 점수 이력
    `CONTENT_POINT` TINYINT,                                #리뷰 정보 추가 점수 이력
    `PLACE_POINT` TINYINT,                                  #첫 장소 리뷰 추가 점수 이력
    `REGISTER_DATE` datetime DEFAULT current_timestamp(),   #등록일
    `UPDATE_DATE` datetime DEFAULT current_timestamp()      #수정일
#     PRIMARY KEY (USER_ID, PLACE_ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#인덱스
CREATE INDEX IDX_USER_REVIEW ON USER_REVIEW (USER_ID, PLACE_ID);
CREATE INDEX IDX_USER_REVIEW_POINT ON USER_REVIEW_POINT (USER_ID, PLACE_ID);
CREATE INDEX IDX_USER_REVIEW_POINT_PLACE ON USER_REVIEW_POINT (PLACE_ID);


