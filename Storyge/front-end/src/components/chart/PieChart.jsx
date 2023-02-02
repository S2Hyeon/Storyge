import React from "react";
import {
  Chart as ChartJS,
  ArcElement,
  Tooltip,
  Legend,
  plugins,
} from "chart.js";
import { Doughnut } from "react-chartjs-2";

ChartJS.register(ArcElement, Tooltip, Legend);

const data = {
  labels: ["분노", "놀람", "혐오", "슬픔", "공포", "기쁨", "중립"],
  datasets: [
    {
      label: "# of Votes",
      data: [12, 19, 3, 5, 2, 3, 12],
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

function PieChart() {
  return <Doughnut data={data} options={options} />;
}

export default PieChart;
