import axios from "axios";
import Api from "lib/customApi";
import { getCookie } from "utils/Cookies";

export async function putMakeReadAlarm(notificationId) {
  try {
    await Api.put(
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
  } catch (error) {
    console.error(error);
  }
}
