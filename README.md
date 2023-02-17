# Readme

# 📕STORYGE

## 프로젝트 소개

![Storyge.gif](Readme%20f0e0ecf6216545eba7577d3ed98aef28/Storyge.gif)

### “ 시시각각 변하는 나의 감정을 기록하고 공유하자 “

- 일기를 작성하고, 그 때 느낀 감정을 함께 기록하는 서비스 입니다
- SNS 기능으로 주변 사람들과 함께 나의 하루와 감정을 공유할 수 있습니다
- AI를 이용한 감정 분석이 들어가 나의 감정을 AI가 분석하고 이모티콘을 추천해 줍니다

## 프로젝트 기간

> 2023.01.09 - 2023.02.17 (6주)
> 

## 참여 인원 및 역할 분배

### Front-End

- **안태현** :
    
    FE 팀장 / 웹사이트 구조 구성 / 사용자 정보 페이지 / 사용자 검색 / 달력 커스텀 / 감정 통계 도넛 차트 커스텀 / 일기 댓글 / 실시간 알림
    
- **서희연** :
    
    발표자 / 노래 추천 분석 / 일기 감정 분석 / 일기 작성 / 일별 일기 목록 / 일기 상세 페이지 / GPT3 API 활용 / Youtube API 활용
    
- **송수현** :
    
    UI/UX 디자인 / 로그인 적용 / 팔로우/팔로잉 리스트 / 마이 페이지 / 알림 페이지 / 노래 추천 페이지 / 라우팅 처리
    

### Back-End

- **송승현** :
    
    BE 팀장 / 서버 배포 / Jira 관리 / DB설계 / 다이어리 / 캘린더 용 하루 감정 평균 / 년-월 별 감정 통계 / UCC 편집
    
- **두소원** :
    
    DB설계 / 팔로우 기능 / 팔로잉 유저의 최근 다이어리 조회 / 일기 댓글 / 글귀 / 알림센터(SSE를 통한 실시간 알림) / 발표 PPT 준비
    
- **박수민** :
    
    DB설계 / User 관리(OAuth, JWT, 프로필 사진 파일 업로드) / SpringSecurity / 코드 폴리싱 / Config 관리 / 버그 관리
    

## 개발 배경

- 하루 동안의 내 기분이 어땠는지, 더 나아가서 년/월 별로 내가 어떤 감정 상태로 살고 있는지 자세히 모르는 경우가 많다
- 일기 작성 시 하루 동안 있었던 일들을 한번에 정리하며 작성하기에 부담이 있다
- 나의 감정과 일상을 순간 순간마다 정리할 수 있도록 하자
- 본인의 감정을 대놓고 드러내기엔 부담스럽지만 알아줬으면 하는 마음이 있다.

## 개발 환경

### Front-End

- React 18.2.0
- Node.js LTS 18.13.0(includes npm 8.19.3)
- Visual Studio code : 1.74.3

### Back-End

- Intellij
    - Build 223.8214.52, built on December 20, 2022
- Java 11
    - zulu-11 java version “11.0.18”
- Springboot 2.7.6
- MySQL 8.0.31

### ETC

- Figma
- Nginx 1.18.0 (ubuntu)
- AWS EC2 Ubuntu 20.04 LTS
- AWS S3 2.2.6

### 협업 툴

- Jira
- Gitlab
- Notion

## 아키텍처

![Untitled](Readme%20f0e0ecf6216545eba7577d3ed98aef28/Untitled.png)

## ERD

![Storyge ERD.png](Readme%20f0e0ecf6216545eba7577d3ed98aef28/Storyge_ERD.png)

## 핵심 기술

- OAuth 2.0, JWT, SpringSecurity를 활용한 Authorization Code Grant Type의 구글, 네이버, 카카오 소셜 로그인 서비스
- OpenAI의 GPT3 API를 이용하여 일기 작성 내용에 따른 감정분석 제공과 분석 결과에 따른 감정 이모티콘 추천
- 사연을 입력하면 GPT3를 이용하여 사연을 분석한 후 그에 맞는 음악을 Google API를 이용하여 유튜브 영상으로 제공
- SSE(Server Sent Event)를 활용한 실시간 알림 서비스

- OpenAI 참고 글(삭제 예정)
    
    [Azure OpenAI 모델 - Azure OpenAI](https://learn.microsoft.com/ko-kr/azure/cognitive-services/openai/concepts/models)
    

## 기능 소개

### 소셜 로그인

![로그인.gif](Readme%20f0e0ecf6216545eba7577d3ed98aef28/%25EB%25A1%259C%25EA%25B7%25B8%25EC%259D%25B8.gif)

- 구글, 네이버, 카카오 로그인 지원
- OAuth 2.0의 Authorization grant 방식, JWT, Spring Security를 이용하여 보안성이 강화된 로그인 서비스를 제공

### 일기

**일기 작성 + 캘린더 보여주기**

![KakaoTalk_20230217_110701369_AdobeExpress (1).gif](Readme%20f0e0ecf6216545eba7577d3ed98aef28/KakaoTalk_20230217_110701369_AdobeExpress_(1).gif)

![통계그래프 보이기.gif](Readme%20f0e0ecf6216545eba7577d3ed98aef28/%25ED%2586%25B5%25EA%25B3%2584%25EA%25B7%25B8%25EB%259E%2598%25ED%2594%2584_%25EB%25B3%25B4%25EC%259D%25B4%25EA%25B8%25B0.gif)

- 일기를 작성한 뒤 감정 분석하기 버튼을 누르면 AI가 일기의 감정을 분석하여 감정 이모티콘을 추천
- 일기는 비공개로도 작성이 가능하며, 비공개로 작성시 다른 유저들은 읽을 수 없음
    - 감정 이모티콘은 기쁨, 놀람, 혐오, 분노, 공포, 슬픔, 중립의 7가지가 제공되며, 추천된 감정을 선택하지 않으면 직접 감정을 선택할 수 있다

**일기 상세**

![일기 상세 확인.gif](Readme%20f0e0ecf6216545eba7577d3ed98aef28/%25EC%259D%25BC%25EA%25B8%25B0_%25EC%2583%2581%25EC%2584%25B8_%25ED%2599%2595%25EC%259D%25B8.gif)

- 일기를 작성한 뒤 분석된 결과를 읽어볼 수 있다
- 팔로워들은 일기에 댓글을 작성할 수 있다

**달력**

- !일별 대표 감정은 해당 일자의 최다 선택 감정으로 표시된다
- !연별/월별 감정 개수의 통계 확인 가능

!**팔로잉하는 유저의 최근 다이어리**

- 내가 팔로우 하는 사람들의 작성한 지 24시간이 지나지 않은 일기 중 가장 최근 다이어리를 메인 페이지에서 바로 이동 가능

### 팔로우/팔로잉

![KakaoTalk_20230217_110704701_AdobeExpress (1).gif](Readme%20f0e0ecf6216545eba7577d3ed98aef28/KakaoTalk_20230217_110704701_AdobeExpress_(1).gif)

![KakaoTalk_20230217_110707300_AdobeExpress (1).gif](Readme%20f0e0ecf6216545eba7577d3ed98aef28/KakaoTalk_20230217_110707300_AdobeExpress_(1).gif)

- 상대방을 팔로우하기 전에는 상대방의 달력과 일기를 확인할 수 없다

### 알림

- 팔로우 신청이 들어오거나, 수락 됐거나, 내 다이어리에 댓글이 달렸다면 알림창에서 확인 후 해당 페이지로 바로 이동이 가능하다
- 알림이 들어오면 실시간으로 하단 알림 아이콘에 빨간색 점으로 표시가 되어 바로 알림이 온 것을 확인 가능하다

### !마이페이지

![프로필 변경.gif](Readme%20f0e0ecf6216545eba7577d3ed98aef28/%25ED%2594%2584%25EB%25A1%259C%25ED%2595%2584_%25EB%25B3%2580%25EA%25B2%25BD.gif)

- 프로필 사진과 닉네임 변경이 가능
- 나의 팔로잉, 팔로워들 목록을 확인 가능
- 챗봇을 통한 서비스 활용 가이드 제공

### 음악 추천

![KakaoTalk_20230217_110657462_AdobeExpress (1).gif](Readme%20f0e0ecf6216545eba7577d3ed98aef28/KakaoTalk_20230217_110657462_AdobeExpress_(1).gif)

- 글을 입력하면 그 글에 맞는 노래를 유튜브 영상으로 추천
- 글 분석과 추천은 openAI의 GPT3 API를 활용했으며 유튜브 영상은 Google API 이용

### 우울증 경고

- 대표 감정에서 슬픔이 14일 이상 지속된다면 우울증 경고 모달

## 기대 효과

- 언제 어디서든 간단하게 일기를 작성을 할 수 있어 일기에 대한 부담감을 줄여준다
- 하루동안 있었던 일들과 감정들을 정리하고 기록하면서 본인의 감정상태를 자세히 파악할 수 있다
- 지속될 경우, 경고를 통해 자가인식을 유도함
- 다른사람들과의 일기 공유를 통해 서로의 감정을 교류하며, SNS에 순기능을 이끌어낼 수 있다.
- AI의 분석과 추천 기능을 통해 서비스 사용에 재미 요소를 더할 수 있다