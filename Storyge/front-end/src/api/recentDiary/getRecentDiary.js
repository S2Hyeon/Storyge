import Api from "lib/customApi";
import { getCookie } from "utils/Cookies";

export async function getRecentDiary() {
  try {
    const response = await Api.get("/recent", {
      headers: {
        Authorization: getCookie("token"),
      },
    });
    return response.data;
  } catch (error) {
    console.error(error);
  }
}
