import { Doughnut } from "react-chartjs-2";
import { Chart as ChartJS, ArcElement, Tooltip, Legend } from "chart.js";
import * as S from "./style";
const data = {
  // labels: [
  //   "red",
  //   "orange",
  //   "yellow",
  //   "green",
  //   "skyblue",
  //   "indigo",
  //   "purple",
  //   "brown",
  // ],
  datasets: [
    {
      // label: "# of Level",
      data: [12, 19, 3, 5, 2, 3, 10, 8],
      backgroundColor: [
        "#FF4E36",
        "#FFA62E",
        "#FFCF1B",
        "#C0FA87",
        "#6EE2F5",
        "#3C5DD3",
        "#8533FF",
        "#695F54",
      ],
      borderColor: [
        "#FF4E36",
        "#FFA62E",
        "#FFCF1B",
        "#C0FA87",
        "#6EE2F5",
        "#3C5DD3",
        "#8533FF",
        "#695F54",
      ],
      borderWidth: 1,
    },
  ],
};

const options = {
  responsive: true,
  plugins: {
    legend: {
      display: false,
      position: "right",
      labels: {
        color: "#ffffff",
      },
    },
  },
};

const labelValues = [
  { color: "#FF4E36", text: "빨강색" },
  { color: "#FFA62E", text: "주황색" },
  { color: "#FFCF1B", text: "노랑색" },
  { color: "#C0FA87", text: "초록색" },
  { color: "#6EE2F5", text: "하늘색" },
  { color: "#3C5DD3", text: "남색" },
  { color: "#8533FF", text: "보라색" },
  { color: "#695F54", text: "갈색" },
];

const StackedGraph = () => {
  return (
    <S.GraphWrapper>
      <Doughnut data={data} options={options} />
      <S.GraphLabels>
        {labelValues &&
          labelValues.length > 0 &&
          labelValues.map((item) => (
            <S.Label key={item.color}>
              <S.LabelColor color={item.color} />
              <S.LabelText>{item.text}</S.LabelText>
            </S.Label>
          ))}
      </S.GraphLabels>
    </S.GraphWrapper>
  );
};
export default StackedGraph;

ChartJS.register(ArcElement, Tooltip, Legend);
