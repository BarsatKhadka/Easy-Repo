//library imports
import {useAxios} from "../../utility/axiosUtils"
import {useEffect , useState} from 'react'
import {Select, SelectItem} from "@nextui-org/react";
import {Snippet} from "@nextui-org/snippet";
import {Alert} from "@nextui-org/react";
import { BsBoxSeamFill } from "react-icons/bs";
import axios from 'axios'


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
    const[values,setValues] = useState<string>("")
    
    const[createCollectionName, setCreateCollectionName] = useState("")

    const{response , fetchData} = useAxios();

    useEffect(()=>{
      fetchData({url: '/easyrepo/collections/all', method: 'get'})
      console.log("success on another world")

  },[onOpen]) 


  const githubRepoIds: Array<string> = []
    const handleSelectedKeys = (e: any) =>{
      const selectedValues = e.target.value
    setValues(selectedValues);
   
    
    }
githubRepoIds.push(values)

  const handleChange = (event: any) =>{
    setCreateCollectionName(event.target.value)
  }

  

  console.log(githubRepoIds)

  const creatingCollect = async() =>{
    try{
     
      const repoIds: Array<number>= githubRepoIds[0].split(",").map(Number);
      
      //spaces give spaces in url and cause backend to crash. Temporary solution for right now.
        const finalcreateCollectionName = createCollectionName.replace(/\s+/g, '_');

        await axios.post("http://localhost:8080/easyrepo/collections/createCollection", {'collectionName': finalcreateCollectionName   , 'githubRepoIds': repoIds}, 
        {withCredentials: true , headers : {'X-CSRF-TOKEN': sessionStorage.getItem('csrf') }})
        location.reload()

    }
    catch(error){

    }

}
 
// console.log(values)


    const repositories = [
      {label: "", key: "", description: ""}
    ];


    response?.data?.githubRepo.forEach((item: { name: string; repoId: string }) => {
      repositories.push({
        label: item.name,
        key: item.repoId,
        description: item.name, 
      });
    });


  
    return (
      <>
       <Button color="default" size="sm" variant="flat" onPress={onOpen}  >
            + New Collection 
        </Button>
        <Modal isOpen={isOpen} placement="top-center" onOpenChange={onOpenChange}  >
          <ModalContent>
            {(onClose) => (
              <>
                <ModalHeader className="flex flex-col gap-1">Create a Collection</ModalHeader>
                 <ModalBody className="space-y-6">
                  
                  <Input
                    label="Collection Name"
                    placeholder="Enter collection name"
                    variant="bordered"
                    onChange={handleChange}
                    className="w-full"
                    classNames={{
                      inputWrapper: "border-gray-300 hover:border-blue-500 focus:border-blue-500",
                      label: "text-gray-700 font-medium",
                    }}
                  />

                  
                  <div className="flex w-full flex-col gap-4">
                    <Select
                      label="Your Repositories"
                      placeholder="Select your repositories"
                      selectionMode="multiple"
                      onChange={handleSelectedKeys}
                      className="w-full"
                      classNames={{
                        trigger: "border-gray-300 hover:border-blue-500 focus:border-blue-500",
                        label: "text-gray-700 font-medium",
                        value: "text-gray-900",
                      }}
                    >
                      {repositories.map((repo) => (
                        <SelectItem key={repo.key} className="text-gray-700 hover:bg-blue-50">
                          {repo.label}
                        </SelectItem>
                      ))}
                    </Select>
                  </div>

                  
                  <div className="flex py-2 px-1 justify-between"></div>
                </ModalBody>
                <ModalFooter>
                  <Button color="danger" variant="flat" onPress={onClose}>
                    Close
                  </Button>
                  <Button color="success" onPress={() => {onClose ; creatingCollect() }}>
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

  


    

    return (
        <>
    <div>
    
    <div className="flex items-center justify-center w-100 mt-5 mr-5">
      <Alert
        description="group your repo with collections"
        endContent={
          <CollectionsPopUp/>
        }
        title="Collections"
        variant="faded"
        icon = {<BsBoxSeamFill />}
      />
    </div>
             {/* the type this prop must return is defined in the element itself , not here. */}

        {collections?.map((item:any) =>      
       <div className="flex flex-wrap gap-4 mt-8" key={item.collectionName}>
       <Snippet hideCopyButton color="warning" variant="flat" className="ml-3 w-full h-12 mr-12 flex items-center justify-between">
         <a href="#" className=" text-white" onClick={() => setCollectionName(item.collectionName)}>
          <span className="hover:underline">{item.collectionName}</span> 
         </a>
       </Snippet>
     </div>) }
       
        </div>
    

          

        
        </>
    )
}