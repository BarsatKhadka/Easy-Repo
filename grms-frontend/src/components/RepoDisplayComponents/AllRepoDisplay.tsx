import {  useEffect , useState } from "react"
import { useAxios } from "../../utility/axiosUtils"


import {Accordion, AccordionItem , Avatar, useUser} from "@nextui-org/react";
import {Breadcrumbs, BreadcrumbItem} from "@nextui-org/breadcrumbs";
import {Button, ButtonGroup} from "@nextui-org/button";
import {Card, CardBody} from "@nextui-org/react";


import{Pagination , usePagination} from "@nextui-org/react"
import { GetTreeDrawer } from "../Drawers/GetTreeDrawer";
import { useUserStore } from "../../store/UserStore";
import { GetRepoCommitGraphDrawer } from "../Drawers/GetCalendar";
import { GetLinesOfCodeDrawer } from "../Drawers/GetLinesOfCodeDrawer";
import { ReadMeDrawer } from "../Drawers/ReadMeDrawer";
import axios from 'axios'



interface githubRepoItem{

    repoId: number;
    githubId: number;
    name: string;
    created_at: string;
    default_branch: string;
    description: string;
    fork: boolean;
    fullName: string;
    html_url: string;
    language:string;



}
interface GithubRepoType{
    githubRepo: githubRepoItem[] | []
}


export const AllRepoDisplay = () =>{
    const{response,fetchData} = useAxios()
    
     
    

    //i am fetching all the collections in the main component because all collections will be made from the pool of all collections , it being accessible from here makes sense.
    useEffect(()=>{
        fetchData({url: '/easyrepo/collections/all', method: 'get'})

    },[]) 

    //total Item by number of pages. 5 per page.
    const totalItem = Math.ceil(response?.data?.repositoryCount /6) 

    const [currentPage, setCurrentPage] = useState(1);
    
    const convertHtmlUrl = (html_url: string) =>{
      return html_url.replace(/https:\/\/github\.com/g, "https://github.dev");
    }

    const deleteUrl = async(repoName: string) =>{
      const backendUrl = import.meta.env.VITE_BACKEND_URL
      const deleteResponse = await axios.get(backendUrl+ "/easyRepo/repoTweaks/deleteRename/"+ repoName , {withCredentials: true})
      window.location.href = deleteResponse?.data 
    }


    const firstDataIndex = 6 * (currentPage -1)
    const lastDataIndex = 6 * currentPage


    const array = ['default','success','primary','secondary','warning','danger'];
    

    



    //drawer open states that will control the drawer from here
    const{treeDrawerOpen , setTreeDrawerOpen} = useUserStore()
    const{graphDrawerOpen, setGraphDrawerOpen} = useUserStore()
    const{locDrawerOpen , setLocDrawerOpen} = useUserStore()
    const{readMeDrawerOpen, setreadMeDrawerOpen} = useUserStore()

    const{treeRepoId, setTreeRepoId , repoName,  setRepoName} = useUserStore()
    
  
    return(
        <>
    <div className="flex justify-end items-center mt mr-1">
  <div className="hidden sm:block mr-2">
    <Pagination
      color="danger"
      page={currentPage}
      total={totalItem}
      onChange={setCurrentPage}
      className="mr-1"
      style={{ color: 'white' }}
    />
  </div>

  <div className="flex gap-1 sm:gap-2">
    <Button
      color="default"
      size="sm"
      variant="flat"
      onPress={() => setCurrentPage((prev) => (prev > 1 ? prev - 1 : prev))}
      aria-label="Previous Page"
    >
      ← 
    </Button>
    <Button
      color="default"
      size="sm"
      variant="flat"
      onPress={() => setCurrentPage((prev) => (prev < totalItem ? prev + 1 : prev))}
      aria-label="Next Page"
    >
      → 
    </Button>
  </div>
</div>  
        <div className="flex flex-col ">
    

    </div>

        <div className="flex flex-col flex-wrap gap-4 ml-4 mb-12">
        
    
    <Breadcrumbs  key= "success" color="success" className="mt-8">
      <BreadcrumbItem >Collections</BreadcrumbItem>
      <BreadcrumbItem >All Repositories Collection</BreadcrumbItem>
    </Breadcrumbs>



    {/* {real display section} */}
  
</div>

        <div>{response?.data?.githubRepo
        .sort((a: githubRepoItem, b: githubRepoItem) => a.repoId - b.repoId)
        .slice(firstDataIndex,lastDataIndex)
        .map((items: githubRepoItem) =>{
          const randomIndex = Math.floor(Math.random() * array.length);
        const randomColor = array[randomIndex] as "success" | "default" | "primary" | "secondary" | "warning" | "danger";;

          return(

        
    
        <div key={items.repoId}>
            
       <Accordion isCompact
       defaultExpandedKeys={["theme"]}
       
        
        >
       
       <AccordionItem 
    key={items.repoId}
    aria-label="idk"
    startContent={
        <Avatar
          isBordered
          style={{ backgroundColor: 'white' }}
          color={randomColor}
          radius="lg"
          src={`https://cdn.jsdelivr.net/npm/simple-icons/icons/${items.language ? items.language.toLowerCase() : 'github'}.svg`}
        />
    }
    indicator={({ isOpen }) => (isOpen ? "close" : "open")}
    classNames={{
        subtitle: "text-green sm", 
        trigger: "px-2 py-0 rounded-lg h-14 flex items-center bg duration-200 ease-in-out hover:bg-default hover:bg-opacity-8"
    }}
    className="mt-8"
    subtitle={
        <span className="hidden sm:inline">{items.description}</span>
    }
    title={items.name}
>
        
        <Card style={{backgroundColor: 'black'}}>
      <CardBody>
        <p>
        <span style={{color: '#98FB98  '}}>get:</span> ------
        <a href="#" className= "hover:underline" onClick={() => (setTreeDrawerOpen(true) , setTreeRepoId(items.repoId))}>Files in Tree Structure </a>
        ------
        <a href="#" className="hover:underline" onClick={() => (setGraphDrawerOpen(true) ,setTreeRepoId(items.repoId))}> Plan commits </a>------
        <a href="#" className="hover:underline" onClick={() => (setLocDrawerOpen(true), setRepoName(items.name))} >{"   "} Lines of code </a>------
        <a href="#" className="hover:underline" onClick={()=> (setreadMeDrawerOpen(true) , setRepoName(items.name))}> readMe</a>
        </p>
        <p>
    
         
         <span style={{color: '#fde047'}}> post: </span>-----<a href={convertHtmlUrl(items.html_url)} target="_blank" className="hover:underline">Open with VS Code
         </a>
         </p>
        <p>
         <span style={{color: '#ED4337'}}> delete: </span> ---<a href="#" onClick={(e) =>{ e.preventDefault();  deleteUrl(items.name)}} target="_blank" className="hover:underline">this.repository</a>
         </p>
         <p>
        <span style={{color: '#ED008C  '}} >put: </span><span>------<a href="#" onClick={(e) =>{ e.preventDefault();  deleteUrl(items.name)}} target="_blank" className="hover:underline">this.rename</a></span>
    
         </p>
      </CardBody>
    </Card>

    {/* this will just popup from left side when onClick on Files in tree structure */}
    <GetTreeDrawer />
    <GetRepoCommitGraphDrawer/>
    <GetLinesOfCodeDrawer/>
    <ReadMeDrawer/>
    
      </AccordionItem>
    </Accordion>
        </div>

        
        )})}</div>
           
        </>
    )
}




