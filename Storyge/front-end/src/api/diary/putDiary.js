import Api from "lib/customApi";
import { getCookie } from "utils/Cookies";

export async function putDiary(diary, content, diaryId, scope) {
  try {
    await Api.put(
      "/diary",
      {
        analyzedResult: content[1],
        diaryContent: diary,
        emoticonName: content[0],
        scope: scope,
        diaryId: diaryId,
      },
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
