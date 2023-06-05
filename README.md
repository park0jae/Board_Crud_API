# Board

**Environments**
<br>
<img src="https://img.shields.io/badge/intellij IDEA-000000?style=for-the-badge&logo=intellijidea&logoColor=white">
<img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white">
<img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">

**Developments**
<br>
<img src="https://img.shields.io/badge/spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white">
<img src="https://img.shields.io/badge/thymeleaf-005F0F?style=for-the-badge&logo=thymeleaf&logoColor=white">

<br>

# Project 🔍
- 프로젝트명 : 스프링 JPA를 이용한 게시판 CRUD API
- 개발 언어 : Java
- 개발 프레임워크 : Srping Framework
- 데이터베이스 : H2 Database
- 요구사항
  - 게시판 : CRUD API , 페이징 처리 , 필드 예외 검증 , 전역 예외 처리 , 검색 기능

<br>

# RESTful API

<br>

## BoardController
: 게시판의 CRUD 기능으로 뷰 템플릿을 사용하여 웹 페이지 상에서 데이터를 등록, 수정, 삭제, 조회하는 컨트롤러
|Description|Http Method|URL|
|:---:|:---:|:---:|
|게시물 등록|POST|/board|
|게시물 조회(단건)|GET|/board/{boardId}|
|게시물 조회(전체)|GET|/board|
|게시물 수정|PATCH|/board/{boardId}|
|게시물 삭제|DELETE|/board/{boardId}|

<br>

## BoardApiController
: 게시판의 CRUD 기능으로 JSON 통신을 이용해 데이터를 등록, 수정, 삭제, 조회하는 컨트롤러
|Description|Http Method|URL|
|:---:|:---:|:---:|
|게시물 등록|POST|/api/boards|
|게시물 조회(단건)|GET|/api/boards/{boardId}|
|게시물 조회(전체)|GET|/api/boards|
|게시물 수정|PATCH|/api/boards/{boardId}|
|게시물 삭제|DELETE|/api/boards/{boardId}|


<br>

# Settings
**- application.properties**
```
#h2 db ??
spring.datasource.url=jdbc:h2:tcp://localhost/~/board
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect

#hibernate ??
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.show_sql=true
spring.jpa.hibernate.ddl-auto=create

spring.mvc.throw-exception-if-no-handler-found=true
spring.web.resources.add-mappings=false 

# hidden method
spring.mvc.hiddenmethod.filter.enable=true

# database test data setting
spring.jpa.defer-datasource-initialization = true
spring.sql.init.mode = always
```

**- data.sql**
: 애플리케이션 실행 시 임의의 테스트 데이터를 데이터베이스에 삽입하기 위해 세팅해놓은 데이터 파일


# 페이지 구성
- 글 목록
![image](https://github.com/park0jae/Board/assets/84398970/412014cb-996e-40aa-b082-60d9c98a7091)

- 글 상세페이지
![image](https://github.com/park0jae/Board/assets/84398970/ece8c5b1-ee7a-4d8c-8006-b286ea8696e6)

- 글 수정페이지
![image](https://github.com/park0jae/Board/assets/84398970/404e220f-2d42-4b1c-875c-373f39d9cce0)

- 글 검색 
![image](https://github.com/park0jae/Board/assets/84398970/8c638ffc-9af7-4888-9d84-200cde59e042)


