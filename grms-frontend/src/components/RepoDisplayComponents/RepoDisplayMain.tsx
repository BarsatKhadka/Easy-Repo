import { useEffect } from "react"
import { useUserStore } from "../../store/UserStore"

import { AllRepoDisplay } from "./AllRepoDisplay"


export const RepoDisplayMain = () =>{
    
    const {collectionName , setCollectionName} = useUserStore()

    //when collection Name changes , hit the endpoint to display.
    useEffect(() => {
        

    }, [collectionName])


    return(
        <>
        {collectionName === "all" && <AllRepoDisplay/>}
        </>
    )
}