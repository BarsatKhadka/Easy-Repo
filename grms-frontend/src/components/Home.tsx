import {useAxios} from '../utility/axiosUtils'
import { useContext , useEffect } from 'react';
import { UserLoginContext , UserLoginContextType} from '../ContextAPI/UserLoginContext';


export const Home = () => {
  //  const {userDetails , setUserDetails , userDetailsError , setUserDetailsError} = useContext(UserLoginContext) as UserLoginContextType
  useEffect(()=>{
    console.log("Let's see when it runs")
}, [])

  return (
    <div>
      <h1>This is home page</h1>
      <h1>username: </h1>
      

    </div>
  );
}
