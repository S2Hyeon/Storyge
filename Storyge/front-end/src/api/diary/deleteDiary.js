import axios from "axios";

export async function postDiary({ analysis, content, emotion, userId }) {
  axios({
    method: "delete",
    url: "http://storyge.xyz:8080/diary",
    params: {
      "analizedResult": analysis,
      "createdAt": "2023-02-10",
      "diaryContent": content,
      "diaryId": 0,
      "emoticonName": emotion,
      "scope": 0,
      "updateCnt":0,
      "userId": userId,
    }
  })
    .then((res) => {
      console.log(res.data);
    })
    .catch((err) => {
      console.log(err);
    });
}
