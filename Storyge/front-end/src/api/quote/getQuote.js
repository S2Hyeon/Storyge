import axios from "axios";

export async function getQuote() {
  try {
    const response = await axios.get("http://storyge.xyz:8080/quote");
    console.log("response: ", response.data);
    return response.data;
  } catch (error) {
    console.error(error);
  }
}
