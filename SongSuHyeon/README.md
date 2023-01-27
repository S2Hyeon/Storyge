# Day 01/26

## To Do List
:white_check_mark: Modern Java와 Python<br/>
:white_check_mark: 프로토타입 디자인 최종<br/>
---------

## Modern Java와 Python
### Modern Java
**모던 자바란?**
- 함수형 프로그래밍 도입으로 큰 변화가 있었던 Java8 이후

**모던 자바의 특징**
- 함수형 패러다임 도입
- 쉬운 동시성(병렬처리) 도입
- 모듈성 강화
- 개발자 편의 API 추가

**함수형**
- 문자열 리스트에서 길이가 5~10인 것만 반복문, 조건문을 사용하지 않고 대문자로 출력하기
    - 모던 자바 <br/>
    `list.stream().filter(s -> s.length() >= 5 && s.length() <= 10).map(s -> s.toUpperCase()).forEach(System.out::println);`
    - 파이썬 <br/>
    `[s.upper() for s in str_lst iflen(s) in range(5, 11)]`
- 함수를 일급 시민(First Class Citizen)에 포함 -> 사실은...
- 익명 클래스의 번거로움을 람다로 간편하게, 메서드 참조로 재사용
- 코드 블록을 주입(동작 파라미터화)히거 조합(Pipeline)할 수 있게 됨
- 스트림의 기반, 병렬처리와 조화
- 주요 패키지, 클래스
    - @FuctionalInterface
    - java.util.function
    - Consumer, Supplier, Function, Predicate...
    - Operator
    - 기본형 Int, Long, Double

**함수형 : 람다, 메서드 참조**
- 람다(lambda) = 익명 함수, 익명 클래스를 대체
- 함수형 인터페이스(이름 있는 람다) : 하나의 추상 메서드를 가진 인터페이스
- 메서드 참저 : 메서드나 생성자를 참조하기 (::)
- 예시 : 문자열 리스트를 길이에 따라 정렬
    - 자바
        ```
        Collections.sort( words, new Comporator<String>() {
            public int compare(String o1, String o2) {
                return Integer.compare o1.length(), o2.length() );
            }
        } );
        ```
    - 모던 자바
        ```
        Collections.sort(words, (o1, o2) -> Integer.compare (o1.length(), o2.length() ));
        ```
        ```
        Collections.sort(words, Comparator.comparingInt(String::length));
        ```
        ```
        words.sort(Comparator.comparingInt(String::length));
        ```

**스트림**
- 컬렉션 + 함수형, 데이터 처리 연산을 지원하도록 소스에서 추출된 연속된 요소
    - 외부순환(for, while) vs 내부 순환(VM아 이것 좀 해줘)
    - SQL처럼 선언형 스타일로 데이터를 처리
    - 쉽게 병렬처리 적용 : parallelStream 메서드
- 주요 패키지, 클래스, 메서드
    - java.util.stream
    - BaseStream, Stream
    - map(). filter(), reduce(), min()
    - C.stream(), C.parallelStrea()
- 중간연산과 최종연산
    - ![스트림 연산](./Day%200127/stream.png)
    - 중간 연산 : 스트림을 반환, 여러 연산 조합 가능
    - 최종 연산은 스트림을 모두 소비하고 닫음
    - 스트림은 1회용 (최종 연산 이후 사용 불가)
- 예제
    - ![](./Day%200127/stream2.png)

### Python
**파이썬**
- 원래 함수형 (v1.0, since 1994)
- 내장 컬렉션 = 리스트, 맵(딕셔너리), 튜플, 세트
- lambda, itertools, functools, generator
**병렬/동시성**
- 저수준 병렬 처리의 어려움 : Thread, Lock, synchronized
    - 많이 사요되는 패턴들을 언어 차원에서 API로 지원
    - 고수준, 추상화, Tread Safety, 비동기 지원
- 주요 패키지, 클래스
    - java.util.concurrent
    - Executor(s), ExecutorService
    - xxThreadPool, ForkJoinPool
    - Futere, CompletableFuture
    - Runnable, Callable

**Executor / Service / Etc**
- Thread를 직접 생성, 관리하지 않고 ExecutorService에서 스레드 관리
- 작업(Runnable, Callable)을 Executor 서비스에 요청하고 결과 받기
- 작업 스케줄리(cron, at) 기능 : ScheduledExecutorService
- Concurrent Collection
    - 스레드 안전한(Thread Safe) List / Map 제공
- Atomic Variable
    - 변수 자체가 원자성을 보장
- Lock 객체
    - 동기화 패턴에 따라 사용할 수 있는 유틸리티

**비동기 지원 (Async)**
    ![비동기](./Day%200127/async.png)
