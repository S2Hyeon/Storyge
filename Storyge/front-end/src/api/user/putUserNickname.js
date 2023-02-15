import Api from "lib/customApi";
import { getCookie } from "utils/Cookies";

export async function putUserNickname(nickName) {
  try {
    console.log("유저 닉네임 변경 >>>>>>>>.. ");
    console.log(nickName);
    await Api.put(
      "/user/change/nickname",
      { nickname: nickName },
      {
        headers: {
          Authorization: getCookie("token"),
          "Content-Type": `application/json`,
        },
      }
    );
  } catch (error) {
    console.error(error);
  }
}
