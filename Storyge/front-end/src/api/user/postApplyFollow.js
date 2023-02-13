import Api from "lib/customApi";
import { getCookie } from "utils/Cookies";

export async function postApplyFollow(otherUserId) {
  try {
    await Api.post(
      "/follow-wait",
      { userId: otherUserId },
      {
        headers: {
          Authorization: getCookie("token"),
        },
      }
    );
  } catch (error) {
    console.error(error);
  }
}
