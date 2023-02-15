import React, { useState } from "react";
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
      // "Content-Type": "text/evnent-stream",
      // "Cache-Control": "no-cache",
      // Connection: "keep-alive",
      // "X-Accel-Buffering": "no",
    },
    heartbeatTimeout: 1200000,
    withCredentials: true,
  });
  eventSource.addEventListener("connect", (e) => {
    const { data: receivedConnectData } = e;
    console.log("connected?", receivedConnectData);
  });
  eventSource.addEventListener("notification", function(event) {
    const { data: receivedConnectData } = event;
    console.log("noti?", receivedConnectData);
    setIsNewAlert(true);
  });

  return (
    <S.Nav>
      <S.IconContainer
        onClick={() => {
          movePage("/");
        }}
      >
        <TbHome
          size={30}
          color={location.pathname === "/" ? "#ACCEBC" : "#D9D9D9"}
        />
      </S.IconContainer>
      <S.IconContainer
        onClick={() => {
          movePage("/music");
        }}
      >
        <TbMusic
          size={30}
          color={location.pathname === "/music" ? "#ACCEBC" : "#D9D9D9"}
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
        <div style={{ position: "absolute" }}>
          <TbBell
            size={30}
            color={location.pathname === "/alarm" ? "#ACCEBC" : "#D9D9D9"}
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
              ? "#ACCEBC"
              : "#D9D9D9"
          }
        />
      </S.IconContainer>
    </S.Nav>
  );
}

export default Footer;
