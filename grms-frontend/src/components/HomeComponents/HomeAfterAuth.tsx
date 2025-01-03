import { useEffect } from "react"
import { useAxios } from "../../utility/axiosUtils"

import { NavbarAfterAuth } from "../Navbar/Navbar"
import {HeroSectionAfterAuth} from "../HeroSection/HeroSectionAfterAuth"
import { CollectionsMain } from "../CollectionsComponents/CollectionsMain"
import { Divider } from "@nextui-org/react"
import { RepoDisplayMain } from "../RepoDisplayComponents/RepoDisplayMain"


export const HomeAfterAuth = () =>{
    const {response, fetchData } = useAxios()

    useEffect(() =>{
        fetchData({url: "/easyrepo/user/getUserDetails" , method: 'get'})
    }, [])


    return(
        <>
      <div className='h-screen flex flex-col '>
      <div className="grid grid-cols-4 grid-rows-7 gap-5 flex-grow overflow-auto">
  <div key="item-1" className="col-start-1 row-start-1 col-span-4 row-span-1 ">
  <NavbarAfterAuth data = {response?.data} />
  <Divider />   
  </div>
  
  <div key="item-2" className="col-start-1 row-start-2 col-span-1 row-span-6">
    <CollectionsMain/>
   
  </div>
  <div key="item-3" className="col-start-2 row-start-2 col-span-2 row-span-1">
  <HeroSectionAfterAuth username={response?.data.username}/>
  </div>
  <div key="item-4" className="col-start-4 row-start-2 col-span-1 row-span-3">
    4
  </div>
  <div key="item-5" className="col-start-4 row-start-5 col-span-1 row-span-3">
    5
  </div>
  <div key="item-6" className="col-start-2 row-start-3 col-span-2 row-span-5">
    <RepoDisplayMain/>
  </div>
</div>

        
      
</div>
  
  
  

        </>
    )
}