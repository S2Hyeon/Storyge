import Api from "lib/customApi";
import { getCookie } from "utils/Cookies";

export async function deleteDiary(id) {
  try {
    console.log(">>>>>>>>>", id);
    const response = await Api.delete(
      "/diary",
      { headers: { Authorization: getCookie("token") } },
      { data: { diaryId: id } }
    );
    console.log(response.data);
  } catch (error) {
    console.error(error);
  }
}
