// OAuth.js 라는 컴포넌트를 따로 생성하여 관리하였음

const KAKAO_CLIENT_ID = "dd18f3127a0437b4a57b38e093cdcb7b";
const KAKAO_REDIRECT_URI = "http://localhost:3000/oauth/callback/kakao";
export const KAKAO_AUTH_URL = `https://kauth.kakao.com/oauth/authorize?client_id=${KAKAO_CLIENT_ID}&redirect_uri=${KAKAO_REDIRECT_URI}&response_type=code&state=kakao`;

export const NAVER_CLIENT_ID = "lcJvJuk5zQePdaqAV108";
const NAVER_REDIRECT_URI = "http://localhost:3000/oauth/callback/naver";
export const NAVER_AUTH_URL = `https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=${NAVER_CLIENT_ID}&redirect_uri=${NAVER_REDIRECT_URI}&state=naver`;

const GOOGLE_CLIENT_ID =
  "632690347836-0tk1p98e4eccl0roh47mmqrbr770q43s.apps.googleusercontent.com";
const GOOGLE_REDIRECT_URI = "http://localhost:3000/oauth/callback/google";
export const GOOGLE_AUTH_URL = `https://accounts.google.com/o/oauth2/v2/auth?scope=https%3A//www.googleapis.com/auth/drive.metadata.readonly&access_type=offline&include_granted_scopes=true&response_type=code&state=google&redirect_uri=${GOOGLE_REDIRECT_URI}&client_id=${GOOGLE_CLIENT_ID}`;
