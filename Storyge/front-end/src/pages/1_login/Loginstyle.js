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

export const LoginText = styled.h2`
  margin-bottom: 60px;
  font-family: "S-CoreDream-6Bold";
  color: var(--color-black);
`;

export const LoginInfoText = styled.h2`
  margin-bottom: 40px;
  font-family: "S-CoreDream-6Bold";
  color: var(--color-black);
`;

export const NaverImg = styled.img`
  width: 350px;
  object-fit: scale-down;
`;
export const GoogleImg = styled.img`
  width: 350px;
  object-fit: scale-down;
`;
export const KakaoImg = styled.img`
  width: 350px;
  object-fit: scale-down;
`;

export const LinkBtn = styled.a`
  margin-bottom: 50px;
`;
