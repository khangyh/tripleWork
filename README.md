# 트리플 과제 HomeWork9 프로젝트

+ ###프로젝트 파일 설명
  + ModelMapperConfig.kt (JPA Entity <-> DTO Mapper Configration)
  + ResponseDTO.kt (요청 응답에 대한 Response 공통 결과 설정 DTO)
  + UserReviewController.kt (Controller)
  + UserReviewEntity.kt (리뷰 Entity)
  + UserReviewPointEntity.kt (포인트 Entity)
  + UserReviewDTO.kt  (사용자 리뷰 데이터 DTO)
  + UserReviewPointDTO.kt (리뷰 포인트 데이터 DTO)
  + UserReviewPointRepository.kt (포인터 JPA Repository)
  + UserReviewRepository.kt (리뷰 JPA Repository)
  + UserReviewService.kt (Business Logic Service)
  + application.yml (Spring Boot Setting Properties)
  + **schema.sql (DataBase Schema)**
  + **UserReviewTest.kt (TestMock 여기서 테스트 하시면 됩니다.)**

###Table Schema Info

````
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
````

>테스트 방법
> + UserReviewTest (Mock Test)
> + setUserReview (리뷰 등록, 수정, 삭제)
> + getUserReviewPoint (사용자 포인트 조회)
> + http://localhost:8080/review (API 결과 조회 페이지 Spring Boot 서버 실행 필요)

#테스트 기회를 주셔서 감사합니다.
+ 처음으로 사용해보는 Kotlin이라 문법이 부족한 부분이 있을 수 있습니다.
+ UserReviewTest 파일에 있는 setUserReview 함수로 파라메터를 변경하여 등록,
수정, 삭제 가능합니다. getUserReviewPoint 함수를 사용하여 개인 포인트 정보를 조회 가능합니다.
+ 데이터베이스 테이블 스키마 정보는 schema.sql 작성되어있으며 mysql을 사용하였습니다. 
Spring Boot 스키마 자동 생성은 비황성화 되어 있기 때분에 생성이 필요 하며 설정 되어 있는 개인 서버의
설정으로 사용 가능하기 때문에 수정 없이 Mock 테스트 가능 합니다.


