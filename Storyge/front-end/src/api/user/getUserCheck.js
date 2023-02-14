import Api from "lib/customApi";
import { getCookie } from "utils/Cookies";

export async function getUserCheck(nickname) {
  try {
    const response = await Api.get(`/user/check/${nickname}`, {
      headers: {
        Authorization: getCookie("token"),
      },
    });
    return response.data;
  } catch (error) {
    console.error(error);
  }
}
