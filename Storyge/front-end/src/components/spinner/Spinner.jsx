import React from "react";
import { BeatLoader } from "react-spinners";

const override = {
  display: "flex",
  justifyContent: "center",
  alignItems: "center",
  margin: "0 auto",
  borderColor: "var(--color-primary)",
  textAlign: "center",
};

const Loading = ({ loading }) => {
  return (
    <div>
      <BeatLoader
        color="var(--color-primary)"
        loading={loading}
        cssOverride={override}
        size={20}
      />
    </div>
  );
};

export default Loading;
