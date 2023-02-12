import Api from 'lib/customApi'
import { getCookie } from 'utils/Cookies'

export async function putUser(nickName, profileImg) {
  try {
    console.log(nickName)
    console.log(profileImg)

    await Api.put(
      '/user',
      {
        nickname: nickName,
        profile: profileImg,
      },
      {
        headers: {
          Authorization: getCookie('token'),
        },
      },
    )
  } catch (error) {
    console.error(error)
  }
}
