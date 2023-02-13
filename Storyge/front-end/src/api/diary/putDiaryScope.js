import Api from "lib/customApi";
import { getCookie } from "utils/Cookies";

export async function putDiaryScope(diaryId, scope) {
  try {
    console.log(diaryId, scope);
    const response = await Api.put(`/diary/scope/${diaryId}/${scope}`, {
      headers: {
        Authorization: getCookie("token"),
      },
    });
    console.log(response.data);
  } catch (error) {
    console.error(error);
  }
}
