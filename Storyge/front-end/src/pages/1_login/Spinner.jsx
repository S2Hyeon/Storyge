import React from "react";
import { BeatLoader } from "react-spinners";

const override = {
  display: "flex",
  justifyContent: "center",
  alignItems: "center",
  margin: "0 auto",
  borderColor: "#ACCEBC",
  textAlign: "center",
};

const Loading = ({ loading }) => {
  return (
    <div>
      <BeatLoader
        color="#ACCEBC"
        loading={loading}
        cssOverride={override}
        size={30}
      />
    </div>
  );
};

export default Loading;
