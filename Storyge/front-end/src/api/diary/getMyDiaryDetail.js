import Api from "lib/customApi";
import { getCookie } from "utils/Cookies";

export async function getMyDiaryDetail(diaryId) {
  try {
    const response = await Api.get(`/diary/detail/${diaryId}`, {
      headers: {
        Authorization: getCookie("token"),
      },
    });
    return response.data;
  } catch (error) {
    console.error(error);
  }
}
