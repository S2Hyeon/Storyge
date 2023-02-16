import Api from "lib/customApi";
import { getCookie } from "utils/Cookies";

export async function postDiary(diary, content, scope) {
  try {
    await Api.post(
      "/diary",
      {
        analyzedResult: content[1],
        diaryContent: diary,
        emoticonName: content[0],
        scope: scope,
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
