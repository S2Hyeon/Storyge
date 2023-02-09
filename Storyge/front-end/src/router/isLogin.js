import Cookies from "js-cookie";

export const isLogin = () => !!Cookies.get("token");
