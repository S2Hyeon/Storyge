import Api from "lib/customApi";
import { getCookie } from "utils/Cookies";

export async function putDiary(diary, content, diaryId) {
  try {
    const response = await Api.put(
      "/diary",
      {
        analizedResult: content[1],
        diaryContent: diary,
        emoticonName: content[0],
        scope: 1,
        diaryId: diaryId,
      },
      {
        headers: {
          Authorization: getCookie("token"),
        },
      }
    );
    console.log(response.data);
  } catch (error) {
    console.error(error);
  }
}
