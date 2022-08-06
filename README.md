# clian-board-app
해당 프로젝트는 클리앙 모두의 공원 게시판 목록을 안드로이드로 불러오는 프로젝트입니다.

프로젝트는 java로 구현하였으며, 클리앙 모두의 공원 게시판에서 받아올 데이터는 제목, 작성자, 작성시간, 엠블럼을 가져오는것을 구현 하였습니다.
  1. listView를 통해 layout을 작성
  2. listView 1개 view를 list_board xml로 작성하여 textView, imageView를 불러오게 작성
  3. jsoup을 사용하여 html passing을 진행
  4. asyncTask 를 통해 jsoup 데이터를 받아오게 작성
  5. ListBoardView를 통해 게시판 목록 1개의 내용이 보여질 요소 를작성
  6. ListBoardDTO에서 게시판 목록 1개의 내용의 TextView, ImageView의 getter와 setter를 정의
  7. ListViewBaseAdapter를 통해 view가 생성되고 해당 view에 내용을 업데이트 하는 adapter 작성
  8. scroll이 바닥에 다을경우 다음 페이지 로딩을 위해 MainActivity.class에 OnscrollListener를 implements 작성
  9. 로딩중을 확인하기 위해 progressBar를 1초간 작동하게 작성
  
