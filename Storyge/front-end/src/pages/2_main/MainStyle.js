import styled from "styled-components";

export const All = styled.div`
  height: 100vh;
  width: 100vw;
  background-color: var(--color-background);
`;

export const NewDiary = styled.div`
  height: 62px;
  width: 100vh;
  border: 1px solid red;
  display: flex;
  justify-content: center;

  margin-top: 10px;
  margin-bottom: 10px;

  overflow-x: scroll;
`;

export const Profile = styled.div`
  height: 60px;
  width: 60px;
  border-radius: 100px;
  flex-direction: column;
  // margin-left: 5px;
  // margin-right: 5px;
  // flex: 0 0 auto;
  background-color: black;
`;

export const Container = styled.div`
  padding-left: 20px;
  padding-right: 20px;
`;

export const CalendarContainer = styled.div`
  width: 100%;
  margin: 20px 0 20px 0;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;

export const CalendarBox = styled.div`
  width: 100%;
  min-height: 390px;
  background: var(--color-white);
  box-shadow: 0px 16px 40px -4px rgba(112, 144, 176, 0.2);
  border-radius: 12px;
`;

export const CalendarToggle = styled.div`
  width: 100%;
  font-family: "S-CoreDream-5Medium";
  font-size: 12px;
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 5px;
`;

export const ToggleOne = styled.div`
  margin: 0 5px;
`;

export const WiseBox = styled.div`
  width: 100%;
  height: 108px;
  background: var(--color-white);
  box-shadow: 0px 16px 40px -4px rgba(112, 144, 176, 0.2);
  border-radius: 12px;
  margin: 10px 0;
  font-family: "SeoulHangangM";
  font-size: 12px;
`;

export const Wise = styled.div`
  padding-top: 25px;
  line-height: 130%;
`;

export const WiseFrom = styled.div`
  color: #b8b8d2;
  padding-top: 10px;
`;
