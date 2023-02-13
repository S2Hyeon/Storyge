import styled from "styled-components";

export const Box = styled.div`
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    min-height: 100vh;
    background-color: var(--color-yellow-light);
`;

export const Logo = styled.img`
  width: 50%;
  margin-bottom: 30% ;
`;

export const TextM = styled.div`
    color: var(--color-brown-dark);
    font-family: "S-CoreDream-5Medium";
    font-size:2vw;
    margin-bottom: 2% ;
`;

export const TextS = styled.div`
    color: var(--color-brown-dark);
    font-family: "S-CoreDream-5Medium";
    font-size:2vw;
`;