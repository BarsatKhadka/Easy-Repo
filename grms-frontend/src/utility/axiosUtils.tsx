import {useState} from 'react'
import axios , {AxiosInstance , AxiosError} from "axios";

interface FetchDataTypes{
    url : string;
    method : 'get' | 'post' | 'put' | 'delete';
    data? : Record<string,any>;   // type similar to Map<String,Object> in java. question mark makes this optional
    params?: Record<string , any>;

}

export const useAxios = () =>{

    //states
    const[response,setResponse] = useState(null)
    const[loading,setLoading] = useState(false)
    const[error,setError] = useState("")

//client is axiosInstance type which is a predefined typescript interface including methods like get , post , put , delete
const axiosInstance : AxiosInstance  = axios.create({

    //baseURL coming from springboot
    baseURL : "http://localhost:8080",
    withCredentials: true
    })

    //passing these vars into a curly braces here in js is destructuring an object.
    const fetchData = async({url,method,data = {}, params = {}} : FetchDataTypes) =>{
        setLoading(true)
        try{
            const fetchDataResponse = await axiosInstance({method : method , url: url , data: data , params: params})
            setResponse(fetchDataResponse.data)

        }
        catch(error){

            //lets typescript know the type of error that it is type of axios error. Errors that originate from failing of axios request.
            if(error instanceof AxiosError){
                setError(error.response ? error.response.data : error.message)
            }
            else{
                setError("unknown Error")
            }


        }
        finally{

            setLoading(false)

        }

       

    }
    return {response , loading , error , fetchData}
  




}

