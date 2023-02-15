import Api from "lib/customApi";
import { getCookie } from "utils/Cookies";

export async function deleteDiary(id) {
  try {
    await Api.delete(`/diary/${id}`, {
      headers: { Authorization: getCookie("token") },
    });
  } catch (error) {
    console.error(error);
  }
}
