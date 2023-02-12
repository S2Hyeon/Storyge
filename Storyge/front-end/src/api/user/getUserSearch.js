import Api from 'lib/customApi'
import { getCookie } from 'utils/Cookies'

export async function getUserSearch(keyword) {
    try {
    console.log('Get : ' + keyword)
    const response = await Api.get(
      `/user/search`,
      {
        nickname: keyword,
      },
      {
        headers: {
          Authorization: getCookie('token'),
        },
      },
    )
    console.log('Get2 : ' + keyword)
    return response.data
  } catch (error) {
    console.error(error)
  }
}
