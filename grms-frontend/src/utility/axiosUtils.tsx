import {useEffect, useState} from 'react'
import axios , {AxiosInstance , AxiosError} from "axios";

export interface FetchDataTypes{
    url : string;
    method : 'get' | 'post' | 'put' | 'delete';
    data? : Record<string,any>;   // type similar to Map<String,Object> in java. question mark makes this optional
    params?: Record<string , any>;


}

export const useAxios = () =>{

    //states
    const[response,setResponse] = useState(null)
    const[loading,setLoading] = useState(false)
    const[error,setError] = useState(null)

//client is axiosInstance type which is a predefined typescript interface including methods like get , post , put , delete
const axiosInstance : AxiosInstance  = axios.create({

    //baseURL coming from springboot
    baseURL : "http://localhost:8080",
    withCredentials: true
    })


    axiosInstance.interceptors.request.use((config) => {
        return config
    }, 
    (error) =>{
        return Promise.reject(error)
    }
)

    axiosInstance.interceptors.response.use((response) =>{
        return response
    },
    (error) =>{
    return Promise.reject(error)
    })

    let controller = new AbortController()
    useEffect(() =>{
        return ()=> controller?.abort()

    }, [])


    //passing these vars into a curly braces here in js is destructuring an object.
    const fetchData = async({url,method,data = {}, params = {}} : FetchDataTypes) =>{

        //abort if there are any controller previously , a detailed example is in my 'Concepts i learned thing'
        controller.abort()
        controller = new AbortController()

        setLoading(true)
        try{
            const fetchDataResponse = await axiosInstance({method : method , url: url , data: data , params: params , signal: controller.signal})
            setResponse(fetchDataResponse.data)

        }
        catch(error){

            //lets typescript know the type of error that it is type of axios error. Errors that originate from failing of axios request.
            if(error instanceof AxiosError){
                setError(error.response ? error.response.data : error.message)
            }
            else{
                setError(null)
            }


        }
        finally{

            setLoading(false)

        }

       

    }
    return {response , loading , error , fetchData}
  




}

