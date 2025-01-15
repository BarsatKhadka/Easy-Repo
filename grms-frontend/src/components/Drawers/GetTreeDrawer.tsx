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


export const GetTreeDrawer = () =>{


    const {response, fetchData} = useAxios()


    //this lets me know if to open the drawer or not 
    const {treeDrawerOpen , setTreeDrawerOpen} = useUserStore()
    const{treeRepoId , setTreeRepoId} = useUserStore()


    
    useEffect(()=>{
        fetchData({url: "/getTree/"+ treeRepoId , method: 'get'})
    
    },[treeRepoId])



    


    const {isOpen, onOpen, onOpenChange} = useDisclosure();

    const [placement, setPlacement] = React.useState("left");

  
    const handleOpen = (placement: "left") => {
      setPlacement(placement);
      onOpen();
    };

    const handleOpenChange = (isOpen: boolean) => {
        if (!isOpen) {
          setTreeDrawerOpen(false);
        }
      };



      //recursiveness man
      const renderTree = (tree: any, level: number = 0) => {
        if (!tree) return null;
      
        const indent = '│   '.repeat(level) + (level > 0 ? '├── ' : '');
      
        return (
          <div style={{ fontFamily: 'monospace', whiteSpace: 'pre' }}>
            <div>
              <a href={tree.url} target="_blank" className="hover:underline">{indent}{tree.displayName}</a>
            </div>
            {tree.children && (
              <div>
                {tree.children.map((child: any) => renderTree(child, level + 1))}
              </div>
            )}
          </div>
        );
      };
    

   
    
    return(
        <>
              <div className="flex flex-wrap gap-3">
  
      </div>
      <Drawer isOpen={treeDrawerOpen} placement={"left"} size={"4xl"} onOpenChange={handleOpenChange}>
        <DrawerContent>
          {(onClose) => (
            <>
              <DrawerHeader className="flex flex-col gap-1" >Tree Structure for Repository:  {response?.data.displayName}</DrawerHeader>
              
              <DrawerBody>
                <p>
                  {response?.data ? renderTree(response?.data) : <p>Loading</p>}
                
                </p>

              </DrawerBody>
             
            </>
          )}
        </DrawerContent>
      </Drawer>
        </>
    )
}