import {Input, useUser} from "@nextui-org/react";
import { useState } from "react";
import axios from "axios";
import { GetTreeDrawer } from "../Drawers/GetTreeDrawer";
import { useUserStore } from "../../store/UserStore";
import { GetRepoCommitGraphDrawer } from "../Drawers/GetCalendar";
import { GetLinesOfCodeDrawer } from "../Drawers/GetLinesOfCodeDrawer";
import {Alert} from "@nextui-org/react";



export const CLIMain = () =>{
    const [inputValue, setInputValue] = useState("");
    const[response,setResponse] = useState("");
    const backendUrl = import.meta.env.VITE_BACKEND_URL

     const {treeDrawerOpen , setTreeDrawerOpen} = useUserStore()
     const{treeRepoId , setTreeRepoId} = useUserStore()
     const{graphDrawerOpen , setGraphDrawerOpen} = useUserStore()
      const{repoName , setRepoName} = useUserStore()
    const {locDrawerOpen , setLocDrawerOpen} = useUserStore()
     const {readMeDrawerOpen , setreadMeDrawerOpen} = useUserStore()


     const openVsCodeUrl = (url: string) =>{

      window.location.href = url



     }

     //rename and delete both works by same url because it will redirect you to settings.
     const deleteUrl = async(repoName: string) =>{
      const backendUrl = import.meta.env.VITE_BACKEND_URL
      const deleteResponse = await axios.get(backendUrl+ "/easyRepo/repoTweaks/deleteRename/"+ repoName , {withCredentials: true})
      window.location.href = deleteResponse?.data 
    }
        

    const handleKeyDown = (event:any) => {
        if (event.key === "Enter") {
          handleCommand();
        }
      };

      const handleCommand = async() =>{

        const response = await axios.post(backendUrl+"/easyrepo/cli/execute", inputValue , {withCredentials: true , headers : {'X-CSRF-TOKEN': sessionStorage.getItem('csrf') }})
        setResponse(response?.data)
        if(response?.data.includes('tree')){
            setTreeDrawerOpen(true)
            setTreeRepoId(response?.data.split("=")[1])

        }
        if(response?.data.includes('calendar')){
            setGraphDrawerOpen(true)
        }
        if(response?.data.includes('loc')){
            setRepoName(response?.data.split("=")[1])
            setLocDrawerOpen(true)

        }
        if(response?.data.includes('readMe')){
            setreadMeDrawerOpen(true)
            setRepoName(response?.data.split("=")[1])
        }
        if(response?.data.includes('Vs+Code')){
          openVsCodeUrl(response?.data.split("=")[1])
        }
        if(response?.data.includes('delete')){
          deleteUrl(response?.data.split("=")[1])
        }
        if(response?.data.includes('rename')){
          deleteUrl(response?.data.split("=")[1])
        }

        

      }
    return(
        <>
        <div className="ml-4">
                <Input
          key={"sm"}
          className="max-w-[220px]"
          defaultValue=""
          label="Commander"
          placeholder="Type your CLI command"
          radius={"sm"}
          type="text"
          variant="underlined"
          onKeyDown={handleKeyDown}
          value={inputValue}
          onChange={(e) => setInputValue(e.target.value)}
        /> 
         </div>
         <div className="mt-8">
         <Alert
          key={"solid"}
          color="default"
          title={response ? response: 'Command results appear here'}
          variant={"solid"}
        />
        
        </div>
        <div>

            {response.includes('tree') && <GetTreeDrawer/> }
            {response.includes('calendar') && <GetRepoCommitGraphDrawer/>}
            {response.includes('loc') && <GetLinesOfCodeDrawer/>}

         </div>
        </>
    )
}