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
    const[values,setValues] = useState([])
    

    const{response , fetchData} = useAxios();

    useEffect(()=>{
      fetchData({url: '/easyrepo/collections/all', method: 'get'})
      console.log("success on another world")

  },[onOpen]) 


  const handleSelectedKeys = (selectedKeys: any) =>{
    setValues(selectedKeys)
    console.log(selectedKeys )
  

  }

  const creatingCollect = async() =>{
    try{
        const response = await axios.post("http://localhost:8080/easyrepo/collections/createCollection", {'collectionName': 'verynaya' , 'collectionDescription': 'wala' , 'githubRepoIds': [2,3,4,5]}, 
        {withCredentials: true , headers : {'X-CSRF-TOKEN': sessionStorage.getItem('csrf') }})
        console.log('done boss')
    }
    catch(error){

    }

}
 


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
                  <Button color="success" onPress={() => {onClose ; creatingCollect()}}>
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
        <div className="flex flex-wrap gap-4 mt-8">
      <Snippet color= 'danger' variant='bordered' className="ml-3 w-full mr-12"><a href="#" className="hover:underline text-white" onClick={() => setCollectionName(item.collectionName)}>{item.collectionName}</a></Snippet>
    </div> ) }
       
        </div>
    

          

        
        </>
    )
}