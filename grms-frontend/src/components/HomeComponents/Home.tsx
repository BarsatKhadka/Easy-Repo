import {useAxios} from '../../utility/axiosUtils'
import { useUserStore } from '../../store/UserStore';
import { useEffect } from 'react';


import { CollectionsMain } from '../CollectionsComponents/CollectionsMain';
import { HomeAfterAuth } from './HomeAfterAuth';
import { HomeBeforeAuth } from './HomeBeforeAuth';

export const Home = () => {
  const {response, fetchData} = useAxios()
  const {authenticated,setAuthenticated} = useUserStore()

  //when user is authenticated , it directly comes to / (home) , so at that point just fetch from /easyrepo/user (which contains user details) cthe backend.
useEffect(()=>{
    //fetch only if user is not authenticated , this way i can use this context api throughout react.
    if(!authenticated){
    fetchData({url: "/easyrepo/user" , method: "get"})
    }
}, [])

useEffect(()=>{
  if(response?.status == 200){

    setAuthenticated(true)

    //do all this sessionstorage thing after that user is authenticated.
    if(sessionStorage.getItem('authenticated') != 'True'){
      sessionStorage.setItem('authenticated', 'True')

    }
    //csrf token refreshes with every refresh hence facilitating that.
    if(sessionStorage.getItem('csrf') == null || sessionStorage.getItem('csrf') != response?.data["csrfController"]["token"]){
      sessionStorage.setItem('csrf', response?.data["csrfController"]["token"])
      console.log(sessionStorage.getItem('csrf'))
    }
    
  } 
}, [response])

console.log(response)



  return (
    <div>
      {/* This is the home to be displayed before authentication */}
      {!authenticated && <HomeBeforeAuth/>}
      <h1>username: {response?.data["username: "]} </h1>
      <h1>csrf: {response?.data["csrfController"]["token"]}</h1>
      <div>
        {/* Show collections only if authenticated */}
        {authenticated && <><CollectionsMain/> <HomeAfterAuth/></>}
      </div>
      
      
      

    </div>
  );
}