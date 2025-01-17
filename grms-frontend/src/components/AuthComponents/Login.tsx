
"use client";

import {Button, Input, Checkbox, Link, Divider} from "@nextui-org/react";
import {Icon} from "@iconify/react";
import {NavbarComponent} from "../Navbar/Navbar";



export default function Login() {

   let backendUrl = import.meta.env.VITE_BACKEND_URL

  const githubLogin = () =>{
    window.location.href = backendUrl + "/oauth2/authorization/github" 
    
}

  return (
    <>
    <div className="min-h-screen">
    <NavbarComponent/>
    <div className="flex w-full items-center justify-center mt-32 ">
      <div className="flex w-full max-w-sm flex-col gap-4 rounded-large">
        <div className="flex flex-col items-center pb-6">
            {/* {icon here} */}
          <p className="text-xl font-medium">Welcome</p>
          <p className="text-small text-default-500">Let's wrap it up.</p>
        </div>
        <form className="flex flex-col gap-3" onSubmit={(e) => e.preventDefault()}>
          <div className="flex flex-col">
            <Input
              classNames={{
                base: "-mb-[2px]",
                inputWrapper:
                  "rounded-b-none data-[hover=true]:z-10 group-data-[focus-visible=true]:z-10",
              }}
              label="Github Username"
              name="username"
              placeholder="Enter your username"
              type="text"
              variant="bordered"
            />
    
        
    
          </div>
          <Checkbox isRequired className="py-4" size="sm">
            I agree with the&nbsp;
            <Link href="#" size="sm">
              Contrubution Policy
            </Link>
          </Checkbox>
          <Button color="primary" type="submit">
            Join the Codebase
          </Button>
        </form>
        <div className="flex items-center gap-4 py-2">
          <Divider className="flex-1" />
          <p className="shrink-0 text-tiny text-default-500">or/and simply</p>
          <Divider className="flex-1" />
        </div>
        <div className="flex flex-col gap-2">
          <Button
            startContent={<Icon className="text-default-500" icon="fe:github" width={24} />}
            onPress = {() => {githubLogin()}}
            variant="bordered"
          >
            Log in with Github
          </Button>
        </div>
    
      </div>
    </div>
    </div>
    </>
  );
}