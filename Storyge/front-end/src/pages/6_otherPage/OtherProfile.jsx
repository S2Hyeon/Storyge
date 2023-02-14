import React, { useEffect, useState } from "react";
import { useLocation } from "react-router";

import ProfileBox from "../../components/profileBox/ProfileBox";
import * as G from "../../styles";
import * as S from "./OtherProfileStyle";
import { BsCircleFill } from "react-icons/bs";
import CustomCalendar from "../../components/calender/Calendar";
import PieChart from "../../components/chart/PieChart";
import { getOtherUserData } from "api/user/getOtherUserData";
import { getIsFollowing } from "api/user/getIsFollowing";
import { TbLock } from "react-icons/tb";

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
  const [isAlreadyApply, setIsAlreadyApply] = useState(false);
  useEffect(() => {
    async function getAndSetOtherUserData() {
      const response = await getOtherUserData(otherUserId);
      setOtherUserData(response.user);
      setIsAlreadyApply(response.scope);
    }
    getAndSetOtherUserData();
  }, []);

  //내가 팔로우 하고 있는지 확인
  const [isFollowing, setIsFollowing] = useState();
  useEffect(() => {
    async function getAndSetIsFollowing() {
      const response = await getIsFollowing(otherUserId);
      setIsFollowing(response);
    }
    getAndSetIsFollowing();
  }, []);

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
            isAlreadyApply={isAlreadyApply}
            test={setIsFollowing}
          />
        )}

        {!isFollowing ? (
          <S.CantSee>
            <div>
              <TbLock size="30" />
            </div>
            <div>
              <br />
              아직은 {otherUserData.nickname}님의 일기를 볼 수 없어요!
            </div>
            <div>일기를 보고 싶다면 팔로우를 걸어보세요</div>
          </S.CantSee>
        ) : (
          <S.CalendarContainer>
            <S.CalendarBox>
              {diary ? (
                <CustomCalendar userId={otherUserId} />
              ) : (
                <PieChart userId={otherUserId} />
              )}
            </S.CalendarBox>
            <S.CalendarToggle onClick={() => switchBox()}>
              <S.ToggleOne>
                <BsCircleFill
                  size={7}
                  color={
                    diary ? "var(--color-primary)" : "var(--color-darkgrey)"
                  }
                />
                <span
                  style={{
                    color: diary
                      ? "var(--color-black)"
                      : "var(--color-darkgrey)",
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
        )}
      </G.BodyContainer>
    </S.All>
  );
}

export default OtherPage;
