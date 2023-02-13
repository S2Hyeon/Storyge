import Api from "lib/customApi";
import { getCookie } from "utils/Cookies";

export async function getStatistic(typeValue, yearValue, monthValue, otherId) {
  let getData = [];

  //다른 사용자 통계 조회
  if (otherId != null) {
    //연도별 조회
    if (typeValue === "year") {
      const response = await Api.get(
        `/diary/statistic/${typeValue}/${yearValue}-01-01/${otherId}`,
        {
          headers: {
            Authorization: getCookie("token"),
          },
        }
      );
      console.log(response.data);
      getData = response.data;
    }
    //월별 조회
    else {
      const response = await Api.get(
        `/diary/statistic/${typeValue}/${yearValue}-${monthValue}-01/${otherId}`,
        {
          headers: {
            Authorization: getCookie("token"),
          },
        }
      );
      console.log(response.data);
      getData = response.data;
    }
  }
  //내 통계 조회
  else {
    //연도별 조회
    if (typeValue === "year") {
      const response = await Api.get(
        `/diary/statistic/${typeValue}/${yearValue}-01-01`,
        {
          headers: {
            Authorization: getCookie("token"),
          },
        }
      );
      console.log(response.data);
      getData = response.data;
    }
    //월별 조회
    else {
      const response = await Api.get(
        `/diary/statistic/${typeValue}/${yearValue}-${monthValue}-01`,
        {
          headers: {
            Authorization: getCookie("token"),
          },
        }
      );
      console.log(response.data);
      getData = response.data;
    }
  }

  //데이터 가공
  let returnData = [];
  const labels = [
    "angry",
    "surprised",
    "aversion",
    "sad",
    "scared",
    "happy",
    "soso",
  ];

  labels.map((label) => {
    let emotion = getData.find((element) => element.emoticonName === label);
    if (emotion != null) returnData.push(emotion.emoticonAmount);
    else returnData.push(0);
  });
  console.log(returnData);
  return returnData;
}
