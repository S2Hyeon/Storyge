# Day 01/20

## To Do List
:white_check_mark: 공통 프로젝트 - JIRA의 활용<br/>
:white_check_mark: JSON 스키마<br/>
:white_check_mark: 프로젝트 프로토타입 완성 <br/>
---------
## 공통 프로젝트 - JIRA의 활용
**JIRA를 쓰는 이유**
- 애자일 팀에서 추천하는 최고의 도구
- 프로젝트의 체계적 관리
- 편리한 이슈 트래킹
- 프로젝트 전반에 대한 레퍼런스 확보 및 관리

**JIRA의 기본적 활용**
- Workflow
- BULK
- Components and Labels
- Releases

**Work flow**
- Ticket이 생성되고 완료될 때까지의 상태 변화
- 프로젝트 진행상황을 한눈에 파악 가능
- 불필요한 커뮤니케이션 감소

---------
## JSON 스키마
**JSON 스키마**
- JSON 데이터를 전송받는 측에서 전송 ㅂ다은 데이터갖 ㅓㄱ법한 형식의 데이터인지를 호가인하는 방법
- 적법한 jjson 데이터 형식을 기술한 문서

**JSON 스키마 검증 (validation)**
- 데이터의 타입이 정확한가?
- 필수로 받아와야 하는 데이터가 포함되어 있는가?
- 데이터가 원하는 범위 안에 있는가?
- JSON 스키마에서는 검증 기준을 모두 키워드를 이용하여 직접 명시

**메타 데이터 키워드**
 - 스키마에 대한 정보를 나타내는 메타 데이터 키워드
    - title : 스키마에 대한 이름
    - description : 스키마에 대한 설명 
    - example : 스키마 검증을 통과하는 예시 (배열 형태)
    - $comment : 특정 스키마에 주석을 남길 때 사용

**검증 키워드**
![검증키워드](https://velog.velcdn.com/images/my_id/post/d6c4f2ef-368e-4042-a8ee-971ba4d96b83/image.png)

**스키마 결합**
- JSON 스키마 결합 키워드 (배열 형태로 사용)
    - allOf
    - anyOf
    - one of
    - not
    - if-then-else (예외)

**참고 사이트** <br />
[JSON Schema: JSON 스키마란 무엇일까?](https://madplay.github.io/post/understanding-json-schema)
