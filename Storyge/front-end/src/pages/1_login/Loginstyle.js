import styled from "styled-components";
import "../../styles/index";

export const Login = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  min-height: 100vh;
  margin: 20px;
`;

export const Logo = styled.img`
  width: 100%;
  margin-bottom: 5%;
`;

export const LoginText = styled.h2`
  margin-bottom: 60px;
  font-family: "S-CoreDream-6Bold";
  color: var(--color-black);
`;

export const LoginInfoText = styled.h2`
  margin-bottom: 20%;
  font-family: "S-CoreDream-6Bold";
  color: var(--color-black);
`;

export const NaverImg = styled.img`
  width: 330px;
  object-fit: scale-down;
`;
export const GoogleImg = styled.img`
  width: 330px;
  object-fit: scale-down;
`;
export const KakaoImg = styled.img`
  width: 330px;
  object-fit: scale-down;
`;

export const LinkBtn = styled.a`
  margin-bottom: 10px;
`;

export const SubmitBtn = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  width: 200px;
  height: 40px;
  border-radius: 12px;
  border: 0;
  background-color: #accebc;
  font-family: "S-CoreDream-4Regular";
  color: var(--color-white);
`;
