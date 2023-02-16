import Api from "lib/customApi";
import { getCookie } from "utils/Cookies";

export async function putUserNickname(nickName) {
  try {
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
