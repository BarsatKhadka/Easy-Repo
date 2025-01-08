import { useEffect } from "react"
import { useUserStore } from "../../store/UserStore"

import { AllRepoDisplay } from "./AllRepoDisplay"


export const RepoDisplayMain = () =>{
    
    const {collectionName} = useUserStore()

    //when collection Name changes , hit the endpoint to display.
    useEffect(() => {
        

    }, [collectionName])


    return(
        <>
        {collectionName === "All Repositories" && <AllRepoDisplay/>}
        </>
    )
}