import Api from "lib/customApi";
import { getCookie } from "utils/Cookies";

export async function getCount(diaryId) {
  try {
    const response = await Api.get(`/diary/count`, {
      headers: {
        Authorization: getCookie("token"),
      },
    });
    return response.data;
  } catch (error) {
    console.error(error);
  }
}
