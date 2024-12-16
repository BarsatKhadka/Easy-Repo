import {useQuery } from '@tanstack/react-query'
import client from '../utility/axiosUtils'
export const DashBoard = () => {

    const fetchData = async() =>{
        const data = await client.get("/easyrepo/user" , {withCredentials: true})
        return data.data
    }

    const {data}  = useQuery({
        queryKey: ["test"],
        queryFn: () => fetchData(),
       })
    

    return(
        <>
        <p>User Details</p>
        <p>username : {data?.["username: "]}</p>

        </>
    )
}