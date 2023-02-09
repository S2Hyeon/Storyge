import axios from "axios";

const Api = axios.create({
  baseURL: process.env.BASE_URL,
  timeout: 10000,
  params: {},
});

export default Api;
