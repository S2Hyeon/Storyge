import styled from "styled-components";

export const All = styled.div`
  height: 90vh;
  // width: 100%;
  background-color: var(--color-background);
`;

export const NewDiary = styled.div`
  margin: 60px 0 0 0;
  padding: 10px 20px;
  display: flex;
  flex-wrap: nowrap;
  overflow-x: auto;
  &::-webkit-scrollbar {
    display: none;
  }
`;

export const Profile = styled.div`
  height: 70px;
  width: 70px;
  margin: 0 8px;
  background-image: url(${(props) => props.profile});
  background-size: cover;
  border-radius: 100px;
  // outline: 3px solid var(--color-primary);
  // flex: 0 0 auto;

  flex: 0 0 auto;
`;

export const Container = styled.div`
  padding-left: 20px;
  padding-right: 20px;
`;

export const CalendarContainer = styled.div`
  width: 100%;
  padding: 20px 0;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;

export const CalendarBox = styled.div`
  min-height: 10px;
  width: 100%;
  background: var(--color-white);
  box-shadow: 0px 16px 40px -4px rgba(112, 144, 176, 0.2);
  border-radius: 12px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-item: center;
  padding: 20px 0;
`;

export const CalendarToggle = styled.div`
  width: 100%;
  font-family: "S-CoreDream-5Medium";
  font-size: 12px;
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 10px;
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
  text-align: center;
`;

export const Wise = styled.div`
  padding-top: 25px;
  line-height: 130%;
  white-space: pre-wrap;
`;

export const WiseFrom = styled.div`
  color: #b8b8d2;
  padding-top: 10px;
`;
