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
       <Button color="default" size="sm" variant="flat" onPress={onOpen}  >
            + New Collection 
        </Button>
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
                    onChange={handleChange}
                  />
                     <div className="flex w-full flex-wrap md:flex-nowrap gap-4">
                     <Select
      className="max-w"
      label="Favorite Animal"
      placeholder="Select an animal"
      selectionMode="multiple"
      onChange={handleSelectedKeys}
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
    console.log("this called")

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
    
    <div className="flex items-center justify-center w-100 mt-5 mr-5">
      <Alert
        color="secondary"
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
       <Snippet hideCopyButton color="danger" variant="bordered" className="ml-3 w-full h-12 mr-12 flex items-center justify-between">
         <a href="#" className=" text-white" onClick={() => setCollectionName(item.collectionName)}>
          <span className="hover:underline">{item.collectionName}</span> 
         </a>
       </Snippet>
     </div>) }
       
        </div>
    

          

        
        </>
    )
}