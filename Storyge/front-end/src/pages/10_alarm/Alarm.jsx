import React, { useEffect, useState } from "react";
import * as S from "./AlarmStyle";
import * as G from "styles";
import { getCookie } from "./../../utils/Cookies";
import Api from "lib/customApi";
import { useNavigate } from "react-router-dom";

export default function Alarm() {
  const [userData, setUserData] = useState([]);

  const movePage = useNavigate();

  function goDiaryPage(id) {
    movePage(`/diary`, { state : {diaryId : id}});
  }

  function goOtherPage(id) {
    movePage(`/otherpage`, {state : {otherId : id}});
  }

    function goMyFollowList() {
    movePage("/follower");
  }

  //처음 렌더링이 될 때만 실행
  useEffect(() => {
    async function getUserData() {
      try {
        const response = await Api.get("/notification", {
          headers: {
            Authorization: getCookie("token"),
          },
        });
        setUserData(response.data);
        console.log("알림페이지 : 알림 데이터");
        console.log(userData);
        console.log(response.data);
      } catch (err) {
        console.log(err);
      }
    }
    getUserData();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  return (
    <G.BodyContainer>
      {userData && (
        <S.List>
          {userData.map((alarm, key) => {
            if (alarm.notiType === "WAIT") {
              return (
                <S.Alarm key={key} onClick={ () => goOtherPage(alarm.follow) }>
                  <S.Img profile={alarm.profileImg}></S.Img>
                  <S.Text>
                    {alarm.nickname}님이 팔로우 요청을 보냈습니다.
                  </S.Text>
                </S.Alarm>
              );
            } else if (alarm.notiType === "REVIEW") {
              return (
                <S.Alarm key={key} onClick={() => goDiaryPage(alarm.diaryId)}>
                  <S.Img profile={alarm.profileImg}></S.Img>
                  <S.Text>{alarm.nickname}님이 댓글을 달았습니다.</S.Text>
                </S.Alarm>
              );
            } else if (alarm.notiType === "FOLLOW") {
              return (
                <S.Alarm key={key} onClick={goMyFollowList}>
                  <S.Img profile={alarm.profileImg}></S.Img>
                  <S.Text>
                    {alarm.nickname}님이 팔로우 요청을 수락했습니다.
                  </S.Text>
                </S.Alarm>
              );
            }
          })}
        </S.List>
      )}
    </G.BodyContainer>
  );
}
