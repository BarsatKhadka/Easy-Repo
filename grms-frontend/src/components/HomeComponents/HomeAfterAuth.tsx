import { useEffect } from "react"
import { useAxios } from "../../utility/axiosUtils"

import { NavbarAfterAuth } from "../Navbar/Navbar"
import {HeroSectionAfterAuth} from "../HeroSection/HeroSectionAfterAuth"


export const HomeAfterAuth = () =>{
    const {response, fetchData } = useAxios()

    useEffect(() =>{
        fetchData({url: "/easyrepo/user/getUserDetails" , method: 'get'})
    }, [])
    return(
        <>
      <div className='h-screen flex flex-col '>

        <NavbarAfterAuth data = {response?.data} />
        <div className="grid grid-cols-4 grid-rows-6 gap-8 ">
  <div key="item-1" className="col-start-2 row-start-1 col-span-2 row-span-1 mt-8">
    <HeroSectionAfterAuth username={response?.data.username}/>
  </div>
  <div key="item-2" className="col-start-1 row-start-3 col-span-1 row-span-3">
    2
  </div>
  <div key="item-3" className="col-start-2 row-start-2 col-span-2 row-span-4">
    3
  </div>
  <div key="item-5" className="col-start-4 row-start-1 col-span-1 row-span-2 mt-8">
    5
  </div>
  <div key="item-6" className="col-start-1 row-start-1 col-span-1 row-span-2 mt-8">
    6
  </div>
  <div key="item-7" className="col-start-4 row-start-3 col-span-1 row-span-3">
    7
  </div>
  <div key="item-8" className="col-start-1 row-start-6 col-span-4 row-span-1">
    8
  </div>
</div>
  
  </div>
  

        </>
    )
}