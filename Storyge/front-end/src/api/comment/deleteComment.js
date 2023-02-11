import Api from "lib/customApi";
import { getCookie } from "utils/Cookies";

export async function deleteReview(reviewId) {
  try {
    const response = await Api.delete(`/review/${reviewId}`, {
      headers: {
        Authorization: getCookie("token"),
      },
    });
  } catch (error) {
    console.error(error);
  }
}
