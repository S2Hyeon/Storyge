import Api from "lib/customApi";
import { getCookie } from "utils/Cookies";

export async function getIsFollowing(otherUserId) {
  try {
    const response = await Api.get(`/following/check/${otherUserId}`, {
      headers: {
        Authorization: getCookie("token"),
      },
    });
    return response.data;
  } catch (error) {
    console.error(error);
  }
}
