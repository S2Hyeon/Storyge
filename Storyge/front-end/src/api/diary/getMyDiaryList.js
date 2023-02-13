import Api from "lib/customApi";
import { getCookie } from "utils/Cookies";

export async function getMyDiaryList(date) {
  try {
    const response = await Api.get(`/diary/daily/${date}`, {
      headers: {
        Authorization: getCookie("token"),
      },
    });
    return response.data;
  } catch (error) {
    console.error(error);
  }
}
