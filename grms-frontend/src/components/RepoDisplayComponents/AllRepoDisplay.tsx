import { useEffect } from "react"
import { useAxios } from "../../utility/axiosUtils"


interface githubRepoItem{

    repoId: number;
    githubId: number;
    name: string

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

   
    return(
        <>
        <p>Hello i am all display</p>
        <div>{response?.data.githubRepo.map((items: githubRepoItem) =>
        <div key={items.repoId}>
           <p>{items.name}</p> 
        </div>

        
        )}</div>
        </>
    )
}