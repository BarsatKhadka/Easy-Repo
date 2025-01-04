import {  useEffect , useState } from "react"
import { useAxios } from "../../utility/axiosUtils"


import {Accordion, AccordionItem , Avatar} from "@nextui-org/react";
import {Breadcrumbs, BreadcrumbItem} from "@nextui-org/breadcrumbs";
import {Button, ButtonGroup} from "@nextui-org/button";

import{Pagination , usePagination} from "@nextui-org/react"


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
        console.log("success")

    },[]) 

    //total Item by number of pages. 5 per page.
    const totalItem = Math.ceil(response?.data?.repositoryCount /5) 

    const [currentPage, setCurrentPage] = useState(1);
    
  

    const firstDataIndex = 5 * (currentPage -1)
    const lastDataIndex = 5 * currentPage

    console.log(response)
    const defaultContent =
    "View Tree   View Lines Of Code  This repo's contribution graph" 


    
   
    return(
        <>
        <div className="flex justify-end items-start mt-8 mr-5 ">
<Pagination color="success" page={currentPage} total={totalItem} onChange={setCurrentPage} className="mr-1" style= {{color: 'white'}}/>
      <div className="flex gap-2">
        <Button
          color="default"
          size="sm"
          variant="flat"
          onPress={() => setCurrentPage((prev) => (prev > 1 ? prev - 1 : prev))}
          

        >
            Left
          ←
           
        </Button>
        <Button
          color="default"
          size="sm"
          variant="flat"
          onPress={() => setCurrentPage((prev) => (prev < totalItem ? prev + 1 : prev))}
        >
          → Right

        </Button>
      </div>
      </div>
        <div className="flex flex-col ">
    

    </div>

        <div className="flex flex-col flex-wrap gap-4 ml-4 mb-12">
        
    
    <Breadcrumbs  key= "success" color="success">
      <BreadcrumbItem >Collections</BreadcrumbItem>
      <BreadcrumbItem >All Repositories Collection</BreadcrumbItem>
    </Breadcrumbs>
  
</div>

        <div>{response?.data.githubRepo
        .sort((a: githubRepoItem, b: githubRepoItem) => a.repoId - b.repoId)
        .slice(firstDataIndex,lastDataIndex)
        .map((items: githubRepoItem) =>
    
        <div key={items.repoId}>
            
       <Accordion isCompact
       defaultExpandedKeys={["theme"]}
        
        >
       
      <AccordionItem 
        key={items.repoId}
        aria-label= "idk"
        startContent={
            <Avatar
              isBordered
              style = {{backgroundColor: 'white'  }}
              color = 'success'
              radius="lg"
              src={`https://cdn.jsdelivr.net/npm/simple-icons/icons/${items.language ? items.language.toLowerCase(): 'github'}.svg`}
              

            />
 
          }
          indicator={({isOpen}) => (isOpen ? "close" : "open")}

          classNames={{subtitle: "text-green" ,trigger: "px-2 py-0 data-[hover=true]:bg-default-100 rounded-lg h-14 flex items-center" }}
          className="mt-8"

        subtitle={items.description}
        title= {items.name}
        
      >
        
        
        {defaultContent}
    
      </AccordionItem>
    </Accordion>
        </div>

        
        )}</div>



           
        </>
    )
}




