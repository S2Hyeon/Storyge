import Api from "lib/customApi";
import { getCookie } from "utils/Cookies";

export async function getRecentDiary() {
  try {
    const response = await Api.get("/recent", {
      headers: {
        Authorization: getCookie("token"),
      },
    });
    console.log("최근 업데이트 목록: ", response.data);
    return response.data;
  } catch (error) {
    console.error(error);
  }
}
