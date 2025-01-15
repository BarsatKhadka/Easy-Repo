import React, { useEffect , useState } from "react";
import {
  Drawer,
  DrawerContent,
  DrawerHeader,
  DrawerBody,
  DrawerFooter,
  Button,
  useDisclosure,
  Calendar,
} from "@nextui-org/react";
import { useUserStore } from "../../store/UserStore";
import { useAxios } from "../../utility/axiosUtils";
import { ContinuousCalendar } from "../Calendar/Calendar";



export const GetRepoCommitGraphDrawer = () =>{

    // const {response, fetchData} = useAxios()

    //this lets me know if to open the drawer or not , treeRepoId gives current repoId
    const {graphDrawerOpen , setGraphDrawerOpen , treeRepoId , setTreeRepoId} = useUserStore()
    

    
    // useEffect(()=>{
    //     fetchData({url: "/easyrepo/getCommitGraph/"+ treeRepoId , method: 'get'})
        
    // },[treeRepoId])


    


   
    


    // console.log(response?.data)



    const {isOpen, onOpen, onOpenChange} = useDisclosure();

    const [placement, setPlacement] = React.useState("left");

  
    const handleOpen = (placement: "left") => {
      setPlacement(placement);
      onOpen();
    };

    const handleOpenChange = (isOpen: boolean) => {
        if (!isOpen) {
          setGraphDrawerOpen(false);
        }
      };
      

//       const dates: any = {}

//       response?.data.map((items: any) => {
//         const truncatedDate = items.date.split("T")[0]
//         if(!dates.hasOwnProperty(truncatedDate))
//         {
//             dates[truncatedDate] = 1

//         }
//         else{
//             dates[truncatedDate] +=1 
//         }
// })





// console.log(dates)





    return(
        <>
              <div className="flex flex-wrap gap-3">
  
      </div>
      <Drawer isOpen={graphDrawerOpen} size={"5xl"} placement={"left"} onOpenChange={handleOpenChange}>
        
        <DrawerContent>
          {(onClose) => (
            <>
              <DrawerHeader className="flex flex-col gap-1">Commit Calendar</DrawerHeader>
              <DrawerBody>
                {/* <p>
                {response?.data.map((items: any) => 
                <p>{items.date.split("T")[0]}{"------"}{items.message}</p>
                )}
                </p> */}


                <ContinuousCalendar/>
                  
            
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