import { createContext } from "react";

export interface UserLoginContextType{
    userDetails: any;
    setUserDetails: any;
    userDetailsError: Error | null ;
    setUserDetailsError: Error | null;
}

export const UserLoginContext = createContext({})