import axios from "axios";

const Api = axios.create({
  baseURL: "https://storyge.xyz/api",
  // timeout: 10000,
  params: {},
});

export default Api;
