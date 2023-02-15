import Api from "lib/customApi";
import { getCookie } from "utils/Cookies";

export async function putUser(file, nickName) {
  try {
    console.log("여기서부터는 putUSer");
    console.log("닉네임 : " + nickName);
    console.log(file);
    // file.append("nickname", nickName);

    const blob = new Blob([nickName], {
      type: "application/json",
    });
    file.append("nickname", blob);

    for (const keyValue of file) console.log(keyValue);

    await Api.put("/user", file, {
      headers: {
        Authorization: getCookie("token"),
        // "Context-Type": "multipart/form-data",
      },
    });
  } catch (error) {
    console.error(error);
  }
}
