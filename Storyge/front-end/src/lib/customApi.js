import axios from "axios";

const Api = axios.create({
  baseURL: "https://storyge.xyz/api",
  // baseURL: "http://localhost:8080",
  // timeout: 10000,
  // params: {},
});

export default Api;
