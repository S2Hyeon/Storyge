import styled from "styled-components";

export const All = styled.div`
  height: 100vh;
  width: 100vw;
  background-color: #eaf2ea;
`;

export const NewDiary = styled.div`
  height: 62px;
  width: 100%;
  border: 1px solid red;
  display: flex;
  justify-content: center;

  margin-top: 10px;
  margin-bottom: 10px;
  padding-left: 30px;

  overflow-x: auto;
  white-space: nowrap;
`;

export const Profile = styled.div`
  height: 60px;
  width: 60px;
  border-radius: 100px;
  border: 1px solid red;
  flex-direction: column;
  margin-left: 5px;
  margin-right: 5px;
  flex: 0 0 auto;
`;

export const Container = styled.div`
  padding-left: 20px;
  padding-right: 20px;
  // background-color: #eaf2ea;
`;

export const WiseBox = styled.div`
  width: 100%;
  height: 108px;
  background: #ffffff;
  box-shadow: 0px 16px 40px -4px rgba(112, 144, 176, 0.2);
  border-radius: 12px;
  margin: 20px 0 20px 0;
`;
