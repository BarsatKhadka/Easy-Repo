import React from "react";
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

export const GetLinesOfCodeDrawer = () =>{


    //this lets me know if to open the drawer or not 
    const {locDrawerOpen , setLocDrawerOpen} = useUserStore()


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
      <Drawer isOpen={locDrawerOpen} placement={"left"} onOpenChange={handleOpenChange}>
        <DrawerContent>
          {(onClose) => (
            <>
              <DrawerHeader className="flex flex-col gap-1">Drawer Title</DrawerHeader>
              <DrawerBody>
                <p>
                  Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam pulvinar risus non
                  risus hendrerit venenatis. Pellentesque sit amet hendrerit risus, sed porttitor
                  quam.
                </p>
                <p>
                  Magna exercitation reprehenderit magna aute tempor cupidatat consequat elit dolor
                  adipisicing. Mollit dolor eiusmod sunt ex incididunt cillum quis. Velit duis sit
                  officia eiusmod Lorem aliqua enim laboris do dolor eiusmod.
                </p>
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