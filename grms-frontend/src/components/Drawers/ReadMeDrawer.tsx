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



export const ReadMeDrawer = () =>{

    // const{response, fetchData} = useAxios()


    //this lets me know if to open the drawer or not 
    const {readMeDrawerOpen , setreadMeDrawerOpen} = useUserStore()
    const{repoName , setRepoName} = useUserStore()

    const handleOpenChange = (isOpen: boolean) => {
        if (!isOpen) {
          setreadMeDrawerOpen(false);
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
                   
                         <Card className="py-4 mt-4">
                         <CardHeader className="pb-0 pt-2 px-4 flex-col items-start">
                           <p className="text-tiny uppercase font-bold"></p>
                           <p>Gello </p>
                         </CardHeader>
                         <CardBody className="overflow-visible py-2">
              
                         </CardBody>
                       </Card>
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