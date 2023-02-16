import Api from "lib/customApi";
import { getCookie } from "utils/Cookies";

export async function deleteReview(reviewId) {
  try {
    await Api.delete(`/review/${reviewId}`, {
      headers: {
        Authorization: getCookie("token"),
      },
    });
  } catch (error) {
    console.error(error);
  }
}
