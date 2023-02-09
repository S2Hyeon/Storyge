import axios from "axios";

export async function postDiary() {
  axios({
    method: "POST",
    url: "http://storyge.xyz:8080/diary",
  })
    .then((res) => {
      console.log(res.data);
    })
    .catch((err) => {
      console.log(err);
    });
}
