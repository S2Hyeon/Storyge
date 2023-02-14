import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import * as S from "./MainStyle";
import * as G from "../../styles";
import { BsCircleFill } from "react-icons/bs";
import CustomCalendar from "../../components/calender/Calendar";
import PieChart from "../../components/chart/PieChart";
import { getCookie } from "./../../utils/Cookies";
import { getQuote } from "api/quote/getQuote";
import { getRecentDiary } from "api/recentDiary/getRecentDiary";
import { EventSourcePolyfill, NativeEventSource } from "event-source-polyfill";
import Modal from "./Modal";

function Main() {
  const [isGloomy, setIsGloomy] = useState(false);

  // 로그인 여부 확인 : 쿠기 값 가져오기
  useEffect(() => {
    const ACCESS_TOKEN = getCookie("token");
    console.log("메인 실행되면 액세스 토큰 받아옴" + ACCESS_TOKEN);
  }, []);

  const movePage = useNavigate();

  let [diary, setDiary] = useState(true);

  //새로 업데이트 된 글로 이동!
  function goUpdatedDiary(diaryId, otherUserId) {
    console.log("toner user id ", otherUserId);
    movePage("/diary", {
      state: { diaryId: diaryId, otherUserId: otherUserId },
    });
  }

  //달력 보일건지 통계 보일건지 전환하는 함수
  function switchBox() {
    setDiary(!diary);
  }

  //내가 팔로잉하는 사람의 업데이트 내용 받기 api
  const [recentDiaryData, setRecentDiaryData] = useState([]);
  useEffect(() => {
    async function getAndSetRecentDiaryData() {
      const response = await getRecentDiary();
      console.log("응답", response);
      setRecentDiaryData(response);
    }
    getAndSetRecentDiaryData();
  }, []);

  //하루 글귀 받아오기 api
  const [quoteData, setQuoteData] = useState();
  useEffect(() => {
    async function getAndSetQuoteData() {
      const response = await getQuote();
      setQuoteData(response);
    }
    getAndSetQuoteData();
  }, []);

  return (
    <S.All>
      {recentDiaryData.length > 0 ? (
        <S.NewDiary>
          {recentDiaryData.map((recentDiary) => {
            return (
              <S.Profile
                key={recentDiary.userId}
                profile={recentDiary.profileImg}
                onClick={() =>
                  goUpdatedDiary(recentDiary.diaryId, recentDiary.userId)
                }
              />
            );
          })}
        </S.NewDiary>
      ) : (
        <S.NoNewDiary>
          아무것도 없어요
          <br />뭘 띄워야할까
        </S.NoNewDiary>
      )}

      <G.BodyContainer top="0" bottom="70px" color="true">
        <S.CalendarContainer>
          <S.CalendarBox>
            {diary ? (
              <CustomCalendar userId={-100} setIsGloomy={setIsGloomy} />
            ) : (
              <PieChart userId={-100} />
            )}
          </S.CalendarBox>
          <S.CalendarToggle onClick={() => switchBox()}>
            <S.ToggleOne>
              <BsCircleFill
                size={7}
                color={diary ? "var(--color-primary)" : "var(--color-darkgrey)"}
              />
              <span
                style={{
                  color: diary ? "var(--color-black)" : "var(--color-darkgrey)",
                }}
              >
                {" "}
                다이어리
              </span>
            </S.ToggleOne>
            <S.ToggleOne>
              <BsCircleFill
                size={7}
                color={
                  !diary ? "var(--color-primary)" : "var(--color-darkgrey)"
                }
              />
              <span
                style={{
                  color: !diary
                    ? "var(--color-black)"
                    : "var(--color-darkgrey)",
                }}
              >
                {" "}
                통계
              </span>
            </S.ToggleOne>
          </S.CalendarToggle>
        </S.CalendarContainer>

        <S.WiseBox>
          <S.Wise>{quoteData && quoteData.quoteContent}</S.Wise>
          <S.WiseFrom>{quoteData && quoteData.quoteSource}</S.WiseFrom>
        </S.WiseBox>
      </G.BodyContainer>
      {isGloomy && <Modal setIsGloomy={setIsGloomy} />}
    </S.All>
  );
}

export default Main;
