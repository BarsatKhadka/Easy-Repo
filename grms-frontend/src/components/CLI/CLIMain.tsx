import {Input} from "@nextui-org/react";
export const CLIMain = () =>{
    return(
        <>
        <div className="mt-8 ml-4">
                <Input
          key={"sm"}
          className="max-w-[220px]"
          defaultValue=""
          label="Commander"
          placeholder="Type your CLI command"
          radius={"sm"}
          type="text"
          variant="underlined"
        /> 
         </div>
        </>
    )
}