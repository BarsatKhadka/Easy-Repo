import { useEffect } from "react"
import { useAxios } from "../../utility/axiosUtils"

import { NavbarAfterAuth } from "../Navbar/Navbar"
import { HeroSectionAfterAuth } from "../HeroSection/HeroSectionAfterAuth"
import { CollectionsMain } from "../CollectionsComponents/CollectionsMain"
import { Divider } from "@nextui-org/react"
import { RepoDisplayMain } from "../RepoDisplayComponents/RepoDisplayMain"


export const HomeAfterAuth = () => {
    const { response, fetchData } = useAxios()

    useEffect(() => {
        fetchData({ url: "/easyrepo/user/getUserDetails", method: 'get' })
    }, [])

    return (
        <>
     
     <div className='h-screen flex flex-col'>
  <div className="lg:grid lg:grid-cols-4 lg:grid-rows-7 gap-5 md:grid-cols-2 sm:grid-cols-1 flex-grow overflow-auto">
    <div key="item-1" className="col-start-1 row-start-1 col-span-4 row-span-1">
      <NavbarAfterAuth data={response?.data} />
      <Divider />
    </div>

    <div key="item-2" className="lg:col-start-1 lg:row-start-2 lg:col-span-1 lg:row-span-6 md:col-span-2 sm:col-span-1 border border-white ml-3">
      <CollectionsMain />
    </div>
    <div key="item-3" className="lg:col-start-2 lg:row-start-2 lg:col-span-2 lg:row-span-1 md:col-span-2 sm:col-span-1">
      <HeroSectionAfterAuth username={response?.data.username} />
    </div>
    <div key="item-4" className="lg:col-start-4 lg:row-start-2 lg:col-span-1 lg:row-span-3 md:col-span-2 sm:col-span-1 border border-white mr-3">
      4
    </div>
    <div key="item-5" className="lg:col-start-4 lg:row-start-5 lg:col-span-1 lg:row-span-3 md:col-span-2 sm:col-span-1 border border-white mr-3">
      5
    </div>
    <div key="item-6" className="lg:col-start-2 lg:row-start-3 lg:col-span-2 lg:row-span-5 md:col-span-2 sm:col-span-1 border border-white">
      <RepoDisplayMain />
    </div>
  </div>
</div>

        </>
    )
}
