import React, { useEffect, useState } from "react";

import ChatBot from "react-simple-chatbot";
import { ThemeProvider } from "styled-components";
import { getUserData } from "api/user/getUserData";

const Chatbot = () => {
  const [userNickname, setUserNickname] = useState("");

  useEffect(() => {
    async function getUser() {
      const response = await getUserData();
      setUserNickname(response.nickname);
    }
    getUser();
  }, []);

  const steps = [
    {
      id: "0",
      message: `안녕하세요.
           Storyge 상담 챗봇입니다.`,
      trigger: "1",
    },
    {
      id: "1",
      message: `Storyge 서비스가 궁금하셨죠?            
            그럼 소개를 시작해볼까요?
            준비가 되셨다면 시작버튼을
            눌러 주세요.`,
      trigger: "2",
    },
    {
      id: "2",
      options: [{ value: 1, label: "시작하기", trigger: "3" }],
    },
    {
      id: "3",
      message: `어떤 점이 궁금하신가요?`,
      trigger: "4",
    },
    {
      id: "4",
      options: [
        { value: 1, label: "다이어리", trigger: "5" },
        { value: 2, label: "감정분석", trigger: "6" },
        { value: 3, label: "노래추천", trigger: "7" },
      ],
    },
    {
      id: "5",
      message: `다이어리 작성은 하루 24번 가능합니다. 일기 작성 후, 수정은 1번만 가능하니 유의해서 작성해주세요.😊 일기를 작성하시면, Storyge가 그때의 감정을 분석해드려요. 분석된 감정은 내 캘린더에 추가할 수 있습니다. Storyge를 통해 감정을 분석해보세요!`,
      trigger: "8",
    },
    {
      id: "6",
      message: `Storyge는 7개의 감정을 분석할 수 있습니다. 분석된 결과를 통해 감정 통계를 확인해보세요!`,
      trigger: "8",
    },
    {
      id: "7",
      message: `노래 추천은 하루 1번 가능합니다:) 노래를 추천받고 싶은 이야기를 적어주세요. Storyge가 상황에 어울리는 노래를 틀어드립니다. `,
      trigger: "8",
    },
    {
      id: "8",
      options: [
        { value: 1, label: "돌아가기", trigger: "3" },
        { value: 2, label: "종료하기", trigger: "9" },
      ],
    },
    {
      id: "9",
      message: "챗봇이 종료되었습니다.",
      end: true,
    },
  ];

  const theme = {
    background: "#f5f8fb",
    fontFamily: "S-CoreDream-4Regular",
    headerBgColor: "#EF6C00",
    headerFontColor: "#fff",
    headerFontSize: "15px",
    botBubbleColor: "#accebc",
    botFontColor: "#FFF",
    userBubbleColor: "#fff",
    userFontColor: "#4a4a4a",
  };

  return (
    <>
      <ThemeProvider theme={theme}>
        <ChatBot
          steps={steps}
          hideHeader={true}
          placeholder={"채팅이 불가능한 채널입니다."}
        />
      </ThemeProvider>
    </>
  );
};

export default Chatbot;
