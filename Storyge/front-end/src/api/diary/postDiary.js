import Api from "lib/customApi";
import { getCookie } from "utils/Cookies";

export async function postDiary(diary, content) {
  try {
    const response = await Api.post(
      "/diary",
      {
        analizedResult: content[1],
        diaryContent: diary,
        // diaryId: 0,
        emoticonName: content[0],
        scope: 0,
        // updateCnt: 0,
        // userId: 0,
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
