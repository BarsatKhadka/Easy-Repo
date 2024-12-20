import { CollectionsAll } from "./CollectionsAll"
import {useAxios} from "../../utility/axiosUtils"
import {useEffect} from 'react'
//this will contain all the collections.
export const CollectionsMain = () =>{

    const{response,fetchData} = useAxios()

    //i am fetching all the collections in the main component because all collections will be made from the pool of all collections , it being accessible from here makes sense.
    useEffect(()=>{
        fetchData({url: '/easyrepo/collections/all', method: 'get'})
        console.log("success")

    },[]) 

    const data = response?.data ?? []  //empty array if response is null


    return (
        <>
         <button onClick = {()=> {}}>This is a button</button>

         {/* the type this prop must return is defined in the element itself , not here. */}
        <CollectionsAll allCollection = {data}/>

        
        </>
    )
}