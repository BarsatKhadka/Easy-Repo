import { useUserStore } from "../../store/UserStore";

interface allCollectionPropType{
    allCollection: {
        collectionId : number;
        collectionName : string;
        collectionDescription: string;
        createdAt: string;
        repositoryCount: number;
        updatedAt: string;

        masterUser:{
            masterId: number;
            username: string;
            email: string;
            avatarUrl: string;
            bio: string;
            emailVerified: boolean;
            enabled: boolean;
            provider: string;
            providerUserId: string;
        }

        githubRepo:[{
            repoId: number;
            githubId: number;
            name: string;

        }]
        
        

    };

}

//allCollection prop is coming from CollectionsMain.
export const CollectionsAll = ({allCollection}: allCollectionPropType) =>{

    const {collectionName , setCollectionName} = useUserStore()

    console.log(allCollection)

   
    

    return (
        <>
        <nav className="mt-4 -mx-3 space-y-3 ">
               
               <button className="flex items-center justify-between w-full px-3 py-2 text-xs font-medium text-gray-600 transition-colors duration-300 transform rounded-lg dark:text-gray-300 hover:bg-gray-100 dark:hover:bg-gray-800 dark:hover:text-gray-200 hover:text-gray-700"
                onClick={() => setCollectionName("all")}>
                   <div className="flex items-center gap-x-2 ">
                       <span className="w-2 h-2 bg-pink-500 rounded-full"></span>
                       <span>All Repositories Collection</span>
                   </div>


                   <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" className="w-4 h-4 rtl:rotate-180">
                       <path stroke-linecap="round" stroke-linejoin="round" d="M8.25 4.5l7.5 7.5-7.5 7.5" />
                   </svg>
               </button>

           
           </nav>
        {/* <div>{allCollection?.githubRepo?.map((items) =>
        <div key={items.repoId}>
           <p>{items.name}</p> 
        </div>

        
        )}</div> */}
       
        </>

    )
}