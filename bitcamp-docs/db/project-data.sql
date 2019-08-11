-- 수업 예제 데이터 
insert into lms_lesson(lesson_id, titl, conts, sdt, edt, tot_hr, day_hr)
  values(101, 'java programming', 'java programming learn', '2019-1-1', '2019-2-28', 200, 6);
insert into lms_lesson(lesson_id, titl, conts, sdt, edt, tot_hr, day_hr)
  values(102, 'C/C++ programming', 'programming learn', '2019-2-1', '2019-3-28', 200, 6);
insert into lms_lesson(lesson_id, titl, conts, sdt, edt, tot_hr, day_hr)
  values(103, 'python programming', 'programming learn', '2019-3-1', '2019-4-28', 300, 7);
insert into lms_lesson(lesson_id, titl, conts, sdt, edt, tot_hr, day_hr)
  values(104, 'web  programming', 'programming learn', '2019-1-4', '2019-5-28', 300, 7);
insert into lms_lesson(lesson_id, titl, conts, sdt, edt, tot_hr, day_hr)
  values(105, 'IoT programming', 'programming learn', '2019-5-1', '2019-6-28', 400, 8);

-- 회원 예제 데이터
insert into lms_member(member_id, name, email, pwd) 
  values(11, 'user1', 'user1@test.com', password('1111'));
insert into lms_member(member_id, name, email, pwd) 
  values(12, 'user2', 'user2@test.com', password('1111'));
insert into lms_member(member_id, name, email, pwd) 
  values(13, 'user3', 'user3@test.com', password('1111'));

-- 게시물 예제 데이터
insert into lms_board(board_id, conts) values(1, 'hello1');
insert into lms_board(board_id, conts) values(2, 'hello2');
insert into lms_board(board_id, conts) values(3, 'hello3');
insert into lms_board(board_id, conts) values(4, 'hello4');
insert into lms_board(board_id, conts) values(5, 'hello5');

-- 수업 사진 게시물 예제 데이터
insert into lms_photo(photo_id, lesson_id, titl) 
  values(1, 101, 'java hello5');
insert into lms_photo(photo_id, lesson_id, titl) 
  values(2, 101, 'java hello4');
insert into lms_photo(photo_id, lesson_id, titl) 
  values(3, 101, 'java hello3');
insert into lms_photo(photo_id, lesson_id, titl) 
  values(4, 104, 'HTML/CSS/JavaScript');
insert into lms_photo(photo_id, lesson_id, titl) 
  values(5, 104, 'java hello2');

-- 수업 사진 게시물 첨부 파일 예제 데이터
insert into lms_photo_file(photo_file_id, photo_id, file_path)
  values(1, 1, 'a1.gif');
insert into lms_photo_file(photo_file_id, photo_id, file_path)
  values(2, 1, 'a2.gif');
insert into lms_photo_file(photo_file_id, photo_id, file_path)
  values(3, 1, 'a3.gif');
insert into lms_photo_file(photo_file_id, photo_id, file_path)
  values(4, 2, 'b1.gif');
insert into lms_photo_file(photo_file_id, photo_id, file_path)
  values(5, 3, 'c1.gif');
insert into lms_photo_file(photo_file_id, photo_id, file_path)
  values(6, 3, 'c2.gif');
insert into lms_photo_file(photo_file_id, photo_id, file_path)
  values(7, 4, 'd1.gif');
insert into lms_photo_file(photo_file_id, photo_id, file_path)
  values(8, 5, 'e1.gif');
insert into lms_photo_file(photo_file_id, photo_id, file_path)
  values(9, 5, 'e2.gif');
insert into lms_photo_file(photo_file_id, photo_id, file_path)
  values(10, 5, 'e3.gif');
insert into lms_photo_file(photo_file_id, photo_id, file_path)
  values(11, 5, 'e4.gif');

