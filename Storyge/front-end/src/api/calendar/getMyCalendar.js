import Api from "lib/customApi";
import { getCookie } from "utils/Cookies";

export async function getMyCalendar(month) {
  try {
    const response = await Api.get(`/daily/${month}`, {
      headers: {
        Authorization: getCookie("token"),
      },
    });
    console.log(response.data);
    return response.data;
  } catch (error) {
    console.error(error);
  }
}
