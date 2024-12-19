import axios from "axios"


export const CollectionsAll = () =>{

    const backendURL = import.meta.env.VITE_BACKEND_URL

    const allCollectionsFetch = async() =>{
        try{
         const response = await axios.get(backendURL+'/easyrepo/collections/all', {withCredentials: true})
         console.log(response)
         console.log("success")
        }
        catch(error){

        }

    }
    return (
        <>

        <p>This is collections ALl</p>
        <button onClick = {allCollectionsFetch}>This is a button</button>
        </>

    )
}