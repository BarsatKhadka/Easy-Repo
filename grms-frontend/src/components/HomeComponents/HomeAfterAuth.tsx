import { useEffect } from "react"
import { NavbarAfterAuth } from "../Navbar/Navbar"
import { useAxios } from "../../utility/axiosUtils"

export const HomeAfterAuth = () =>{
    const {response, fetchData } = useAxios()
    useEffect(() =>{
        fetchData({url: "/easyrepo/user/getUserDetails" , method: 'get'})
    }, [])
    return(
        <>
        <NavbarAfterAuth data = {response?.data} />
        

    

        </>
    )
}