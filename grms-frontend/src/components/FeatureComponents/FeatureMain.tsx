import {Textarea , Button} from "@nextui-org/react";
import React from "react";
import axios from "axios";

export const FeatureMain = () =>{
    const [value, setValue] = React.useState("");

    const backendUrl = import.meta.env.VITE_BACKEND_URL

    const postFeature = async() =>{
      if(value.length > 10){
        const response = await axios.post(backendUrl+ "/easyrepo/post/feature" , {"description": value} , {withCredentials: true , headers : {'X-CSRF-TOKEN': sessionStorage.getItem('csrf') }})
        console.log(response)
      }
        
    }

    return(
        <>
        <div className="mt-8 ml-3">
            <Textarea
      isClearable
      className="max-w-xs"
      defaultValue=""
      label="Request a feature"
      placeholder="Type here"
      variant="bordered"
      classNames={{
        base: "border-2 border-indigo-500 rounded-[20px]", 
      }}
      value = {value}
      onValueChange={setValue}
      // eslint-disable-next-line no-console
      onClear={() => console.log("textarea cleared")}
    /> 
    
     <p className="text-default-500 text-small ml-3 inline">Feature: <span className="text-warning">{value}</span></p>
     <Button color="default" className="inline ml-[160px] mt-2" variant="ghost" size="sm" onPress={postFeature}> 
        Request
      </Button> 
    </div>
    
        </>
    )
}