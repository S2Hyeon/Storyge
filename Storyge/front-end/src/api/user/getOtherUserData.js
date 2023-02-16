import Api from "lib/customApi";
import { getCookie } from "utils/Cookies";

export async function getOtherUserData(otherUserId) {
  try {
    const response = await Api.get(`/user/${otherUserId}`, {
      headers: {
        Authorization: getCookie("token"),
      },
    });
    return response.data;
  } catch (error) {
    console.error(error);
  }
}
