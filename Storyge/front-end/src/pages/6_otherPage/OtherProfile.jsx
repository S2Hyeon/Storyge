import React, { useEffect, useState } from "react";
import { useLocation } from "react-router";

import ProfileBox from "../../components/profileBox/ProfileBox";
import * as G from "../../styles";
import * as S from "../2_main/MainStyle";
import { BsCircleFill } from "react-icons/bs";
import CustomCalendar from "../../components/calender/Calendar";
import PieChart from "../../components/chart/PieChart";
import { getOtherUserData } from "api/user/getOtherUserData";
import { getCookie } from "utils/Cookies";

function OtherPage() {

  // console.log("현재 클릭한 페이지 유저의 id: ", props.userId);
  let [diary, setDiary] = useState(true);

  const location = useLocation();
  const [otherUserId] = useState(location.state.otherId);

  function switchBox() {
    setDiary(!diary);
  }

  //다른 사람 정보 가져오기
  const [otherUserData, setOtherUserData] = useState({});
  useEffect(() => {
    async function getAndSetOtherUserData() {
      const response = await getOtherUserData(otherUserId);
      setOtherUserData(response);
    }
    getAndSetOtherUserData();
  }, []);

  //1. 내가 팔로우하고 있다면 달력
  return (
    <S.All>
      <G.BodyContainer>
        {otherUserData && (
          <ProfileBox
            otherUserId={otherUserId}
            profileImg={otherUserData.profileImg}
            nickname={otherUserData.nickname}
            follower={otherUserData.follower}
            following={otherUserData.following}
          />
        )}

        <S.CalendarContainer>
          <S.CalendarBox>
            {diary ? <CustomCalendar /> : <PieChart />}
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
      </G.BodyContainer>
    </S.All>
  );
}

export default OtherPage;
