import { CollectionsAll } from "./CollectionsAll"
import { CreateCollections } from "./CreateCollections"

//library imports
import {useAxios} from "../../utility/axiosUtils"
import {useEffect , useState} from 'react'
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
  import {Autocomplete, AutocompleteItem} from "@nextui-org/react";


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
        <button color="primary" onClick={onOpen}>
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
      <Autocomplete
        className="max-w"
        defaultItems={repositories}
        label="Repositories to add"
        placeholder="Search repositories"
      >
        {(item) => <AutocompleteItem key={item.key}>{item.label}</AutocompleteItem>}
      </Autocomplete>
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


    

    return (
        <>

    <aside className="flex flex-col w-80 h-screen px-5 py-8 overflow-y-auto  ">

    <div>
    
            <div className="flex items-center justify-between">
                <h2 className="text-base font-semibold text-gray-800 dark:text-white">Collections</h2>
                <CollectionsPopUp/>

            </div>
             {/* the type this prop must return is defined in the element itself , not here. */}
        <CollectionsAll />

            <nav className="mt-4 -mx-3 space-y-3 ">
               
                <button className="flex items-center justify-between w-full px-3 py-2 text-xs font-medium text-gray-600 transition-colors duration-300 transform rounded-lg dark:text-gray-300 hover:bg-gray-100 dark:hover:bg-gray-800 dark:hover:text-gray-200 hover:text-gray-700">
                    <div className="flex items-center gap-x-2 ">
                        <span className="w-2 h-2 bg-pink-500 rounded-full"></span>
                        <span>Meraki UI Components</span>
                    </div>


                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" className="w-4 h-4 rtl:rotate-180">
                        <path stroke-linecap="round" stroke-linejoin="round" d="M8.25 4.5l7.5 7.5-7.5 7.5" />
                    </svg>
                </button>

            
            </nav>
        </div>
    
</aside>
         <button onClick = {()=> {}}>This is a button</button>

         
        <CreateCollections/>    

        
        </>
    )
}