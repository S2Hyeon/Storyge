import styled from "styled-components";

export const BodyContainer = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
`;
export const Text = styled.div`
  font-family: "S-CoreDream-6Bold";
  color: var(--color-headerText);
  font-size: x-large;
  margin-bottom: 40px;
`;

export const SubmitBtn = styled.button`
  width: 200px;
  height: 40px;
  border-radius: 12px;
  border: 0;
  background-color: var(--color-primary);
`;

export const BtnText = styled.div`
  font-family: "S-CoreDream-5Medium";
  color: var(--color-white);
`;
