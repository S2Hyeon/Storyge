import { Cookies } from "react-cookie";

const cookies = new Cookies();

// 쿠키를 저장하는 함수 : key, value, option 세 가지 파라미터
// path : 쿠키 값을 저장하는 서버 경로
// expires : 쿠키 만료 시간, Date 객체를 받음
// httpyOnly: JS의 document.cookie를 이용해서 쿠키에 접속하는 것을 차단해 비정상적인 접근을 막음
// secure: http로 통신하는 경우만 쿠키를 서버로 전송
export const setCookie = (name, value, option) => {
  return cookies.set(name, value, { ...option });
};

// 쿠키를 가지고 오는 함수
export const getCookie = (name) => {
  return cookies.get(name);
};

// 쿠키를 삭제하는 함수 : key, option 두 가지 파라미터
export const removeCookie = (name, option) => {
  return cookies.remove(name, { ...option });
};
