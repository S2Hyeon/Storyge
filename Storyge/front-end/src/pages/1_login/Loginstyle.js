import styled from "styled-components";
import "./../../styles/index";

export const Login = styled.div`
  display: flex;
  justify-content: center;
  flex-direction: column;
  align-items: center;
  min-height: 100vh;

  &:a {
    display: flex;
    justify-content: space-evenly;
    align-items: center;
    width: 80%;
    height: 40px;
    text-decoration: none;
    border-radius: 100px;
    border: var(--size-border-thickness) solid var(--color-border-line);
    margin: 13%;
    font-size: var(--font-regular);
  }
  &:a:hover {
    filter: brightness(0.8);
    transition: var(--animation-duration);
  }
`;

export const GoogleBtn = styled.a`
  background-image: url(./../../assets/login/btn_google_signin_light_normal_web@2x.png);
  background-color: transparent;
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
  background-size: 90%;
`;
export const NaverBtn = styled.a`
  background-image: url(./../../assets/login/btnW_official.png);
  background-color: transparent;
  background-size: cover;
  background-position: center;
  background-size: 100%;
`;
export const KakaoBtn = styled.a`
  background-image: url(./../../assets/login/kakao_login_large_narrow.png);
  background-color: transparent;
  background-size: cover;
  background-position: center;
  background-size: 93%;
`;
