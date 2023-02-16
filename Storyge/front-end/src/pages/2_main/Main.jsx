import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import * as S from "./MainStyle";
import * as G from "../../styles";
import { BsCircleFill } from "react-icons/bs";
import CustomCalendar from "../../components/calender/Calendar";
import PieChart from "../../components/chart/PieChart";
import { getCookie, setCookie } from "./../../utils/Cookies";
import { getQuote } from "api/quote/getQuote";
import { getRecentDiary } from "api/recentDiary/getRecentDiary";
import Modal from "./Modal";
import dayjs from "dayjs";

function Main() {
  const [isGloomy, setIsGloomy] = useState(false);
  const [showGloomy, setShowGloomy] = useState(false);
  const curDate = dayjs(new Date()).format("YYYY-MM-DD");

  //우울하시네요 모달창 띄우는거 하루로 제한
  useEffect(() => {
    //14일동안 우울하다면 저장
    if (isGloomy) {
      if (
        getCookie("modalDate") != null &&
        curDate != dayjs(getCookie("modalDate")).format("YYYY-MM-DD")
      ) {
        let expires = new Date();
        expires = expires.setHours(expires.getHours() + 24);
        setCookie("modalDate", new Date());
        setShowGloomy(true);
      } else if (
        getCookie("modalDate") != null &&
        curDate == dayjs(getCookie("modalDate")).format("YYYY-MM-DD")
      ) {
        setShowGloomy(false);
      } else {
        let expires = new Date();
        expires = expires.setHours(expires.getHours() + 24);
        setCookie("modalDate", new Date());
        setShowGloomy(true);
      }
    }
  }, [isGloomy]);

  // 로그인 여부 확인 : 쿠키 값 가져오기
  useEffect(() => {
    const ACCESS_TOKEN = getCookie("token");
  }, []);

  const movePage = useNavigate();

  let [diary, setDiary] = useState(true);

  //새로 업데이트 된 글로 이동!
  function goUpdatedDiary(diaryId, otherUserId, nickname) {
    movePage("/diary", {
      state: { diaryId: diaryId, otherUserId: otherUserId, nickname: nickname },
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
    <>
      {recentDiaryData.length > 0 ? (
        <S.NewDiary>
          {recentDiaryData.map((recentDiary) => {
            return (
              <S.NewDiaryContainer key={recentDiary.diaryId}>
                <S.ProfileContainer>
                  <S.Profile
                    key={recentDiary.userId}
                    profile={recentDiary.profileImg}
                    onClick={() =>
                      goUpdatedDiary(
                        recentDiary.diaryId,
                        recentDiary.userId,
                        recentDiary.nickname
                      )
                    }
                  />
                </S.ProfileContainer>
                <S.ProfileNickName>{recentDiary.nickname}</S.ProfileNickName>
              </S.NewDiaryContainer>
            );
          })}
        </S.NewDiary>
      ) : (
        <S.NoNewDiary>팔로잉하는 사람들의 최근 일기가 없어요🥲</S.NoNewDiary>
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
                color={diary ? "var(--color-primary)" : "var(--color-grey)"}
              />
              <span
                style={{
                  color: diary ? "var(--color-black)" : "var(--color-grey)",
                }}
              >
                {" "}
                다이어리
              </span>
            </S.ToggleOne>
            <S.ToggleOne>
              <BsCircleFill
                size={7}
                color={!diary ? "var(--color-primary)" : "var(--color-grey)"}
              />
              <span
                style={{
                  color: !diary ? "var(--color-black)" : "var(--color-grey)",
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
      {isGloomy && showGloomy && <Modal setIsGloomy={setIsGloomy} />}
    </>
  );
}

export default Main;
