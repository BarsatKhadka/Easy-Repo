import {useQuery } from '@tanstack/react-query'
import {useAxios} from '../utility/axiosUtils'
import { useEffect } from 'react'
export const DashBoard = () => {

    const {response , loading ,error , fetchData} = useAxios()

    const fetchDataHere = () =>{
        useEffect(()=>{

            fetchData({url : '/easyrepo/user' , method: 'get'})
        }, [])
        
        //
    }

    fetchDataHere()
    

    return(
        <>
        <p>User Details</p>
        <p>username : {response?.["username: "]}</p>

        </>
    )
}