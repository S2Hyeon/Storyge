import React, { useEffect, useState } from "react";
import * as S from "./PieChartStyle";
import {
  Chart as ChartJS,
  ArcElement,
  Tooltip,
  Legend,
  // plugins,
} from "chart.js";
import { Doughnut } from "react-chartjs-2";
import Select from "react-select";
import dayjs from "dayjs";
import { type } from "@testing-library/user-event/dist/type";
import { getStatistic } from "api/statistic/getStatistic";

ChartJS.register(ArcElement, Tooltip, Legend);

const options = {
  responsive: true,
  plugins: {
    legend: {
      display: true,
      position: "bottom",
      labels: {
        color: "var(--color-primary)",
        fontFamily: "S-CoreDream-5Medium",
        useBorderRadius: true,
        // borderRadius: 1,
        boxWidth: 15,
        boxHeight: 15,
      },
    },
  },
};

function PieChart(props) {
  const userId = props.userId;
  const [statisticData, setStatisticData] = useState([]);

  const data = {
    labels: ["분노", "놀람", "혐오", "슬픔", "공포", "기쁨", "중립"],
    datasets: [
      {
        label: "",
        data: statisticData,
        backgroundColor: [
          "#FBB4AE", //분노 - 빨강
          "#FED9A6", //놀람 - 주황
          "#DECBE4", //혐오 - 보라
          "#B3CDE3", //슬픔 - 파랑
          "#E5D8BD", //공포 - 갈색
          "#FFFFCC", //기쁨 - 연노랑
          "#CCEBC5", //중립 - 초록
        ],
      },
    ],
  };

  //분류 타입
  const types = [
    { value: "month", label: "월별" },
    { value: "year", label: "연도별" },
  ];
  const [typeValue, setTypeValue] = useState(types[0].value);
  function onChangeType(target) {
    setTypeValue(target.value);
  }

  //연도들
  let years = [];
  for (
    let year = Number(dayjs(new Date()).format("YYYY"));
    year >= 2022;
    year--
  ) {
    years.push({ value: String(year), label: String(year) + "년" });
  }
  const [yearValue, setYearValue] = useState(years[0].value);
  function onChangeYear(target) {
    setYearValue(target.value);
  }

  //월들
  let months = [];
  //올해라면 이번 달까지만 표시
  if (yearValue === String(dayjs(new Date()).format("YYYY"))) {
    for (
      let month = Number(dayjs(new Date()).format("MM"));
      month >= 1;
      month--
    ) {
      let stringMonth = "";
      //한자리수면 문자열에 0붙이기
      if (String(month).length === 1) stringMonth = "0" + String(month);
      else stringMonth = String(month);
      months.push({ value: stringMonth, label: String(month) + "월" });
    }
  }
  //올해가 아니라면 12월부터 다 표시
  else {
    for (let month = 12; month >= 1; month--) {
      let stringMonth = "";
      //한자리수면 문자열에 0붙이기
      if (String(month).length === 1) stringMonth = "0" + String(month);
      else stringMonth = String(month);
      months.push({ value: stringMonth, label: String(month) + "월" });
    }
  }
  const [monthValue, setMonthValue] = useState(months[0].value);
  function onChangeMonth(target) {
    setMonthValue(target.value);
  }

  useEffect(() => {
    async function getAndSetStatistic() {
      if (userId < 0) {
        const response = await getStatistic(typeValue, yearValue, monthValue);
        setStatisticData(response);
      } else {
        const response = await getStatistic(
          typeValue,
          yearValue,
          monthValue,
          userId
        );
        setStatisticData(response);
      }
    }
    getAndSetStatistic();
  }, [typeValue, yearValue, monthValue]);

  return (
    <>
      <S.Title>감정 통계 그래프</S.Title>
      <Doughnut data={data} options={options} />
      <S.SelectBox>
        <S.CustomSelect
          options={types}
          defaultValue={types[0]}
          onChange={onChangeType}
          isSearchable={false}
        />
        <S.CustomSelect
          options={years}
          defaultValue={years[0]}
          onChange={onChangeYear}
          isSearchable={false}
        />
        <S.CustomSelect
          options={months}
          defaultValue={months[0]}
          onChange={onChangeMonth}
          isDisabled={typeValue === "year" ? true : false}
          isSearchable={false}
        />
      </S.SelectBox>
    </>
  );
}

export default PieChart;
