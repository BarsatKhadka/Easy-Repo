import {create} from 'zustand'

interface UserStoreType{
    authenticated: boolean;
    setAuthenticated : (authenticated: boolean) => void;
    collectionName: string, 
    setCollectionName: (collectionName :string) => void;
    treeDrawerOpen: boolean;
    setTreeDrawerOpen: (treeDrawerOpen: boolean) => void;
    graphDrawerOpen: boolean;
    setGraphDrawerOpen: (graphDrawerOpen: boolean) => void;
    locDrawerOpen: boolean;
    setLocDrawerOpen: (locDrawerOpen: boolean) => void;
    treeRepoId: number |null;
    setTreeRepoId: (treeRepoId: number |null) => void;
    


}

export const useUserStore = create<UserStoreType>((set)=>({
    authenticated : false,
    setAuthenticated: (authenticated : boolean) => set(()=> ({authenticated})),
    collectionName: "none",
    setCollectionName: (collectionName:string) => set(() => ({collectionName})),
    treeDrawerOpen: false,
    setTreeDrawerOpen: (treeDrawerOpen: boolean) => set(()=> ({treeDrawerOpen}) ),
    graphDrawerOpen: false,
    setGraphDrawerOpen: (graphDrawerOpen: boolean) => set(() => ({graphDrawerOpen})),
    locDrawerOpen: false,
    setLocDrawerOpen: (locDrawerOpen: boolean) => set(() => ({locDrawerOpen})),
    treeRepoId: null,
    setTreeRepoId: (treeRepoId: number|null) => set(() => ({treeRepoId})),
   

}))