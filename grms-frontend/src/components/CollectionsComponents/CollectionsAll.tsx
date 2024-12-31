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

    console.log(allCollection)

   
    

    return (
        <>

        <p>This is collections ALl</p>
        <div>{allCollection?.githubRepo?.map((items) =>
        <div key={items.repoId}>
           <p>{items.name}</p> 
        </div>

        
        )}</div>
       
        </>

    )
}