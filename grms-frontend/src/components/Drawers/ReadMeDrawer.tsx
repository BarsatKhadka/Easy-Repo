import { useEffect } from "react";
import {
  Drawer,
  DrawerContent,
  DrawerHeader,
  DrawerBody,
} from "@nextui-org/react";
import { useUserStore } from "../../store/UserStore";
import { useAxios } from "../../utility/axiosUtils";
import ReactMarkdown from 'react-markdown'



export const ReadMeDrawer = () =>{

    const{response, setResponse, fetchData} = useAxios()

    

    //this lets me know if to open the drawer or not 
    const {readMeDrawerOpen , setreadMeDrawerOpen} = useUserStore()
    const{repoName , setRepoName} = useUserStore()

    useEffect(() =>{
        fetchData({url: "/easyrepo/insights/repo/getReadMe/" + repoName , method: 'get'})

    }, [repoName])

    const handleOpenChange = (isOpen: boolean) => {
        if (!isOpen) {
          setreadMeDrawerOpen(false);
          setResponse(null)    
        }
      };



    return(
        <>
              <Drawer isOpen={readMeDrawerOpen} placement="left" size="4xl" onOpenChange={handleOpenChange}>
      <DrawerContent>
        <DrawerHeader className="p-6 border-b border-gray-200">
          <h2 className="text-xl font-semibold">Read Me for {repoName}</h2>
        </DrawerHeader>
        <DrawerBody className="p-6 overflow-y-auto">
          <div className="prose prose-sm sm:prose lg:prose-lg xl:prose-xl max-w-none">
            <ReactMarkdown>{response?.data}</ReactMarkdown>
          </div>
        </DrawerBody>
      </DrawerContent>
    </Drawer>
        </>
    )
}