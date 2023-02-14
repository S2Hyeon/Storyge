import styled from "styled-components";

export const Nav = styled.div`
  position: fixed;
  bottom: 0;
  display: flex;
  height: 70px;
  width: 100%;
  background: #ffffff;
  box-shadow: 0px 2px 15px rgba(184, 184, 210, 0.5);
`;

export const IconContainer = styled.div`
  height: 100%;
  width: 20%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;

export const AlertContainer = styled.div`
  height: 40px;
  width: 30px;
  margin-top: 3spx;
  margin-right: 5px;
  display: flex;
  justify-content: right;
`;

export const Alert = styled.div`
  height: 5px;
  width: 5px;
  border-radius: 100px;
  background-color: red;
`;

export const CenterCircle = styled.div`
  background-color: #accebc;
  height: 50px;
  width: 50px;
  border-radius: 100px;
  display: flex;
  justify-content: center;
  align-items: center;
  box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.25);
`;
