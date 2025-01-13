import {Input} from "@nextui-org/react";
export const CLIMain = () =>{
    return(
        <>
                <Input
          key={"sm"}
          className="max-w-[220px]"
          defaultValue="junior@nextui.org"
          label="Commander"
          placeholder="Type your command"
          radius={"sm"}
          type="text"
          variant="underlined"
        />  
        </>
    )
}