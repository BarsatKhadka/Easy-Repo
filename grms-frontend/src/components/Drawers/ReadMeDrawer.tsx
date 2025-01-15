import React, { useEffect } from "react";
import {
  Drawer,
  DrawerContent,
  DrawerHeader,
  DrawerBody,
  DrawerFooter,
  Button,
  useDisclosure,
} from "@nextui-org/react";
import { useUserStore } from "../../store/UserStore";
import { useAxios } from "../../utility/axiosUtils";
import {Card, CardHeader, CardBody, Image } from "@nextui-org/react";
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
              <div className="flex flex-wrap gap-3">
  
      </div>
      <Drawer isOpen={readMeDrawerOpen} placement={"left"} size={"4xl"}onOpenChange={handleOpenChange} >
        <DrawerContent>
          {(onClose) => (
            <>
            <DrawerHeader className="flex flex-col gap-1">Read Me for {repoName}</DrawerHeader>
            <DrawerBody>
             <>
                 <p>
                    <ReactMarkdown>{response?.data}</ReactMarkdown>
                 </p>    
                
               </>
          
           </DrawerBody>

<DrawerFooter>
                 <Button color="danger" variant="light" onPress={onClose}>
                   Close
                 </Button>
                 <Button color="primary" onPress={onClose}>
                   Action
                 </Button>
               </DrawerFooter>
          
           
            </>
          )}
        </DrawerContent>
      </Drawer>
        </>
    )
}