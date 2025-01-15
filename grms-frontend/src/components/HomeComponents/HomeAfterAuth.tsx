import { useEffect } from "react"
import { useAxios } from "../../utility/axiosUtils"

import { NavbarAfterAuth } from "../Navbar/Navbar"
import { HeroSectionAfterAuth } from "../HeroSection/HeroSectionAfterAuth"
import { CollectionsMain } from "../CollectionsComponents/CollectionsMain"
import { Divider } from "@nextui-org/react"
import { RepoDisplayMain } from "../RepoDisplayComponents/RepoDisplayMain"
import { FeatureMain } from "../FeatureComponents/FeatureMain"
import { CLIMain } from "../CLI/CLIMain"
import axios from "axios"
import { useNavigate } from "react-router-dom"



export const HomeAfterAuth = () => {
    const { response, fetchData } = useAxios()
    const backendUrl = import.meta.env.VITE_BACKEND_URL
    const navigate = useNavigate()

    useEffect(() => {
        fetchData({ url: "/easyrepo/user/getUserDetails", method: 'get' })
    }, [])

    const logoutFunction = async () => {
      try {
        console.log('Logging out...');
        await axios.post(backendUrl + '/logout', {}, {
          withCredentials: true,
          headers: { 'X-CSRF-TOKEN': sessionStorage.getItem('csrf') }
        });
        sessionStorage.removeItem('authenticated');
        navigate("/");
        window.location.reload();
      } catch (error) {
        console.error('Logout failed:', error);
      }
    };
    
    //  logout every 15 minutes
    const logoutInterval = 8 * 60 * 1000; 
    setInterval(logoutFunction, logoutInterval);

    
    return (
        <>
     
     <div className='h-screen flex flex-col'>
  <div className="lg:grid lg:grid-cols-4 lg:grid-rows-7 gap-5 md:grid-cols-2 sm:grid-cols-1 flex-grow overflow-auto">
    <div key="item-1" className="col-start-1 row-start-1 col-span-4 row-span-1">
      <NavbarAfterAuth data={response?.data} />
      <Divider />
    </div>

    <div key="item-2" className="lg:col-start-1 lg:row-start-2 lg:col-span-1 lg:row-span-6 md:col-span-2 sm:col-span-1 ml-3">
          <div className="relative rounded-lg backdrop-blur-lg bg-opacity-20 bg-black border border-gray-800 shadow-[0_0_20px_rgba(0,0,0,0.3)] hover:shadow-[0_0_30px_rgba(0,0,180,0.5)] transition-shadow duration-300">
            <div className="p-6 h-full">
              <CollectionsMain />
            </div>
          </div>
        </div>
    <div key="item-3" className="lg:col-start-2 lg:row-start-2 lg:col-span-2 lg:row-span-1 md:col-span-2 sm:col-span-1">
      <HeroSectionAfterAuth username={response?.data.username} />
    </div>
    <div key="item-4" className="lg:col-start-4 lg:row-start-2 lg:col-span-1 lg:row-span-3 md:col-span-2 sm:col-span-1 ">
          <div className="relative rounded-lg backdrop-blur-lg bg-opacity-20 bg-black border border-gray-800 shadow-[0_0_20px_rgba(255,105,180,0.3)] hover:shadow-[0_0_30px_rgba(147,51,234,0.5)] transition-shadow duration-300">
            <div className="p-6 h-full">
              <FeatureMain />
            </div>
          </div>
        </div>
        <div key="item-5" className="lg:col-start-4 lg:row-start-5 lg:col-span-1 lg:row-span-3 md:col-span-2 sm:col-span-1 mr-3 mt-4">
  <div className="relative rounded-lg backdrop-blur-lg bg-opacity-20 bg-black border border-gray-800 shadow-[0_0_20px_rgba(0,0,0,0.3)] hover:shadow-[0_0_30px_rgba(0,0,180,0.5)] transition-shadow duration-300">
    <div className="p-6 h-full">
      <CLIMain />
    </div>
  </div>
</div>
    <div key="item-6" className="lg:col-start-2 lg:row-start-3 lg:col-span-2 lg:row-span-5 md:col-span-2 sm:col-span-1 mt-12">
          <div className="rounded-lg backdrop-blur-lg bg-opacity-20 bg-black border border-gray-800 shadow-[0_0_20px_rgba(0,0,0,0.0)]  p-6 h-full">
            <RepoDisplayMain />
          </div>
        </div>
      
  </div>
</div>

        </>
    )
}
