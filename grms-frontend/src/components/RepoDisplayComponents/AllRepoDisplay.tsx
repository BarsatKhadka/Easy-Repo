import { useEffect } from "react"
import { useAxios } from "../../utility/axiosUtils"


import {Accordion, AccordionItem} from "@nextui-org/react";
import {Breadcrumbs, BreadcrumbItem} from "@nextui-org/breadcrumbs";


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
    githubRepo: githubRepoItem[]
}


export const AllRepoDisplay = () =>{
    const{response,fetchData} = useAxios()

    //i am fetching all the collections in the main component because all collections will be made from the pool of all collections , it being accessible from here makes sense.
    useEffect(()=>{
        fetchData({url: '/easyrepo/collections/all', method: 'get'})
        console.log("success")

    },[]) 
    console.log(response)
    const defaultContent =
    "View Tree   View Lines Of Code  This repo's contribution graph" 
   
    return(
        <>
        <p>All Repositories Collection</p>
        <div className="flex flex-col flex-wrap gap-4">
    
    <Breadcrumbs  key= "success" color="success">
      <BreadcrumbItem>Collections</BreadcrumbItem>
      <BreadcrumbItem>All Repositories Collection</BreadcrumbItem>
    </Breadcrumbs>
</div>
        <div>{response?.data.githubRepo
        .sort((a: githubRepoItem, b: githubRepoItem) => a.repoId - b.repoId)
        .map((items: githubRepoItem) =>
        <div key={items.repoId}>
       <Accordion>
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




