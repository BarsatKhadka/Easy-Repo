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
    repoName: string |null;
    setRepoName: (repoName: string |null) => void;
    readMeDrawerOpen: boolean;
    setreadMeDrawerOpen: (readMeDrawerOpen: boolean) => void;
    


}

export const useUserStore = create<UserStoreType>((set)=>({
    authenticated : false,
    setAuthenticated: (authenticated : boolean) => set(()=> ({authenticated})),
    collectionName: "All Repositories",
    setCollectionName: (collectionName:string) => set(() => ({collectionName})),
    treeDrawerOpen: false,
    setTreeDrawerOpen: (treeDrawerOpen: boolean) => set(()=> ({treeDrawerOpen}) ),
    graphDrawerOpen: false,
    setGraphDrawerOpen: (graphDrawerOpen: boolean) => set(() => ({graphDrawerOpen})),
    locDrawerOpen: false,
    setLocDrawerOpen: (locDrawerOpen: boolean) => set(() => ({locDrawerOpen})),
    treeRepoId: null,
    setTreeRepoId: (treeRepoId: number|null) => set(() => ({treeRepoId})),
    repoName: null,
    setRepoName: (repoName: string | null) => set(() => ({repoName})),
    readMeDrawerOpen: false,
    setreadMeDrawerOpen: (readMeDrawerOpen: boolean) => set(() => ({readMeDrawerOpen}))
   

}))