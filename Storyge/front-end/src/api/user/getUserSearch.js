import Api from "lib/customApi";
import { getCookie } from "utils/Cookies";

export async function getUserSearch(keyword) {
  if (keyword !== "") {
    try {
      const response = await Api.get(`/user/search/${keyword}`, {
        headers: {
          Authorization: getCookie("token"),
        },
      });
      return response.data;
    } catch (error) {
      console.error(error);
    }
  }
}
