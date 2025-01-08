import { CreateCollections } from "./CreateCollections"

//library imports
import {useAxios} from "../../utility/axiosUtils"
import {useEffect , useState} from 'react'
import {Select, SelectItem} from "@nextui-org/react";
import {Snippet} from "@nextui-org/snippet";

import {
    Modal,
    ModalContent,
    ModalHeader,
    ModalBody,
    ModalFooter,
    Button,
    useDisclosure,
    Input,
  } from "@nextui-org/react";

import { useUserStore } from "../../store/UserStore";


export const  CollectionsPopUp = () => {
    const {isOpen, onOpen, onOpenChange} = useDisclosure();

    const{response , fetchData} = useAxios();

    useEffect(()=>{
      fetchData({url: '/easyrepo/collections/all', method: 'get'})
      console.log("success on another world")

  },[onOpen]) 


  
 


    const repositories = [
      {label: "", key: "", description: ""}
    ];

    console.log(response?.data?.githubRepo)
    response?.data?.githubRepo.forEach((item: { name: string; repoId: string }) => {
      repositories.push({
        label: item.name,
        key: item.repoId,
        description: item.name, 
      });
    });
  
    return (
      <>
        <button color="primary" onClick={onOpen} className="mr-24 mt-2">
        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" className="w-4 h-4">
                        <path stroke-linecap="round" stroke-linejoin="round" d="M12 4.5v15m7.5-7.5h-15" />
                    </svg>
        </button>   
        <Modal isOpen={isOpen} placement="top-center" onOpenChange={onOpenChange}  >
          <ModalContent>
            {(onClose) => (
              <>
                <ModalHeader className="flex flex-col gap-1">Create a Collection</ModalHeader>
                <ModalBody>
                  <Input
                    
                    label="Collection Name"
                    placeholder=""
                    variant="bordered"
                  />
                     <div className="flex w-full flex-wrap md:flex-nowrap gap-4">
                     <Select
      className="max-w"
      label="Favorite Animal"
      placeholder="Select an animal"
      selectionMode="multiple"
    >
      {repositories.map((repo) => (
        <SelectItem key={repo.key}>{repo.label}</SelectItem>
      ))}
    </Select>
    </div>
                  <div className="flex py-2 px-1 justify-between">
          
                  </div>
                </ModalBody>
                <ModalFooter>
                  <Button color="danger" variant="flat" onPress={onClose}>
                    Close
                  </Button>
                  <Button color="success" onPress={onClose}>
                    Create
                  </Button>
                </ModalFooter>
              </>
            )}
          </ModalContent>
        </Modal>
      </>
    );
  }



//this will contain all the collections.
export const CollectionsMain = () =>{

  const {setCollectionName} = useUserStore()

  useEffect(() =>{

    getAllCollections()

  },[])
  
  const backendUrl = import.meta.env.VITE_BACKEND_URL;
  const [collections,setCollections] = useState<any>(null)

  const getAllCollections = async() => {
   const responseServer =  await fetch(backendUrl + "/easyrepo/collections/allExistingCollections" , {method: 'GET' , credentials: 'include'}  )
   const responseHere = await(responseServer.json())
   setCollections(responseHere)
  }

  console.log(collections)

    

    return (
        <>
    <div>
    
            <div className="flex items-center justify-between">
                <h2 className="text-base font-semibold text-gray-800 dark:text-white mt-8 mb-8 ml-4 ">Collections</h2>
                <CollectionsPopUp/>

            </div>
             {/* the type this prop must return is defined in the element itself , not here. */}

        {collections?.map((item:any) =>      
        <div className="flex flex-wrap gap-4 mt-8">
      <Snippet color= 'danger' variant='bordered' className="ml-3"><a href="#" className="hover:underline text-white" onClick={() => setCollectionName(item.collectionName)}>{item.collectionName}</a></Snippet>
    </div> ) }
       
        </div>
    

         <button onClick = {()=> {}}>This is a button</button>

         
        <CreateCollections/>    

        
        </>
    )
}