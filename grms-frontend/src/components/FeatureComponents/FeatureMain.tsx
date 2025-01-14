import {Textarea , Button} from "@nextui-org/react";
import {Chip} from "@nextui-org/react";
import React from "react";
import axios from "axios";
import {User} from "@nextui-org/user";
import {Code} from "@nextui-org/react";


export const FeatureMain = () =>{
    const [value, setValue] = React.useState("");


    const backendUrl = import.meta.env.VITE_BACKEND_URL

    const postFeature = async() =>{
      if(value.length > 10){
        const response = await axios.post(backendUrl+ "/easyrepo/post/feature" , {"description": value} , {withCredentials: true , headers : {'X-CSRF-TOKEN': sessionStorage.getItem('csrf') }})
        
      }
        
    }

    return(
        <>
        <div className="mt-8 ml-3">
            <Textarea
      isClearable
      className="max-w-xs secondary"
      defaultValue=""
      label="Request a feature"
      placeholder="Type here"
      variant="bordered"
      classNames={{
        base: "border-2 border-gray-500 ", 
      }}
      value = {value}
      onValueChange={setValue}
      // eslint-disable-next-line no-console
      onClear={() => console.log("textarea cleared")}
    /> 
    
     <p className="text-default-500 text-small ml-3 inline">Feature: <span className="text-warning">{value}</span></p>
     <div>
     <Button color="default" className=" ml-2 mt-4 focus-visible:ring-offset-0 focus-visible:ring-0" variant="ghost" size="sm" onPress={postFeature}> 
        Request
      </Button> 
      </div>
    </div>
    <div>
     <Chip color="warning" variant="dot" className="mt-3 ml-4"> 
      We'll email you if your feature is implemented.</Chip>
      </div>
      <div>
        <div className="mt-12 ml-4">
      <Code color="secondary">Contributors</Code>
      </div>
      <User
      avatarProps={{
        src: "https://avatars.githubusercontent.com/u/184331957?s=400&u=b4b32d6e417646b36b6ace28b2bc0505e802770a&v=4",
      }}
      description="Developer"
      name="Barsat Khadka"
      className="ml-4 mt-3  "
    />
     <User
      avatarProps={{
        src: "",
      }}
      description= {<a href="" className="hover:underline">How to contribute</a>}
      name="Be the next contributor"
      className="ml-4 mt-3  "
    />
    </div>
  
        </>
    )
}