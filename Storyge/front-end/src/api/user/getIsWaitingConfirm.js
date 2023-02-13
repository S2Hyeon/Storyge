import Api from "lib/customApi";
import { getCookie } from "utils/Cookies";

export async function getIsWaitingConfirm(otherUserId) {
  //   try {
  //     const response = await Api.get(`/user/${otherUserId}`, {
  //       headers: {
  //         Authorization: getCookie("token"),
  //       },
  //     });
  //     console.log(response.data);
  //     return response.data;
  //   } catch (error) {
  //     console.error(error);
  //   }
  console.log("getIsWaitingConfirm API 나오면 붙이기");
}
