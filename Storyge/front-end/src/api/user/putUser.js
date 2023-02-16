import axios from 'axios'
import Api from 'lib/customApi'
import { getCookie } from 'utils/Cookies'
import Api from "lib/customApi";
import { getCookie } from "utils/Cookies";

export async function putUser(file, nickName) {
  try {
    const formData = new FormData()
    const blob = new Blob([nickName], { type: 'application/json' })
    formData.append('multipartFile', file)
    formData.append('nickname', blob)
    const response = await axios.put('http://localhost:8080/user', formData, {
    const formData = new FormData();
    const blob = new Blob([nickName], { type: "application/json" });
    formData.append("multipartFile", file);
    formData.append("nickname", blob);
    await Api.put("/user", formData, {
      headers: {
        Authorization: getCookie('token'),
        'Content-Type': 'multipart/form-data',
      },
    })
  } catch (error) {
    console.error(error)
  }
}
