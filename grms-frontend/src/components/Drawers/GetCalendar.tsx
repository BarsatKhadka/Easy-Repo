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
import axios from "axios";
import { GoogleOAuthProvider , GoogleLogin } from "@react-oauth/google";



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

const clientId = import.meta.env.VITE_GOOGLE_CLIENT_ID
const backendUrl = import.meta.env.VITE_BACKEND_URL
const [googleAuthenticated , setGoogleAuthenticated] = useState<boolean>(false)

const getGoogleAccessToken = async(credential: any) =>{
  try {
    const response = await axios.post(`${backendUrl}/auth/google/verify`, { token: credential });
    if (response.data.success) {
      setGoogleAuthenticated(true); 
      console.log("User authenticated:", response.data.user);
    } else {
      console.error("Authentication failed:", response.data.message);
    }
  } catch (error) {
    console.error("Error verifying Google token:", error);
  }
};



    return(
        <>
              <div className="flex flex-wrap gap-3">
  
      </div>
      <Drawer isOpen={graphDrawerOpen} size={"5xl"} placement={"left"} onOpenChange={handleOpenChange}>
        
        <DrawerContent>
          {(onClose) => (
            <>
              <DrawerHeader className="flex flex-col gap-1">Drawer Title</DrawerHeader>
              <DrawerBody>
                {/* <p>
                {response?.data.map((items: any) => 
                <p>{items.date.split("T")[0]}{"------"}{items.message}</p>
                )}
                </p> */}
                <GoogleOAuthProvider clientId= {clientId}>
                  {!googleAuthenticated && 
                  <GoogleLogin 
                onSuccess={credentialResponse => {
                  if (credentialResponse.credential) {
                    console.log(credentialResponse)
                    // getGoogleAccessToken(credentialResponse.credential); 
                  }
                }}
                onError={() => {
                  console.log('Login Failed');
                }}
                 />
                  }
                
                </GoogleOAuthProvider>

                {googleAuthenticated && 

                <div>
                  kaam vayena vane ta lyang hunxa yar
                </div>
                
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