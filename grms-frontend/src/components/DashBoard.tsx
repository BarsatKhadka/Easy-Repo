import {useQuery } from '@tanstack/react-query'
import {useAxios} from '../utility/axiosUtils'
import { useContext, useEffect } from 'react'
import { UserLoginContext, UserLoginContextType } from '../ContextAPI/UserLoginContext'



export const DashBoard = () => {

    // const {response, loading  , error , fetchData} = useAxios()
    // const {userDetails , setUserDetails , userDetailsError , setUserDetailsError} = useContext(UserLoginContext) as UserLoginContextType

    return(
        <>
        <p>User Details</p>
        <p>username : </p>
        

        </>
    )
}