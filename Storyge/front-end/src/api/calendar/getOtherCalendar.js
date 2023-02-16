import Api from "lib/customApi";
import { getCookie } from "utils/Cookies";

export async function getOtherCalendar(month, userId) {
  try {
    const response = await Api.get(`/daily/${month}/${userId}`, {
      headers: {
        Authorization: getCookie("token"),
      },
    });
    return response.data;
  } catch (error) {
    console.error(error);
  }
}
