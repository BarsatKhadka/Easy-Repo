import React, { useEffect } from "react";
import {
  Drawer,
  DrawerContent,
  DrawerHeader,
  DrawerBody,
  useDisclosure,
} from "@nextui-org/react";
import { useUserStore } from "../../store/UserStore";
import { useAxios } from "../../utility/axiosUtils";
import {Card, CardHeader, CardBody } from "@nextui-org/react";



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
    <Drawer isOpen={locDrawerOpen} placement="left" size="4xl" onOpenChange={handleOpenChange}>
  <DrawerContent className="bg-gradient-to-r from-gray-100 via-white to-gray-200">
    {(onClose) => (
      <>
        <DrawerHeader className="flex flex-col gap-2">
          <h2 className="text-xl font-bold text-gray-900">Lines of Code for {repoName}</h2>
          <p className="text-sm text-gray-600">Detailed breakdown of code lines by language</p>
        </DrawerHeader>
        <DrawerBody className="bg-gradient-to-br from-white to-gray-100 text-gray-800">
          {response?.data?.languages ? (
            response.data.languages.map((item: any, index: number) => (
              <Card
                className="py-4 mt-4 bg-white border border-gray-300 shadow-md hover:shadow-lg transform transition-all duration-300 hover:scale-105"
                key={index}
              >
                <CardHeader className="pb-0 pt-2 px-4 flex items-center">
                  <div className="flex items-center gap-2">
                    <span className="text-lg font-bold text-gray-800">{response.data.keys[index]}</span>
                    <span className="bg-blue-100 text-blue-800 text-xs font-semibold ml-2 px-2.5 py-0.5 rounded">
                      {item} LOC
                    </span>
                  </div>
                </CardHeader>
                <CardBody className="overflow-visible py-2 px-4">
                  <p className="text-sm text-gray-600">
                    {item} lines of code written in {response.data.keys[index]}.
                  </p>
                  
                </CardBody>
              </Card>
            ))
          ) : (
            <p className="text-center text-gray-500">Loading...</p>
          )}
        </DrawerBody>
      </>
    )}
  </DrawerContent>
</Drawer>

        </>
    )
}