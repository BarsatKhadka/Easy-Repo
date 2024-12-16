import axios , {AxiosInstance} from "axios";

//client is axiosInstance type which is a predefined typescript interface including methods like get , post , put , delete
const client : AxiosInstance  = axios.create({

    //baseURL coming from springboot
    baseURL : "http://localhost:8080"
    

    })




export default client;

