import Api from "lib/customApi";
import { getCookie } from "utils/Cookies";

export async function getUserId() {
  try {
    const response = await Api.get(`/user`, {
      headers: {
        Authorization: getCookie("token"),
      },
    });
    return response.data;
  } catch (error) {
    console.error(error);
  }
}