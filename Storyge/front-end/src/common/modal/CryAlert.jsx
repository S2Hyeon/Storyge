import React from "react";
import Swal from "sweetalert2";
import withReactContent from "sweetalert2-react-content";

const MySwal = withReactContent(Swal);

export default function CryAlert() {
  Swal.fire("우울한 날이 14일 이상 지속되었습니다.", "info");

  return <>{MySwal.fire}</>;
}
