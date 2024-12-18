import {create} from 'zustand'

interface UserStoreType{
    authenticated: boolean;
    setAuthenticated : (authenticated: boolean) => void;
}

export const useUserStore = create<UserStoreType>((set)=>({
    authenticated : false,
    setAuthenticated: (authenticated : boolean) => set(()=> ({authenticated}))
}))