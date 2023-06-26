# petpat-co-back
Spring Boot로 만들어 보는 반려동물 커뮤니티 🐾

# 🐾 Project

## 프로젝트 소개

## 🐣 petpat 백엔드 멤버

<table>
  <tr>
    <td align="center"><a href="https://github.com/kim-ju-ho"><img src="https://avatars.githubusercontent.com/u/45279435?v=4" width="100px" /></a></td>
    <td align="center"><a href="https://github.com/ohyeryung"><img src="https://avatars.githubusercontent.com/u/100130070?v=4" width="100px" /></a></td>
    <td align="center"><a href="https://github.com/ohyeryung"><img src="https://avatars.githubusercontent.com/u/73023890?v=4" width="100px" /></a></td>
    <td align="center"><a href="https://github.com/Littlecold4"><img src="https://avatars.githubusercontent.com/u/72268423?v=4" width="100px" /></a></td>
  </tr>
  <tr>
    <td align="center"><b>김주호</b></td>
    <td align="center"><b>오예령</b></td>
    <td align="center"><b>황다빈</b></td>
    <td align="center"><b>장민우</b></td>
  </tr>
  <tr>
    <td align="center"><b>Backend </b></td>
    <td align="center"><b>Backend </b></td>
    <td align="center"><b>Backend </b></td>
    <td align="center"><b>Backend </b></td>
  </tr>
</table>
<hr>

### 프로젝트 설명

<pre> 펫팻은 반려동물들을 위한 커뮤니티입니다.
 분양과 거래 게시판을 통해 소통할 수 있습니다.
 1차 개발 이후에도 기능 및 내용을 추가하거나 수정하며 발전시킬 프로젝트입니다. </pre>

### 페이지별 기능

- **`회원가입 및 로그인`**
  - 회원과 비회원 모두 이용 가능, 비회원은 조회만 가능
  - OAuth 2.0을 활용한 카카오, 구글, 깃헙의 소셜 로그인 
- **`분양`**
  - 게시물 등록, 조회, 수정, 삭제 기능
  - 원하는 게시물에 대한 좋아요, 북마크 가능
- **`거래`**
  - 게시물 등록, 조회, 수정, 삭제 기능
  - 원하는 게시물에 대한 좋아요, 북마크 기능

### 프로젝트 환경

- Java 11
- Gradle 7.5
- SpringBoot 2.7.4

### 기술 스택
<p>
<img src= "https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white" >
<img src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=Spring&logoColor=white"> 
<img src="https://img.shields.io/badge/Springboot-6DB33F?style=for-the-badge&logo=Springboot&logoColor=white">
<img src="https://img.shields.io/badge/spring security-6DB33F?style=for-the-badge&logo=spring security&logoColor=white">
  <img src="https://img.shields.io/badge/Springjpa-4FC08D?style=for-the-badge&logo=jpa&logoColor=white"> 
<img  src="https://img.shields.io/badge/QueryDSL-4695EB?style=for-the-badge&logo=&logoColor=white">
<img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=MySQL&logoColor=white">
<br>
<img src="https://img.shields.io/badge/AWS Ec2-232F3E?style=for-the-badge&logo=amazonaws&logoColor=white"> 
<img  src="https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=Docker&logoColor=white">
<img src="https://img.shields.io/badge/Linux-FCC624?style=for-the-badge&logo=linux&logoColor=black">
<img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white">
<img src="https://img.shields.io/badge/Amazon S3-569A31?style=for-the-badge&logo=Amazon S3&logoColor=white">
<img  src="https://img.shields.io/badge/Amazon RDS-527FFF?style=for-the-badge&logo=Amazon RDS&logoColor=white">
<br>
</p>

### Tool
<img src= "https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white"/>
<img src="https://img.shields.io/badge/Slack-4A154B?style=for-the-badge&logo=Slack&logoColor=white"/> 
<img src="https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=Git&logoColor=white"/>
<img src="https://img.shields.io/badge/Github-181717?style=for-the-badge&logo=github&logoColor=white">


## ERD
<img alt="petpat_erd" src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fnz6jX%2FbtsgKoYdS7Y%2FXeDhpHIKsABClUYMJR91r0%2Fimg.png" width="650" height="400">


## API 
- [API 명세서](https://docs.google.com/spreadsheets/d/1GQ8mQJfZzy7_0CFoN61FxM25Lsl5OUadt8TldtZNUP4/edit#gid=193839811)


## Git Convention

> 수정한 종류에 따라 커밋 메시지를 선택
> | 메시지명 | 설명 |
> | -------- | -------------------------------------------------------- |
> | feat | 새로운 기능 추가 관련 |
> | fix | 버그 수정 |
> | test | 테스트 코드, 리팩토링 테스트 코드 추가 |
> | refactor | 코드 리팩토링(기능향상) |
> | chore | 빌드 업무 수정, 패키지 매니저 수정 |
> | docs | 문서 수정(md, git관련 파일, 이미지파일 수정) |
> | style | 코드 formatting, 세미콜론(;) 누락, 코드 변경이 없는 경우 |
