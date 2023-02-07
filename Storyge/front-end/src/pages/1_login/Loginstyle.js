import styled from "styled-components";
import "../../styles/index";

export const Login = styled.div`
  display: flex;
  justify-content: center;
  flex-direction: column;
  align-items: center;
  min-height: 100vh;
  margin: 20px;
`;

export const LoginText = styled.h2`
  margin-bottom: 40px;
  font-family: "S-CoreDream-6Bold";
  color: var(--color-black);
`;

export const LoginInfoText = styled.h2`
  margin-bottom: 40px;
  font-family: "S-CoreDream-6Bold";
  color: var(--color-black);
`;

export const Naver = styled.a`
  width: 80%;
  height: 60px;
  margin-bottom: 20px;
  background-size: contain;
  background-repeat: no-repeat;
  background-image: url("https://t1.daumcdn.net/cfile/tistory/99580C465C3D7D130C?original");
`;

export const Kakao = styled.a`
  width: 80%;
  height: 60px;
  margin-bottom: 20px;
  background-size: contain;
  background-repeat: no-repeat;
  background-image: url("https://t1.daumcdn.net/cfile/tistory/99BEE8465C3D7D1214?original");
`;

export const Google = styled.a`
  width: 80%;
  height: 60px;
  margin-bottom: 20px;
  background-size: contain;
  background-repeat: no-repeat;
  background-image: url("https://t1.daumcdn.net/cfile/tistory/998689465C3D7D1217?original");
`;
