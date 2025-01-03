import {  useEffect , useState } from "react"
import { useAxios } from "../../utility/axiosUtils"


import {Accordion, AccordionItem} from "@nextui-org/react";
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
  

    const firstDataIndex = 0 + 5 * (currentPage -1)
    const lastDataIndex = 5 * currentPage

    console.log(response)
    const defaultContent =
    "View Tree   View Lines Of Code  This repo's contribution graph" 


    
   
    return(
        <>
        <div className="flex flex-col gap-5">
      <p className="text-small text-default-500">Selected Page: {currentPage}</p>
      <Pagination color="secondary" page={currentPage} total={totalItem} onChange={setCurrentPage} />
      <div className="flex gap-2">
        <Button
          color="secondary"
          size="sm"
          variant="flat"
          onPress={() => setCurrentPage((prev) => (prev > 1 ? prev - 1 : prev))}
        >
          Previous
        </Button>
        <Button
          color="secondary"
          size="sm"
          variant="flat"
          onPress={() => setCurrentPage((prev) => (prev < totalItem ? prev + 1 : prev))}
        >
          Next
        </Button>
      </div>

    </div>
        <p>{currentPage}</p>

        <div className="flex flex-col flex-wrap gap-4">
    
    <Breadcrumbs  key= "success" color="success">
      <BreadcrumbItem>Collections</BreadcrumbItem>
      <BreadcrumbItem>All Repositories Collection</BreadcrumbItem>
    </Breadcrumbs>
  
</div>

        <div>{response?.data.githubRepo
        .sort((a: githubRepoItem, b: githubRepoItem) => a.repoId - b.repoId)
        .slice(firstDataIndex,lastDataIndex)
        .map((items: githubRepoItem) =>
    
        <div key={items.repoId}>
       <Accordion isCompact>
      <AccordionItem
        key={items.repoId}
        aria-label= "idk"
        subtitle="Press to expand"
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




