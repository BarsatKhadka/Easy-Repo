import {useAxios} from '../utility/axiosUtils'
import {useState, useEffect } from 'react';

export const Home = () => {
  const {response, fetchData} = useAxios()
  const [authenticated , setAuthenticated ]= useState<boolean>(false)

  //when user is authenticated , it directly comes to / (home) , so at that point just fetch from /easyrepo/user (which contains user details) cthe backend.
  useEffect(()=>{
    //fetch only if user is not authenticated , this way i can use this context api throughout react.
    if(!authenticated){
    fetchData({url: "/easyrepo/user" , method: "get"})
    }

}, [authenticated])

useEffect(()=>{
  if(response?.status == 200){
    setAuthenticated(true)
  } 
  

}, [response])


  return (
    <div>
      <h1>This is home page</h1>
      <h1>username: {response?.data["username: "]} </h1>
      
      
      

    </div>
  );
}
