import styled from "styled-components";

export const Box = styled.div`
  display: flex;
  width: 350px;
  height: 36px;
  margin-top: 20px;
`;

export const Follower = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #ACCEBC;
  width: 50%;
  border-radius:  8px 0px 0px 8px;
`;

export const Following = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #EEECEF;
  width: 50%;
  border-radius:  0px 8px 8px 0px;
`;

export const Text = styled.p`
  font-family: "S-CoreDream-5Medium";
  font-size: 14px;
  color: white;
`;