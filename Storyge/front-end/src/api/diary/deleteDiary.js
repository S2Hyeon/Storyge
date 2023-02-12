import Api from "lib/customApi";
import { getCookie } from "utils/Cookies";

export async function deleteDiary(id) {
  try {
    console.log(">>>>>>>>>", id);
    const response = await Api.delete(`/diary/${id}`, {
      headers: { Authorization: getCookie("token") },
    });
    console.log(response.data);
  } catch (error) {
    console.error(error);
  }
}
