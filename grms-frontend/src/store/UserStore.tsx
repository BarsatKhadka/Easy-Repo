import {create} from 'zustand'

interface UserStoreType{
    authenticated: boolean;
    setAuthenticated : (authenticated: boolean) => void;
    collectionName: string, 
    setCollectionName: (collectionName :string) => void;
    treeDrawerOpen: boolean;
    setTreeDrawerOpen: (treeDrawerOpen: boolean) => void;

}

export const useUserStore = create<UserStoreType>((set)=>({
    authenticated : false,
    setAuthenticated: (authenticated : boolean) => set(()=> ({authenticated})),
    collectionName: "none",
    setCollectionName: (collectionName:string) => set(() => ({collectionName})),
    treeDrawerOpen: false,
    setTreeDrawerOpen: (treeDrawerOpen: boolean) => set(()=> ({treeDrawerOpen}) )

}))