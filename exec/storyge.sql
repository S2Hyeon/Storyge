DROP DATABASE IF EXISTS storyge;
CREATE DATABASE storyge DEFAULT CHARACTER SET utf8mb4;
USE storyge;

CREATE TABLE IF NOT EXISTS `user`(
	`user_id` BIGINT AUTO_INCREMENT,
    `email` VARCHAR(50) NOT NULL,
    `nickname` VARCHAR(50),
    `profile_img` VARCHAR(2000) DEFAULT 'https://storyge-project.s3.ap-northeast-2.amazonaws.com/profile/KakaoTalk_20230203_100217288.jpg',
    `role` VARCHAR(20) NOT NULL,
    `provider` VARCHAR(10) NOT NULL,
    `provider_id` VARCHAR(50) NOT NULL,
    `created_at` TIMESTAMP DEFAULT now(),
    `name` VARCHAR(200) NOT NULL,
    PRIMARY KEY (user_id)
); 

CREATE TABLE IF NOT EXISTS `token`(
	`token_id` BIGINT AUTO_INCREMENT,
    `refresh_token` VARCHAR(300) NOT NULL,
    `user_id` BIGINT NOT NULL,
    PRIMARY KEY (token_id),
    FOREIGN KEY (user_id) REFERENCES `user` (user_id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS `diary_count`(
	`user_id` BIGINT NOT NULL,
    `diary_cnt` INT DEFAULT 0,
    PRIMARY KEY (user_id),
	FOREIGN KEY (user_id) REFERENCES `user` (user_id) ON UPDATE CASCADE ON DELETE CASCADE
);

create EVENT reset_diary_count
on SCHEDULE EVERY 1 DAY STARTS '2023-02-13 00:00:00'
COMMENT '일일 일기 제한 개수 초기화'
DO
update diary_count set diary_cnt = 0;

CREATE TABLE IF NOT EXISTS `follow`(
	`follow_id` BIGINT AUTO_INCREMENT,
    `following` BIGINT NOT NULL,
    `follower` BIGINT NOT NULL,
    PRIMARY KEY (follow_id),
    FOREIGN KEY (`following`) REFERENCES `user` (user_id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (`follower`) REFERENCES `user` (user_id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS `follow_waiting` (
	`waiting_id` BIGINT AUTO_INCREMENT,
    `following` BIGINT NOT NULL,
    `user_id` BIGINT NOT NULL,
    PRIMARY KEY (waiting_id),
	FOREIGN KEY (`following`) REFERENCES `user` (user_id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (`user_id`) REFERENCES `user` (user_id) ON UPDATE CASCADE ON DELETE CASCADE
);
    
    CREATE TABLE IF NOT EXISTS `diary`(
	`diary_id` BIGINT AUTO_INCREMENT,
    `user_id` BIGINT NOT NULL,
    `emoticon_name` VARCHAR(20),
    `diary_content` VARCHAR(100),
    `scope` INT,
    `update_cnt` INT DEFAULT 0,
    `analyzed_result` VARCHAR(1000),
    `created_at` TIMESTAMP,
    PRIMARY KEY (diary_id),
    FOREIGN KEY (`user_id`) REFERENCES `user` (user_id) ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS `notification`(
	`noti_id` BIGINT AUTO_INCREMENT,
	`user_id` BIGINT NOT NULL,
    `follow` BIGINT NOT NULL,
	`noti_type` VARCHAR(10),
    `diary_id` BIGINT,
    `created_at` TIMESTAMP DEFAULT now(),
    `read_check` INT DEFAULT 0,
    PRIMARY KEY (noti_id),
	FOREIGN KEY (`user_id`) REFERENCES `user` (user_id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (`follow`) REFERENCES `user` (user_id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (`diary_id`) REFERENCES `diary` (diary_id) ON UPDATE CASCADE ON DELETE CASCADE
);



CREATE TABLE IF NOT EXISTS `recent_diary`(
	`recent_id` BIGINT AUTO_INCREMENT,
	`user_id` BIGINT NOT NULL,
    `diary_id` BIGINT NOT NULL,
    `ends_at` TIMESTAMP NOT NULL DEFAULT now(),
    PRIMARY KEY (recent_id),
    FOREIGN KEY (`user_id`) REFERENCES `user` (user_id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (`diary_id`) REFERENCES `diary` (diary_id) ON UPDATE CASCADE ON DELETE CASCADE
);

create event delete_recent_diary
on schedule every 1 day starts '2023-02-14 04:00:00'
do
delete from recent_diary
where ends_at < now();

CREATE TABLE IF NOT EXISTS `read_diary`(
	`read_id` BIGINT AUTO_INCREMENT,
	`user_id` BIGINT NOT NULL,
    `recent_id` BIGINT NOT NULL,
    PRIMARY KEY (`read_id`),
	FOREIGN KEY (`user_id`) REFERENCES `user` (user_id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (`recent_id`) REFERENCES `recent_diary` (recent_id) ON UPDATE CASCADE ON DELETE CASCADE
);

create event delete_read_diary
on schedule every 1 day starts '2023-02-14 04:00:00'
do
delete from read_diary
where recent_id in (select recent_id from recent_diary where ends_at < now());

CREATE TABLE IF NOT EXISTS `daily_emotion`(
	`daily_id` BIGINT AUTO_INCREMENT,
    `user_id` BIGINT NOT NULL,
    `emoticon_name` VARCHAR(20) NOT NULL,
    `created_at` DATE NOT NULL,
	PRIMARY KEY (daily_id),
    FOREIGN KEY (`user_id`) REFERENCES `user` (user_id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS `review`(
	`review_id` BIGINT AUTO_INCREMENT,
    `user_id` BIGINT NOT NULL,
    `diary_id` BIGINT NOT NULL,
    `review_content` VARCHAR(50) NOT NULL,
    `created_at` TIMESTAMP DEFAULT now(),
    PRIMARY KEY (review_id),
    FOREIGN KEY (`user_id`) REFERENCES `user` (user_id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (`diary_id`) REFERENCES `diary` (diary_id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS `quote`(
	`quote_id` BIGINT AUTO_INCREMENT,
    `quote_content` VARCHAR(100) NOT NULL,
    `quote_source` VARCHAR(100),
    PRIMARY KEY (quote_id)
);

CREATE TABLE IF NOT EXISTS `today_quote`(
	`today_id` BIGINT AUTO_INCREMENT,
    `today_quote_id` BIGINT,
    PRIMARY KEY(`today_id`)
);
SELECT * FROM storyge.user;
SELECT * FROM storyge.token;
SELECT * FROM storyge.today_quote;
SELECT * FROM storyge.review;
SELECT * FROM storyge.recent_diary;
SELECT * FROM storyge.read_diary;
SELECT * FROM storyge.quote;
SELECT * FROM storyge.notification;
SELECT * FROM storyge.follow_waiting;
SELECT * FROM storyge.follow;
SELECT * FROM storyge.diary_count;
SELECT * FROM storyge.daily_emotion;
SELECT * FROM storyge.diary;

-- 명언 덤프파일  
INSERT INTO quote 
VALUES (1,'우리가 출발한 곳은 선택할 수 없지만,\n어딜 향해 갈 지는 선택할 수 있어.','영화 <월 플라워> 중'),(2,'인생에서 가장 큰 영광은 넘어지지 않는 것에 있는 것이 아니라n매번 일어선다는 데 있다. ','넬슨 만델라'),
(3,'결국, 여러분의 인생에서 중요한 것은\n지나가는 세월이 아닌 생활이다','아브라함 링컨'),(4,'일기예보에도 불구하고, \n봄처럼 살아라.','릴리 퓰리쳐'),
(5,'사랑에는 항상 광기가 존재한다.\n그러나 광기에는 항상 이유가 존재한다.','프레드릭 니체'),(6,'사랑은 내게 질문하지 않으며, \n다만 끝없는 지지를 준다.','윌리엄 세익스피어'),
(7,'성공이란 실패에 실패를 거듭하면서도\n열의를 잃지 않는 것이다. ','윈스턴 처칠'),(8,'더 나은 것을 위해\n좋은 것을 포기하는 걸 두려워 하지 마라.','존 록펠러'),
(9,'넌 도전했고 도전에는 용기가 필요하지,\n네가 자랑스럽구나.','영화 <리틀 미스 선샤인> 중'),(10,'천재성은 인종은 없고 강인함에는 남녀가 없으며\n용기에는 한계가 없다','영화 <히든 피겨스> 중'),
(11,'단지 두려움 때문에 좋아하는 일을 포기하지 마.','영화 <씽> 중'),(12,'인생을 바꿀 기회는 1분마다 찾아옵니다.','영화 <바닐라 스카이> 중'),
(13,'역경을 이겨내고 핀 꽃이 \n가장 아름다운 꽃이다.','영화 <뮬란> 중'),(14,'우리가 두려워할 것은 \n두려움 뿐이죠.','영화 <주토피아> 중'),
(15,'난 가고싶은 곳에 가기 위해 뛰었는데\n그게 삶의 기회가 될 줄 몰랐어요.','영화 <포레스트 검프> 중'),(16,'변화는 너로부터 시작해.','영화<주토피아> 중'),
(17,'꿈을 계속 간직하고 있으면 \n반드시 실현할 때가 온다','괴테'),(18,'오늘이라는 날은\n두 번 다시 오지 않는다는 것을 잊지 말라.','단테'),(19,'계획이란 미래에 대한 현재의 결정이다.','드래커'),
(20,'고통이 남기고 간 뒤를 보라 \n고난이 지나면 반드시 기쁨이 스며든다.','괴테'),(21,'선천적으로 현명한 사람은 없다. \n 시간이 모든 것을 완성한다 ','세르반테스'),(22,'한번의 실패와 영원한 실패를 혼동하지 마라.','F.스콧 핏제랄드'),
(23,'오랫동안 꿈을 그리는 사람은 \n마침내 그 꿈을 닮아 간다.','앙드레 말로'),(24,'행복은 습관이다,그것을 몸에 지니라.','허버드'),(25,'1퍼센트의 가능성, \n그것이 나의 길이다.','나폴레옹'),
(26,'고난의 시기에 동요하지 않는 것, \n이것은 진정 칭찬받을 만한 뛰어난 인물의 증거다.','베토벤'),(27,'사막이 아름다운 것은 \n어딘가에 샘이 숨겨져 있기 때문이다.','생텍쥐페리'),(28,'성공해서 만족하는 것은 아니다. \n만족하고 있었기 때문에 성공한 것이다.','알랭'),
(29,'당신이 할수 있다고 믿든 할수 없다고 믿든 \n믿는 대로 될것이다','헨리 포드'),(30,'겨울이 오면 봄이 멀지 않으리','셸리'),(31,'절대 어제를 후회하지 마라 . \n인생은 오늘의 나 안에 있고 \n내일은 스스로 만드는 것이다','L.론허바드'),
(32,'인생에 뜻을 세우는데 있어 \n늦은 때라곤 없다.','볼드윈'),(33,'이미끝나버린 일을 후회하기 보다는 \n하고 싶었던 일들을 하지못한 것을 후회하라.','탈무드'),(34,'실패는 잊어라. \n그러나 그것이 준 교훈은 절대 잊으면 안된다','하버트 개서'),
(35,'길을 잃는 다는 것은 \n곧 길을 알게 된다는 것이다.','동아프리카 속담'),(36,'먼저 핀 꽃은 먼저진다. \n남보다 먼저 공을 세우려고 조급히 서둘것이 아니다.','채근담'),(37,'고개 숙이지 마십시오. \n세상을 똑바로 정면으로 바라보십시오. ','헬렌 켈러'),
(38,'작은 기회로 부터\n종종 위대한 업적이 시작된다.','데모스테네스'),(39,'최고에 도달하려면 최저에서 시작하라.','P.시루스'),(40,'내 비장의 무기는 아직 손안에 있다.\n그것은 희망이다','나폴레옹'),
(41,'문제는 목적지에 얼마나 빨리 가느냐가 아니라 \n그 목적지가 어디냐는 것이다.','메이벨 뉴컴버'),(42,'노력을 이기는 재능은 없고\n노력을 외면하는 결과도 없다.','이창호'),(43,'그곳을 빠져나가는 가장 좋은 방법은\n그곳을 거쳐가는 것이다.','로버트 프로스트'),
(44,'당신은 뭔가 더 대단한 것을\n해낼 수 있다.','칼로스 M. 구티에레즈'),(45,'신은 용기 있는 자를 결코 버리지 않는다.','켄러'),(46,'모든 인생은 실험이다.\n더 많이 실험할수록 더 나아진다','랄프 왈도 에머슨'),
(47,'사람은 어려움 속에서 성장한다.','제임스 캐시 페니'),(48,'당신의 실수에서 교훈을 얻어라.','크레이그 뉴마크');

-- 우을 감정 확인을 위한 diary 덤프파일
 INSERT INTO diary (user_id, emoticon_name, diary_content, scope, update_cnt, analyzed_result, created_at)
 VALUES(8, 'sad', '슬프다', 1, 0, '분석결과', '2023-02-04 09:28:32'),
 (8, 'sad', '슬프다', 1, 0, '분석결과', '2023-02-05 09:28:32'),
 (8, 'sad', '슬프다', 1, 0, '분석결과', '2023-02-06 09:28:32'),
 (8, 'sad', '슬프다', 1, 0, '분석결과', '2023-02-07 09:28:32'),
 (8, 'sad', '슬프다', 1, 0, '분석결과', '2023-02-08 09:28:32'),
 (8, 'sad', '슬프다', 1, 0, '분석결과', '2023-02-09 09:28:32'),
 (8, 'sad', '슬프다', 1, 0, '분석결과', '2023-02-10 09:28:32'),
 (8, 'sad', '슬프다', 1, 0, '분석결과', '2023-02-11 09:28:32'),
 (8, 'sad', '슬프다', 1, 0, '분석결과', '2023-02-12 09:28:32'),
 (8, 'sad', '슬프다', 1, 0, '분석결과', '2023-02-13 09:28:32'),
 (8, 'sad', '슬프다', 1, 0, '분석결과', '2023-02-14 09:28:32'),
 (8, 'sad', '슬프다', 1, 0, '분석결과', '2023-02-15 09:28:32'),
 (8, 'sad', '슬프다', 1, 0, '분석결과', '2023-02-16 09:28:32');
 
 -- 우울 감정 확인을 위한 daily_emotion 덤프파일
 INSERT INTO daily_emotion(user_id, emoticon_name, created_at)
 VALUES (8, 'sad', '2023-02-04'),
 (8, 'sad', '2023-02-05'),
 (8, 'sad', '2023-02-06'),
 (8, 'sad', '2023-02-07'),
 (8, 'sad', '2023-02-08'),
 (8, 'sad', '2023-02-09'),
 (8, 'sad', '2023-02-10'),
 (8, 'sad', '2023-02-11'),
 (8, 'sad', '2023-02-12'),
 (8, 'sad', '2023-02-13'),
 (8, 'sad', '2023-02-14'),
 (8, 'sad', '2023-02-15'),
 (8, 'sad', '2023-02-16');
