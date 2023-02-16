import React, { useState, useEffect } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import * as S from "./NavStyle";
import { TbHome, TbMusic, TbPlus, TbBell, TbUser } from "react-icons/tb";
import { EventSourcePolyfill } from "event-source-polyfill";
import { getCookie } from "utils/Cookies";

function Footer(props) {
  const location = useLocation();
  const movePage = useNavigate();

  //알림 실시간 처리
  const [isNewAlert, setIsNewAlert] = useState(false);
  const eventSource = new EventSourcePolyfill("https://storyge.xyz/api/sub", {
    headers: {
      Authorization: getCookie("token"),
      "Content-Type": "text/event-stream",
      "Cache-Control": "no-cache",
      Connection: "keep-alive",
      "X-Accel-Buffering": "no",
    },
    heartbeatTimeout: 61000,
    withCredentials: true,
  });
  eventSource.addEventListener("connect", (e) => {
    const { data: receivedConnectData } = e;
  });
  eventSource.addEventListener("notification", function(event) {
    const { data: receivedConnectData } = event;
    setIsNewAlert(true);
  });
  // const [isNewAlert, setIsNewAlert] = useState(false);
  // useEffect(() => {
  //   let eventSource;
  //   const fetchSse = async () => {
  //     try {
  //       eventSource = new EventSource("https://storyge.xyz/api/sub", {
  //         headers: {
  //           Authorization: getCookie("token"),
  //           "Content-Type": "text/event-stream",
  //           "Cache-Control": "no-cache",
  //           Connection: "keep-alive",
  //           "X-Accel-Buffering": "no",
  //         },
  //         heartbeatTimeout: 12000,
  //         withCredentials: true,
  //       });
  
  //       //이벤트 소스에서 새로운 응답이 있을 경우
  //       eventSource.onmessage = async (event) => {
  //         const response = await event.data;
  //         console.log(response); //뭐가 넘어오는지 확인해보기!!!
  //         setIsNewAlert(true);
  //       };
  
  //       //에러가 발생했을 경우
  //       eventSource.onerror = async (event) => {
  //         // No activity가 포함되지 않았을 경우 이벤트소스 닫기
  //         eventSource.close();
  //       };
  //     } catch (error) {}
  //   };
  //   fetchSse();
  //   return () => eventSource.close();
  // });
  return (
    <S.Nav>
      <S.IconContainer
        onClick={() => {
          movePage("/");
        }}
      >
        <TbHome
          size={30}
          color={location.pathname === "/" ? "var(--color-primary)" : "#D9D9D9"}
        />
      </S.IconContainer>
      <S.IconContainer
        onClick={() => {
          movePage("/music");
        }}
      >
        <TbMusic
          size={30}
          color={
            location.pathname === "/music" ? "var(--color-primary)" : "#D9D9D9"
          }
        />
      </S.IconContainer>
      <S.IconContainer
        onClick={() => {
          movePage("/createDiary");
        }}
      >
        <S.CenterCircle>
          <TbPlus size={30} color="white" />
        </S.CenterCircle>
      </S.IconContainer>
      <S.IconContainer
        onClick={() => {
          movePage("/alarm");
          setIsNewAlert(false);
        }}
        style={{ position: "relative" }}
      >
        <div style={{ position: "absolute", height: "30px" }}>
          <TbBell
            size={30}
            color={
              location.pathname === "/alarm"
                ? "var(--color-primary)"
                : "#D9D9D9"
            }
          />
        </div>
        {isNewAlert ? (
          <S.AlertContainer style={{ position: "absolute" }}>
            <S.Alert />
          </S.AlertContainer>
        ) : null}
      </S.IconContainer>

      <S.IconContainer
        onClick={() => {
          movePage("/mypage");
        }}
      >
        <TbUser
          size={30}
          color={
            location.pathname === "/mypage" || location.pathname === "/follower"
              ? "var(--color-primary)"
              : "#D9D9D9"
          }
        />
      </S.IconContainer>
    </S.Nav>
  );
}

export default Footer;
