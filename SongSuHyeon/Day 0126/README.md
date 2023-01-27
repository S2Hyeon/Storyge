# Day 01/26

## To Do List
:white_check_mark: NGINX<br/>
:white_check_mark: 프로토타입 디자인 최종<br/>
---------

## NGINX

**Web Server V/S WAS**
웹 서버 (Web Server)
- 정적인 데이터(.html, .jpeg, .css) 처리
- node.js
웹 어플리케이션 서버 (WAS : Web Application Server)
- DB 조회나 다양한 로직 처리를 요구하는 동적인 컨텐츠를 제공하기 위해 만들어진 Application Server
- WAS = Web Server + Web Container

**APACHE 구조**
![APACHE 구조](https://velog.velcdn.com/images%2Fdeannn%2Fpost%2Fd47876a7-25a9-4ea3-adf5-46b3d5af59c5%2Fimage.png)

**NGINX**
- 트래픽이 많은 웹사이트의 서버를 도와주는 비동기 이벤트 기반 구조의 경량화 엡 서버 프로그램
- 아파치 C10K(connection 10000 problem) 문제를 해결하기 위하여 개발
- Web Client -> Web Server -> WAS -> DB


**NGINX 동작**
- worker porcess를 생성하는 master porcess가 있음
- worker porcess가 만들어질 때 지정된 listen 소켓을 ㅐ벚ㅇ
- 소켓에 새로운 클라이언트의 요청이 들어오면 connection을 형성하고 처리
- 이벤트 기반 방식 여러개의 connection을 전부 Event Handler를 통해 비동기 방식으로 처리
- NGINX 구조
    ![NGINX 구조](https://velog.velcdn.com/images%2Fdeannn%2Fpost%2Fb4a393fa-1330-4e5c-b20d-576337c8c844%2Fimage.png)


**NGINX 특징**
- 리버스 프록시 서버
- SSL 지원
- 비동기 처리

**리버스 프록시 서버**
- 장점
    - 로드 밸런싱 : 서버가 처리해야 할 업무 혹은 요청(Load)을 여러 대의 서버로 나누어(Balancing) 처리하는 것
    - 캐싱
    - 압축
    - SSL
    - DDosS 공격 완화
- 구조
    ![리버스 프록시 서버 구조](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbwaXRe%2Fbtq7WvS7NNC%2FxNhYKAVe9goKzYfKKxbfnk%2Fimg.png)

**추가**
- Node.js에 원작자인 라이언 달은 Nginx를 프록시 서버로 앞단에 놓고 node.js를 뒤쪽에 놓는게 버퍼 오버플로우 취약점에 의한 공격을 어느정도 방지한다고 말했다. 여기서 버퍼 오버플로우란 메모리 공간을 벗어나는 경우 오버플로우가 되고 이 때 사용되지 않아야 할 영역에 데이터가 덮어씌워져 주소, 값을 바꾸는 공격이다. 이처럼 Nginx를 앞단에 둠으로써 보안적인 이슈도 방지가 가능하다.