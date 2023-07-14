# 뉴스피드 프로젝트
## 팀프로젝트명: 맛의 협주곡(Flavorful Melody)
## 팀명: 칠전팔기(NeverQuit)

# 필수 구현 기능
- [ ]  **사용자 인증 기능**
    - 회원가입 기능
        - 새로운 사용자가 ID와 비밀번호의 형태로 서비스에 가입할 수 있어야 합니다.
            - 이 때, 비밀번호는 안전하게 암호화되어 저장되어야 합니다!
    - 로그인 및 로그아웃 기능
        - 사용자는 자신의 계정으로 서비스에 로그인하고 로그아웃할 수 있어야 합니다.
- [ ]  **프로필 관리**
    - 프로필 수정 기능
        - 이름, 한 줄 소개와 같은 기본적인 정보를 볼 수 있어야 하며 수정할 수 있어야 합니다.
        - 비밀번호 수정 시에는 비밀번호를 한 번 더 입력받는 과정이 필요합니다.
        
- [ ]  **게시물 CRUD 기능**
    - 게시물 작성, 조회, 수정, 삭제 기능
        - 게시물 조회를 제외한 나머지 기능들은 전부 인가(Authorization) 개념이 적용되어야 하며 이는 JWT와 같은 토큰으로 검증이 되어야 할 것입니다.
        - 예컨대, 내가 작성한 글을 남이 삭제할 수는 없어야 하고 오로지 본인만 삭제할 수 있어야겠죠?
    - 게시물 작성, 수정, 삭제 시 새로고침 기능
        - 프론트엔드에서 게시물 작성, 수정 및 삭제를 할 때마다 조회 API를 다시 호출하여 자연스럽게 최신의 게시물 내용을 화면에 보여줄 수 있도록 해야 합니다!
- [ ]  **뉴스 피드 기능**
    - 뉴스 피드 페이지
        - 사용자가 다른 사용자의 게시물을 한 눈에 볼 수 있는 뉴스 피드 페이지가 있어야 합니다.
        
# 추가 구현 기능
- [ ]  **댓글 CRUD 기능**
    - 댓글 작성, 조회, 수정, 삭제 기능
        - 사용자는 게시물에 댓글을 작성할 수 있고 본인의 댓글은 수정 및 삭제를 할 수 있어야 합니다.
        - 또한, 게시물과 마찬가지로 댓글 조회를 제외한 나머지 기능들은 인가(Authorization)개념이 적용되어야 합니다.
    - 댓글 작성, 수정, 삭제 시 새로고침 기능
        - 프론트엔드에서 댓글 작성, 수정 및 삭제를 할 때마다 조회 API를 다시 호출하여 자연스럽게 최신의 댓글 목록을 화면에 보여줄 수 있도록 해야 합니다!
    
- [ ]  **좋아요/싫어요 기능**
    - 게시물 및 댓글 좋아요,싫어요/좋아요,싫어요 취소 기능
        - 사용자가 게시물이나 댓글에 좋아요/싫어요를 남기거나 취소할 수 있어야 합니다.
        - 이 때, 본인이 작성한 게시물과 댓글에 좋아요/싫어요는 남길 수 없도록 해봅니다!

- [ ]  **소셜 로그인 기능 구현**
    - [네이버 로그인 개발가이드](https://developers.naver.com/docs/login/devguide/devguide.md)를 참고하여 네이버 로그인을 구현해보세요.
	- [    카카오 로그인 개발가이드](https://developers.kakao.com/docs/latest/ko/kakaologin/common)를 참고하여 카카오 로그인을 구현해보세요.
- [ ]  **프로필에 사진 업로드 기능 구현**
    - 프로필 사진을 저장할 때는 반드시 AWS S3를 이용해주세요!
- [ ]  **HTTP를 HTTPS로 업그레이드 하기**
    - HTTPS를 적용하여 보안이 강화된 웹 페이지를 제공해보도록 합니다!
    
# API 명세서
![](https://velog.velcdn.com/images/uiseongsang/post/c7f13055-a399-4cd6-8343-46cb7a9bc295/image.png)
> 링크 들어가셔서 자세하게 하나하나씩 클릭해서 보세요!

링크: https://thomass-organization-12.gitbook.io/flavorful-melody/
# ERD (7/15 수정완료)
<img width="870" alt="image" src="https://github.com/nerverQuit/flavorful-melody/assets/40707686/a6fe0605-f1d5-4f70-b2b4-ae3103046650">
링크: https://lucid.app/documents/view/831800f5-7a8b-4a98-9842-3b44083193c6

# 와이어프레임
![](https://velog.velcdn.com/images/uiseongsang/post/939925be-ef29-4ff9-af58-17cd0e6c8b8a/image.png)


## 로그인 / 회원가입
![](https://velog.velcdn.com/images/uiseongsang/post/3a373f88-3f63-4af7-9c46-0c9325a8d65a/image.png)

## 로그인 전 / 로그인 후
![](https://velog.velcdn.com/images/uiseongsang/post/42ccbaaf-842e-44b0-8468-62bd9731dd11/image.png)


## 디테일페이지
![](https://velog.velcdn.com/images/uiseongsang/post/8a424c59-0894-4b21-a037-7f12da03c7e9/image.png)

## 마이페이지
![](https://velog.velcdn.com/images/uiseongsang/post/338bf96c-b2fe-4bdc-919a-20b5f933a994/image.png)

## 피드 남기기
![](https://velog.velcdn.com/images/uiseongsang/post/661d52a2-4ac5-4aee-8f12-436ae6c433b9/image.png)

## 미식의 전당 / 괴식의 전당
![](https://velog.velcdn.com/images/uiseongsang/post/ec7b642b-4462-46d7-b556-582b26c8fc3d/image.png)

# 역할분담

게시물 CRUD + 전체 모아보기 (혜연)

로그인/회원가입, *프로필 관리 (병민)

댓글 CURD (의성)

좋아요/싫어요, 프론트엔드 (명지)

# 프론트엔드
## 메인페이지 (index.html)
![image](https://github.com/nerverQuit/flavorful-melody/assets/40707686/f12100dc-739b-47c2-aa66-726eb5a43a4b)

## 로그인 / 회원가입 (login / signup.html)
![](https://velog.velcdn.com/images/uiseongsang/post/ca63af91-84d8-494b-a39a-97fe6c038137/image.png)

## 상세페이지 (detail.html)
![image](https://github.com/nerverQuit/flavorful-melody/assets/40707686/4e7ca54a-ebbd-4ad9-927a-3df8d53e41ff)

## 미식 / 괴식의 전당 (delight / terror.html)
![](https://velog.velcdn.com/images/uiseongsang/post/5888d53e-be9c-4656-bc91-4dac8a5a4efa/image.png)


## 마이페이지 (mypage.html)
![](https://velog.velcdn.com/images/uiseongsang/post/1b901487-495d-4e58-afd8-c4deb44bdf7c/image.png)


# 컨벤션
## 깃 컨벤션  
main, dev, feature, hotfix
![](https://velog.velcdn.com/images/uiseongsang/post/1d4db6d0-5b8e-4309-a46a-598d9252a0c2/image.png)


## 코드 컨벤션
NAVER : [캠퍼스 핵데이 Java 코딩 컨벤션](https://naver.github.io/hackday-conventions-java/)

# 노션 페이지
![](https://velog.velcdn.com/images/uiseongsang/post/02591b44-5339-4938-8f2b-7af92914b4ae/image.png)

https://www.notion.so/Chili-Dog-c8f294abbbed42f5bfc041089d6f2c82?pvs=4
# 테스트
![](https://velog.velcdn.com/images/uiseongsang/post/627ab68b-a250-40bc-ae59-e4b0fa058c57/image.png)


https://docs.google.com/spreadsheets/d/16lcQrTc2CIJ4U_Sn4_HiYmsf5lZ6Rxdg2tQ1ESMYxbk/edit?usp=sharing

<br>

## 👨‍👩‍👧‍👦 7전8기 팀

| [🚩상의성](https://github.com/uiseongsang) | [손명지](https://github.com/SonMyungJi) | [송병민](https://github.com/mini9707) | [조혜연](https://github.com/hjern) |
|:-------------------------------------:|:------------------------------------:|:-------------------------------------:|:----------------------------------------:|
| ![image](https://github.com/nerverQuit/flavorful-melody/assets/40707686/22c0a6d5-55e4-41d3-8a65-982bfa03bea3) | ![image](https://github.com/nerverQuit/flavorful-melody/assets/40707686/ff540832-6b1b-4694-9c72-52f84c3e95df) | ![image](https://github.com/nerverQuit/flavorful-melody/assets/40707686/db3f1800-b0d8-40ae-b187-171c39efe352) | ![image](https://github.com/nerverQuit/flavorful-melody/assets/40707686/1bf79669-8c42-462a-8027-96e82a52e08d) |
|              BACK-END               |              BACK-END               |              BACK-END               |                BACK-END                 |

<br>
