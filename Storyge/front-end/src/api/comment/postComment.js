import Api from "lib/customApi";
import { getCookie } from "utils/Cookies";

export async function postComment(diaryId, reviewContent) {
  try {
    await Api.post(
      "/review",
      {
        diaryId: diaryId,
        reviewContent: reviewContent,
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
