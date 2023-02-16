import React from "react";

import Swal from "sweetalert2";
import withReactContent from "sweetalert2-react-content";

const MySwal = withReactContent(Swal);

export default function Modal(setIsGloomy) {
  Swal.fire("", "슬픈 감정이 14일 이상 지속되었어요.", "info").then((res) => {
    if (res.isConfirmed) {
      setIsGloomy(false);
    }
  });

  return <>{MySwal.fire}</>;
}
