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



export const GetLinesOfCodeDrawer = () =>{

    const{response, fetchData} = useAxios()


    //this lets me know if to open the drawer or not 
    const {locDrawerOpen , setLocDrawerOpen} = useUserStore()
    const{repoName , setRepoName} = useUserStore()

    useEffect(()=> {
        fetchData({url: "/easyrepo/insights/repo/" + repoName , method:'get'})

    },[repoName])


  

  


    const {isOpen, onOpen, onOpenChange} = useDisclosure();

    const [placement, setPlacement] = React.useState("left");

  
    const handleOpen = (placement: "left") => {
      setPlacement(placement);
      onOpen();
    };

    const handleOpenChange = (isOpen: boolean) => {
        if (!isOpen) {
          setLocDrawerOpen(false);
        }
      };
    

    return(
        <>
              <div className="flex flex-wrap gap-3">
  
      </div>
      <Drawer isOpen={locDrawerOpen} placement={"left"} size={"4xl"}onOpenChange={handleOpenChange} >
        <DrawerContent>
          {(onClose) => (
            <>
            <DrawerHeader className="flex flex-col gap-1">Lines Of Code for {repoName}</DrawerHeader>
            <DrawerBody>
            {response?.data?.languages ? response?.data?.languages.map((item:any , index:number) => 

             <>
               
              
                 <p>
                   
                         <Card className="py-4 mt-4">
                         <CardHeader className="pb-0 pt-2 px-4 flex-col items-start">
                           <p className="text-tiny uppercase font-bold"></p>
                           <p>{response?.data?.keys[index]}: {response?.data?.languages[index]} </p>
                         </CardHeader>
                         <CardBody className="overflow-visible py-2">
              
                         </CardBody>
                       </Card>
                 </p>    
                
               </>
            )
            : <p>Loading</p>
            
          }
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