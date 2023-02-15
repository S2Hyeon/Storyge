import Api from "lib/customApi";
import { getCookie } from "utils/Cookies";

export async function putMakeReadAlarm(notificationId) {
  try {
    const response = await Api.put(
      "/notification",
      {
        notificationId: notificationId,
        isRead: 1,
      },
      {
        headers: {
          Authorization: getCookie("token"),
        },
      }
    );
    console.log(response.data);
  } catch (error) {
    console.error(error);
  }
}
