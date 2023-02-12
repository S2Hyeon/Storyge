import Api from "lib/customApi";
import { getCookie } from "utils/Cookies";

export async function deleteFollowing(otherUserId) {
  try {
    await Api.delete(`/following/${otherUserId}`, {
      headers: {
        Authorization: getCookie("token"),
      },
    });
  } catch (error) {
    console.error(error);
  }
}
