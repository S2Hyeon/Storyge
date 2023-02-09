import axios from "axios";
import Api from "lib/customApi";

export async function getQuote() {
  try {
    // const response = await axios.get("/quote");
    const response = await Api.get("/quote");
    console.log("response: ", response.data);
    return response.data;
  } catch (error) {
    console.error(error);
  }
}
