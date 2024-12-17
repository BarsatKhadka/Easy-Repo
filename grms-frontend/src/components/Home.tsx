import {useAxios} from '../utility/axiosUtils'
import {useEffect } from 'react';

export const Home = () => {
  const {response, fetchData} = useAxios()

  //when user is authenticated , it directly comes to / (home) , so at that point just fetch from /easyrepo/user (which contains user details) cthe backend.
  useEffect(()=>{
    fetchData({url: "/easyrepo/user" , method: "get"})
    console.log("Let's see when it runs")
}, [])


useEffect (() =>{
  if(response){
    console.log(response)
  }
}, [response])


  return (
    <div>
      <h1>This is home page</h1>
      <h1>username: {response?.data["username: "]}</h1>
      
      

    </div>
  );
}
