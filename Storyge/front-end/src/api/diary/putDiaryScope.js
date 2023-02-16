import Api from "lib/customApi";
import { getCookie } from "utils/Cookies";

export async function putDiaryScope(diaryId, scope) {
  try {
    await Api.put(
      `/diary/scope/${diaryId}/${scope}`,
      {}, // put은 data를 무조건 받아와야하기 때문에 빈 값이더라도 보내줘야함
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
