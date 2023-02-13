import Api from "lib/customApi";
import { getCookie } from "utils/Cookies";

export async function getOtherDiaryList(date, otherUserId) {
  try {
    const response = await Api.get(`/diary/daily/${date}/${otherUserId}`, {
      headers: {
        Authorization: getCookie("token"),
      },
    });
    return response.data;
  } catch (error) {
    console.error(error);
  }
}
