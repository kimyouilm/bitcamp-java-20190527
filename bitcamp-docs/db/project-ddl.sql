-- 수업 테이블 삭제 
drop table if exists lms_lesson;

-- 회원 테이블 삭제
drop table if exists lms_member;

-- 게시판 테이블 삭제 
drop table if exists lms_board;

-- 사진 게시판 테이블 삭제
drop table if exists lms_photo;

-- 사진 게시물 첨부 파일 테이블 삭제
drop table if exists lms_photo_file;

-- 수업 테이블 생성
create table lms_lesson (
  lesson_id int not null auto_increment primary key comment 'class data number', 
  sdt datetime not null comment 'class start',
  edt datetime not null comment 'class end',
  tot_hr int not null comment 'total class hours',
  day_hr int not null comment 'one day class hours',
  titl varchar(255) not null comment 'class title',
  conts text not null comment 'class contents'
) comment 'class'; 

-- 회원 테이블 생성
create table lms_member (
  member_id int not null auto_increment primary key comment 'member date number',
  name varchar(30) not null comment 'name',
  email varchar(50) not null comment 'email',
  pwd varchar(255) not null comment 'password',
  cdt datetime default now() comment 'signupdate', 
  tel varchar(20) comment 'phone',
  photo varchar(255) comment 'pic'
) comment 'member';

create unique index UIX_lms_member_email
  on lms_member ( -- member
    email asc    -- email
  );
  
-- 게시물 테이블 생성
create table lms_board (
  board_id int not null auto_increment primary key comment 'pic number',
  conts text not null comment 'contents',
  cdt datetime default now() comment 'signupdate',
  vw_cnt int default 0 comment 'viewcounts' 
) comment 'board';

-- 사진 게시물 테이블 생성
create table lms_photo (
  photo_id int not null auto_increment primary key comment 'pic number',
  lesson_id int not null comment 'class number',
  titl varchar(255) not null comment 'title',
  cdt datetime default now() comment 'uploaddate',
  vw_cnt int default 0 comment 'viewcount',
  -- lesson_id에 저장되는 값은 lms_lesson 테이블의 lesson_id 값으로 제한하는 조건을 추가한다.
  constraint fk_photo_to_lesson foreign key (lesson_id)
    references lms_lesson (lesson_id)
) comment 'pic board';

-- 사진 게시물에 첨부하는 사진 파일 테이블 생성
create table lms_photo_file (
  photo_file_id int not null auto_increment primary key comment 'pic number',
  photo_id int not null,
  file_path varchar(255) not null,
  constraint fk_photo_file_to_photo foreign key (photo_id)
    references lms_photo (photo_id)
) comment 'attach table'; 






